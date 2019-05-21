package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.CheckProjectBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 注释
 **/
public interface CheckProjectContract {

    interface ICheckProjectView extends IView{
        /**
         * 返回数据
         * @param checkProjectBeans
         */
        void setCheckProject(List<CheckProjectBean> checkProjectBeans);

        /**
         * 保存检查项成功
         */
        void saveCheckProjectSuccessful();
    }

    interface ICheckProjectPresenter extends IPresenter<ICheckProjectView> {
        /**
         * 查询检查项目详情
         * @param inspectPlanId
         * @param projectId
         */
        void queryCheckProject(String inspectPlanId,String projectId);

        /**
         * 保存检查项
         */
        void saveCheckProject(String json);
    }

}
