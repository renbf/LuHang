package com.dalaiye.luhang.contract.app;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 注释
 **/
public interface UpDataPwdContract {
    interface IUpDataPwdView extends IView{
        /**
         * 修改密码成功返回
         */
        void updataPwdSuccessful();
    }
    interface IUpDataPresenter extends IPresenter<IUpDataPwdView> {
        /**
         * 修改密码
         * @param userId
         * @param oldPassword
         * @param newPassword
         * @param confirmPassword
         */
        void updataPwd(String userId,String oldPassword,String newPassword,String confirmPassword);
    }
}
