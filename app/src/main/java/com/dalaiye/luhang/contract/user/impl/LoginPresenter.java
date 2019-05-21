package com.dalaiye.luhang.contract.user.impl;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.EncodeUtils;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.LoginContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 登录presenter
 **/
public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements
        LoginContract.ILoginViewPresenter {
    @Override
    public void userLogin(String userName, String passWord) {
        getView().showLoading();
        OkGo.<String>post(ApiService.ACCOUNT_LOGIN)
                .params("userName",userName)
                .params("passWord",EncodeUtils.base64Encode2String(passWord.getBytes()))
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        String userId = JSON.parseObject(result).getString("userId");
                        String isAuth = JSON.parseObject(result).getString("isAuth");
                        String businessId = JSON.parseObject(result).getString("businessId");
                        getView().userLoginSuccessful(userId,isAuth,businessId);
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
