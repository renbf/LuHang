package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.log.LogEndBean;
import com.dalaiye.luhang.bean.log.LogInBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.LogDriveContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/15
 * @function 行车后读取
 **/
public class DriveEndReadPresenter extends BasePresenter<LogDriveContract.IDriveEndReadView>
        implements LogDriveContract.IDriveEndReadPresenter {
    @Override
    public void getLogData(  String logId) {
        OkGo.<String>get(ApiService.GET_LOG_END_BEAN)
                .params("driverLogId", logId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        LogEndBean bean = JSON.parseObject(result, new TypeReference<LogEndBean>() {
                        });
                        getView().setEndData(bean);
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
}
