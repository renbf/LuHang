package com.dalaiye.luhang.contract.train.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.ExamBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.ExamContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public class ExamPresenter extends BasePresenter<ExamContract.IExamView> implements
        ExamContract.IExamPresenter {

    @Override
    public void getExamData(String userId, int pagerNumber, String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.TRAINED_EXAM_PAPER)
                .params("userId",userId)
                .params("pageNumber",pagerNumber)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        ExamBean examBean = JSON.parseObject(result, new TypeReference<ExamBean>() {
                        });
                        getView().setExamData(examBean);
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
    public void queryIsVerifyOn(String businessId,int postion) {
        getView().showLoading();
        OkGo.<String>get(ApiService.IS_VERIFYON)
                .params("businessId",businessId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        String isVerify = JSON.parseObject(result).getString("isVerify");
                        getView().setIsVerifyOn(isVerify,postion);
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
