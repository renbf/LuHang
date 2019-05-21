package com.dalaiye.luhang.contract.user.impl;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.AuthContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

import java.io.File;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 认证presenter
 **/
public class AuthPresenter extends BasePresenter<AuthContract.IAuthView> implements
        AuthContract.IAuthPresenter{

    @Override
    public void uploadAuthPhoto(String userId, String authImg) {
        getView().showLoading();
        OkGo.<String>post(ApiService.UPLOAD_AUTH_IMG)
                .params("userId",userId)
                .params("authImg",new File(authImg), "auth.png")
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().toast(code,message);

                    }

                    @Override
                    protected void failure(int code, String message, String result) {
                        getView().hideLoading();
                        getView().toast(code,message);
                    }

                    @Override
                    protected void error(int code, String message) {
                        getView().hideLoading();
                        getView().toast(code,message);
                    }
                });
    }
}
