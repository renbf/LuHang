package com.gfc.library.utils.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * @author Administrator
 * @date 2018/8/10
 * @function
 */
public abstract class CustomCallback extends StringCallback {

    public static final int REQUEST_SUCCESS = 0;
    public static final int LOGIN_INVALID = 10086;

    public CustomCallback() {
    }


    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public void onSuccess(Response<String> response) {
        try {
            final JSONObject resultObj = JSON.parseObject(response.body());
            final String code = resultObj.getString("status");
            final String message = resultObj.getString("message");
            final String result = resultObj.getString("result");
            if (REQUEST_SUCCESS == Integer.valueOf(code)) {
                success(Integer.valueOf(code), message, result);
            } else {
                failure(Integer.valueOf(code), message, result);
            }
        } catch (JSONException e) {
            failure(-2, "数据解析失败！", null);
        }
    }


    @Override
    public void onError(Response<String> response) {
        error(response.code(), "服务器已经断开连接");
    }

    /**
     * 回参成功
     */
    protected abstract void success(int code, String message, String result);

    /**
     * 回参异常
     */
    protected abstract void failure(int code, String message, String result);

    /**
     * 网络失败
     */
    protected abstract void error(int code, String message);

}

