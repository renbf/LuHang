package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.DangersRectificationBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public interface DangersRectificationContract {
    interface IDangersRectificationView extends IView{
        /**
         * 隐患整改完成
         * @param dangersRectificationBean
         */
        void setDangersRectification(DangersRectificationBean dangersRectificationBean);
    }
    interface IDangersRectificationPresenter extends IPresenter<IDangersRectificationView> {
        /**
         * 隐患整改接口
         * @param userId
         * @param pageNumber
         * @param total
         */
        void queryDangersRectification(String userId,int pageNumber,String total);
    }
}
