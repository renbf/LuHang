package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.RectificationBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 注释
 **/
public interface DangersRectificationDetailsContract {
    interface IDangersRectificationDetailsView extends IView{
        /**
         * 隐患整改详情成功返回
         */
        void queryHiddenDangerDetailSuccessful(RectificationBean rectificationBean);

        /**
         * 拒绝回调
         * @param message
         */
        void refuseHiddenDangerSuccessful(String message);
    }
    interface IDangersRectificationDetailsPresenter extends IPresenter<IDangersRectificationDetailsView> {
        /**
         * 隐患整改详情
         * @param dangerId
         */
        void queryHiddenDangerDetail(String dangerId);

        /**
         * 拒绝接受
         * @param dangerId
         * @param refuseText
         */
        void refuseHiddenDanger(String dangerId,String refuseText);
    }
}
