package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.UpLoadDangersDefaultInfoBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.UpLoadDangersContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

import java.io.File;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 注释
 **/
public class UpLoadDangersPresenter extends BasePresenter<UpLoadDangersContract.IUpLoadDangersView>
        implements UpLoadDangersContract.IUpLoadDangerPresenter{

    @Override
    public void getInitData(String businessId) {
        getView().showLoading();
        OkGo.<String>get(ApiService.QUERY_DEFAULT_TEAM)
                .params("businessId",businessId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        UpLoadDangersDefaultInfoBean upLoadDangersDefaultInfoBean = JSON.parseObject(result, new TypeReference<UpLoadDangersDefaultInfoBean>() {
                        });
                        getView().setInitData(upLoadDangersDefaultInfoBean);
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

    @Override
    public void upLoad(String inspectPlanId, String dangerJson,List<File> fileList) {
        getView().showLoading();
        OkGo.<String>post(ApiService.UPLOAD_HIDDEN_DANGER)
                .params("inspectPlanId",inspectPlanId)
                .params("dangerJson",dangerJson)
                .addFileParams("photoFile",fileList)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().upLoadSuccessful(message);
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
