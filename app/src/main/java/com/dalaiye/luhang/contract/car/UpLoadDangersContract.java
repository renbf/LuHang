package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.UpLoadDangersDefaultInfoBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.io.File;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 注释
 **/
public interface UpLoadDangersContract {

    interface IUpLoadDangersView extends IView{
        /**
         * 返回数据
         * @param upLoadDangersDefaultInfoBean
         */
        void setInitData(UpLoadDangersDefaultInfoBean upLoadDangersDefaultInfoBean);

        /**
         *上报隐患成功
         * @param message
         */
        void upLoadSuccessful(String message);
    }

    interface IUpLoadDangerPresenter extends IPresenter<IUpLoadDangersView> {
        /**
         * 获取选择的数据
         * @param businessId 部门id
         */
        void getInitData(String businessId);

        /**
         * 上报隐患
         * @param inspectPlanId
         * @param dangerJson
         * @param fileList
         */
        void upLoad(String inspectPlanId,String dangerJson,List<File> fileList);
    }
}
