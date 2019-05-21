package com.dalaiye.luhang.contract.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.MessageBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.UserMessageContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/12
 * @function 注释
 **/
public class UserMessagePresenter extends BasePresenter<UserMessageContract.IUserMessageView>
        implements UserMessageContract.IUserMessagePresenter {

    @Override
    public void queryMessageData(String userId, int position, String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.USER_MESSAGE)
                .params("userId", userId)
                .params("pageNumber", position)
                .params("total", total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        MessageBean messageBean = JSON.parseObject(result, new TypeReference<MessageBean>() {
                        });
                        getView().setMessageData(messageBean);
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

    @Override
    public void readAllMessage(String userId) {
        getView().showLoading();
        OkGo.<String>post(ApiService.READ_ALL_MESSAGE)
                .params("userId",userId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().readAllMsgSuccessful();
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
