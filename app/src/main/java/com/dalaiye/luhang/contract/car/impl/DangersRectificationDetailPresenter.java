package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.RectificationBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.DangersRectificationDetailsContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 注释
 **/
public class DangersRectificationDetailPresenter extends BasePresenter<DangersRectificationDetailsContract.IDangersRectificationDetailsView>
        implements DangersRectificationDetailsContract.IDangersRectificationDetailsPresenter {

    @Override
    public void queryHiddenDangerDetail(String dangerId) {
        getView().showLoading();
        OkGo.<String>get(ApiService.DANGERS_RECTIFICATION_DETAIL)
                .params("dangerId",dangerId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        RectificationBean rectificationBean = JSON.parseObject(result, new TypeReference<RectificationBean>() {
                        });
                        getView().queryHiddenDangerDetailSuccessful(rectificationBean);
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
    public void refuseHiddenDanger(String dangerId, String refuseText) {
        getView().showLoading();
        OkGo.<String>post(ApiService.REFUDE_HIDDEN_DANGER)
                .params("dangerId",dangerId)
                .params("refuseText",refuseText)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().refuseHiddenDangerSuccessful(message);
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
