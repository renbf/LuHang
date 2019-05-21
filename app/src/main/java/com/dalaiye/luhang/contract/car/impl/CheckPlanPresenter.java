package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.CheckPlanBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.CheckPlanContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public class CheckPlanPresenter extends BasePresenter<CheckPlanContract.ICheckPlanView> implements
        CheckPlanContract.ICheckPlanPresenter {

    @Override
    public void queryCheckPlan(String userId, int pagerNumber, String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.CHECK_PLAN)
                .params("userId",userId)
                .params("pageNumber",pagerNumber)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        CheckPlanBean checkPlanBean = JSON.parseObject(result, new TypeReference<CheckPlanBean>() {
                        });
                        getView().setCheckPlanData(checkPlanBean);
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
