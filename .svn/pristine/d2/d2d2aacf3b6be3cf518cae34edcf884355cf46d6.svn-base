package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.AcceptanceBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.DangersAcceptanceDetailContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 注释
 **/
public class DangersAcceptanceDetailPresenter extends BasePresenter<DangersAcceptanceDetailContract.IDangersAcceptanceDetailView>
        implements DangersAcceptanceDetailContract.IDangersAcceptanceDetailPresenter {

    @Override
    public void queryAcceptanceDetail(String dangerId) {
        getView().showLoading();
        OkGo.<String>get(ApiService.DANGERS_RECTIFICATION_DETAIL)
                .params("dangerId",dangerId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        AcceptanceBean acceptanceBean = JSON.parseObject(result, new TypeReference<AcceptanceBean>() {
                        });
                        getView().queryAcceptanceDetailSuccessful(acceptanceBean);
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
