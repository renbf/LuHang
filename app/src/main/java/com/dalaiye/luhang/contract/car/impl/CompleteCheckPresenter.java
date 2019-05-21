package com.dalaiye.luhang.contract.car.impl;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.CompleteCheckContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

import java.io.File;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 注释
 **/
public class CompleteCheckPresenter extends BasePresenter<CompleteCheckContract.ICompleteCheckView>
        implements CompleteCheckContract.ICompleteCheckPresenter{

    @Override
    public void completeCheck(String checkId, String checkObj, String file) {
        getView().showLoading();
        OkGo.<String>post(ApiService.COMPLETE_CHECK)
                .params("checkId",checkId)
                .params("checkObj",checkObj)
                .params("file",new File(file),"sign.png")
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().completeCheck(message);
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
