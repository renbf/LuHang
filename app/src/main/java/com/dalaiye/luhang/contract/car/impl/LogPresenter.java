package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.log.LogBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.LogContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/11
 * @function 行车日志
 **/
public class LogPresenter extends BasePresenter<LogContract.ILogView> implements LogContract.ILogPresenter {
    private static final String TAG = "LogPresenter";

    @Override
    public void getLogData(String userId, int totalPage, int pagerNumber) {
        if (!isViewAttached()) {
            return;
        }
        OkGo.<String>get(ApiService.GET_DRIVE_LOG)
                .params("userId", userId)
                .params("total", String.valueOf(totalPage))
                .params("pageNumber", String.valueOf(pagerNumber))
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        LogBean logBean = JSON.parseObject(result, new TypeReference<LogBean>() {
                        });
                        getView().setLogData(logBean);
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
    public void onDestroy() {
        OkGo.getInstance().cancelTag(TAG);
    }
}
