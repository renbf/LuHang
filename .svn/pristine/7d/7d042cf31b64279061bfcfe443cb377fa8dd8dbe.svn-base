package com.dalaiye.luhang.contract.car.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.log.LogStartData;
import com.dalaiye.luhang.bean.log.LogStartParams;
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
 * @function 行车前写入
 **/
public class DriveStartWritePresenter extends BasePresenter<LogDriveContract.IDriveStartWriteView>
        implements LogDriveContract.IDriveStartWritePresenter {

    private static final String TAG = "DriveStartWritePresenter";

    @Override
    public void getInitData(String userId) {
        OkGo.<String>get(ApiService.GET_LOG_DRIVE_START)
                .params("userId", userId)
                .tag(TAG)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        LogStartData data = JSON.parseObject(result, new TypeReference<LogStartData>() {
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
    public void submitParams(LogStartParams.LogStartBasisParams basisParams
            , LogStartParams.LogStartDriveParams driveParams, List<String> carPhoto, String sign) {
        if (!isViewAttached()){
            return;
        }
        Log.d(TAG, "basisParams: "+JSON.toJSONString(basisParams));
        Log.d(TAG, "driveParams: "+JSON.toJSONString(driveParams));
        Log.d(TAG, "carPhoto: "+JSON.toJSONString(carPhoto));
        Log.d(TAG, "sign: "+sign);
        getView().showLoading();
        PostRequest<String> request = OkGo.<String>post(ApiService.LOG_ADD_STAT_START)
                .tag(this)
                .params("driverLogJson", JSON.toJSONString(basisParams))
                .params("driverBeforeLogJson", JSON.toJSONString(driveParams))
                .params("file",new File(sign));
        List<File> files = new ArrayList<>();
        for (String s : carPhoto) {
            files.add(new File(s));
        }
        
        if (files.size()>0){
            request.addFileParams("files",files);
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
