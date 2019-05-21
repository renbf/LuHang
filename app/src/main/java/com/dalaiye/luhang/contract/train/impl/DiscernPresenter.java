package com.dalaiye.luhang.contract.train.impl;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.DiscernContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

import java.io.File;

public class DiscernPresenter extends BasePresenter<DiscernContract.IDiscernView> implements
        DiscernContract.IDiscernPresenter {

    @Override
    public void faceAuth(String userId, String fileUrl) {
        getView().showLoading();
        OkGo.<String>post(ApiService.FACE_AUTHEN)
                .params("userId",userId)
                .params("file",new File(fileUrl),"file.png")
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().toast(code,message);
                        getView().faceAuthSuccessful();
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
