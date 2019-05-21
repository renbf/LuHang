package com.dalaiye.luhang.contract.app.impl;

import android.util.Log;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.app.AppMainContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.gfc.library.utils.user.AccountHelper;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/11
 * @function 启动页面
 **/
public class AppMainPresenter extends BasePresenter<AppMainContract.IAppMainView>
        implements AppMainContract.IAppMainPresenter {
    private static final String TAG = "AppMainPresenter";

    @Override
    public void bindCID(String userId, String cid) {
        OkGo.<String>post(ApiService.ACCOUNT_USER_CID)
                .params("userId", userId)
                .params("clientId", cid)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        
                        Log.d(TAG, "success: " + message);
                    }

                    @Override
                    protected void failure(int code, String message, String result) {
                        Log.d(TAG, "success: " + message);
                    }

                    @Override
                    protected void error(int code, String message) {
                        Log.d(TAG, "success: " + message);
                    }
                });
    }
}
