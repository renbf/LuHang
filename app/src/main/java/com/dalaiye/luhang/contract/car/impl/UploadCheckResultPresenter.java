package com.dalaiye.luhang.contract.car.impl;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.UploadCheckResultContract;
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
public class UploadCheckResultPresenter extends BasePresenter<UploadCheckResultContract.IUploadCheckResultView>
        implements UploadCheckResultContract.IUploadCheckResultPresenter {

    @Override
    public void closeDangers(String dangerId, String checkAcceptDate, String checkAcceptResult, String remark, List<File> fileList) {
        getView().showLoading();
        OkGo.<String>post(ApiService.CLOSE_DANGERS)
                .params("dangerId",dangerId)
                .params("checkAcceptDate",checkAcceptDate)
                .params("remark",remark)
                .params("checkAcceptResult",checkAcceptResult)
                .addFileParams("files",fileList)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().closeDangersSuccessful(message);
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
