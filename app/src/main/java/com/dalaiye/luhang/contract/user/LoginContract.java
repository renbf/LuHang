package com.dalaiye.luhang.contract.user;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 登录契约类
 **/
public interface LoginContract {

    interface ILoginView extends IView{
        /**
         * 用户登录成功的回调
         * @param userId 用户id
         * @param isAuth 是否认证
         * @param businessId 企业id
         */
        void userLoginSuccessful(String userId,String isAuth,String businessId);
    }

    interface ILoginViewPresenter extends IPresenter<ILoginView>{
        /**
         * 用户登录
         * @param userName
         * @param passWord
         */
        void userLogin(String userName,String passWord);
    }
}
