package com.dalaiye.luhang.contract.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.UserBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.UserCenterContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/11
 * @function 用户中心
 **/
public class UserCenterPresenter extends BasePresenter<UserCenterContract.IUserCenterView>
        implements UserCenterContract.IUserCenterPresenter {

    @Override
    public void isHaveMessage(String userId) {
        OkGo.<String>get(ApiService.IS_HAVE_MESSAGE)
                .params("userId",userId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        String isHaveMsg = JSON.parseObject(result).getString("isHaveMsg");
                        getView().setIsHaveMessage(isHaveMsg);
                    }

                    @Override
                    protected void failure(int code, String message, String result) {

                    }

                    @Override
                    protected void error(int code, String message) {

                    }
                });
    }

    @Override
    public void queryUserInfo(String userId) {
        getView().showLoading();
        OkGo.<String>get(ApiService.GET_USER_INFO)
                .params("userId",userId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        UserBean userBean = JSON.parseObject(result, new TypeReference<UserBean>() {
                        });
                        getView().setUserInfo(userBean);
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
