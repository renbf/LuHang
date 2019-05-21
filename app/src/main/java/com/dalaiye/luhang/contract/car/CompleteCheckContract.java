package com.dalaiye.luhang.contract.car;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 注释
 **/
public interface CompleteCheckContract {
    interface ICompleteCheckView extends IView{
        /**
         * 完成检查提示信息
         * @param message
         */
        void completeCheck(String message);
    }
    interface ICompleteCheckPresenter extends IPresenter<ICompleteCheckView> {
        /**
         * 完成检查
         * @param checkId 检查id
         * @param checkObj 检查对象
         * @param file 图片
         */
        void completeCheck(String checkId,String checkObj,String file);
    }
}
