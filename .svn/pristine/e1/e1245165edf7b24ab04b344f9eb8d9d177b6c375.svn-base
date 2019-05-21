package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.CheckPlanBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public interface CheckPlanContract {
    interface ICheckPlanView extends IView{
        /**
         * 检查计划成功返回
         * @param checkPlanBean
         */
        void setCheckPlanData(CheckPlanBean checkPlanBean);
    }
    interface ICheckPlanPresenter extends IPresenter<ICheckPlanView>{
        /**
         * 检查计划
         * @param userId
         * @param pagerNumber
         * @param total
         */
        void queryCheckPlan(String userId,int pagerNumber,String total);
    }
}
