package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.DangersRectificationBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.DangersRectificationContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public class DangersRectificationPresenter extends BasePresenter<DangersRectificationContract.IDangersRectificationView> implements
        DangersRectificationContract.IDangersRectificationPresenter {

    @Override
    public void queryDangersRectification(String userId, int pageNumber, String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.DANGERS_RECTIFICATION)
                .params("userId",userId)
                .params("pageNumber",pageNumber)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        DangersRectificationBean dangersRectificationBean = JSON.parseObject(result, new TypeReference<DangersRectificationBean>() {
                        });
                        getView().setDangersRectification(dangersRectificationBean);

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
