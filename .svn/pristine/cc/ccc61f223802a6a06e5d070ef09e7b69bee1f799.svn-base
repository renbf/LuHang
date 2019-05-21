package com.dalaiye.luhang.contract.app.impl;

import com.blankj.utilcode.util.EncodeUtils;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.app.UpDataPwdContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 注释
 **/
public class UpDataPwdPresenter extends BasePresenter<UpDataPwdContract.IUpDataPwdView> implements
        UpDataPwdContract.IUpDataPresenter {

    @Override
    public void updataPwd(String userId, String oldPassword, String newPassword, String confirmPassword) {
        getView().showLoading();
        OkGo.<String>post(ApiService.CHANGE_PASS_WORD)
                .params("userId",userId)
                .params("oldPassword",EncodeUtils.base64Encode2String(oldPassword.getBytes()))
                .params("newPassword",EncodeUtils.base64Encode2String(newPassword.getBytes()))
                .params("confirmPassword",EncodeUtils.base64Encode2String(confirmPassword.getBytes()))
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
