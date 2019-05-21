package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.DangersAcceptanceBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public interface DangersAcceptanceContract {

    interface IDangersAcceptanceView extends IView{
        /**
         * 设置隐患验收的数据
         * @param dangersAcceptanceData
         */
        void setDangersAcceptanceData(DangersAcceptanceBean dangersAcceptanceData);
    }
    interface IDangersAcceptancePresenter extends IPresenter<IDangersAcceptanceView> {
        /**
         * 隐患验收
         * @param userId
         * @param pagerNumber
         * @param total
         */
        void getDangersAcceptanceData(String userId,int pagerNumber,String total);
    }
}
