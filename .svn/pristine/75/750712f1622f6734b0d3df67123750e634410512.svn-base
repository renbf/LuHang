package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.log.LogEndData;
import com.dalaiye.luhang.bean.log.LogEndParams;
import com.dalaiye.luhang.bean.log.LogInData;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.LogDriveContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/15
 * @function 行车后写入
 **/
public class DriveEndWritePresenter extends BasePresenter<LogDriveContract.IDriveEndWriteView>
        implements LogDriveContract.IDriveEndWritePresenter {

    @Override
    public void getInitDate(String userId) {
        if (!isViewAttached()) {
            return;
        }
        OkGo.<String>get(ApiService.GET_LOG_DRIVE_END)
                .params("userId", userId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        LogEndData data = JSON.parseObject(result, new TypeReference<LogEndData>() {
                        });
                        getView().setInitData(data);
                    }

                    @Override
                    protected void failure(int code, String message, String result) {
                        getView().toast(code, message);
                    }

                    @Override
                    protected void error(int code, String message) {
                        getView().toast(code, message);
                    }
                });
    }

    @Override
    public void submitParams(LogEndParams params, List<String> carPhoto, String sign) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        PostRequest<String> request = OkGo.<String>post(ApiService.LOG_ADD_STAT_END)
                .tag(this)
                .params("driverLogJson", JSON.toJSONString(params))
                .params("file", new File(sign));
        List<File> files = new ArrayList<>();
        for (String s : carPhoto) {
            files.add(new File(s));
        }

        if (files.size() > 0) {
            request.addFileParams("files", files);
        }

        request.execute(new CustomCallback() {
            @Override
            protected void success(int code, String message, String result) {
                getView().hideLoading();
                getView().toast(code, message);
                getView().submitParamsSuccess();
            }

            @Override
            protected void failure(int code, String message, String result) {
                getView().hideLoading();
                getView().toast(code, message);
            }

            @Override
            protected void error(int code, String message) {
                getView().hideLoading();
                getView().toast(code, message);
            }
        });
    }
 
}
