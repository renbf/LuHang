package com.dalaiye.luhang.contract.car.impl;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.DangersAccpetRectificationContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

import java.io.File;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 注释
 **/
public class IDangersAccpetRectificationPresenter extends BasePresenter<DangersAccpetRectificationContract.IDangersAccpetRectificationView>
        implements DangersAccpetRectificationContract.IDangersAccpetRectificationPresenter {


    @Override
    public void rectificationSubmit(String id, String dochangeStep, String dochangeCapital, List<File> fileList) {
        getView().showLoading();
        OkGo.<String>post(ApiService.RECTIFICATION_SUBMIT)
                .params("dangerId",id)
                .params("dochangeStep",dochangeStep)
                .params("dochangeCapital",dochangeCapital)
                .addFileParams("files",fileList)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().rectificationSubmitSuccessful(message);
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
