package com.dalaiye.luhang.contract.train.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.ExamDetailsContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @date 2019/4/19
 * @function 注释
 **/
public class ExamDetailsPresenter extends BasePresenter<ExamDetailsContract.IExamDetailsView>
        implements ExamDetailsContract.IExamDetailsPresenter {

    private static final String TAG = "ExamDetailsPresenter";

    @Override
    public void getExamDetails(String url,String userPaperId) {
        if (!isViewAttached()) {
            return;
        }
        OkGo.<String>get(url)
                .params("userPaperId", userPaperId)
                .tag(TAG)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        ExamDetailsBean detailsBean = JSON.parseObject(result, new TypeReference<ExamDetailsBean>() {
                        });
                        getView().setExamDetails(detailsBean);
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
    public void submitQuestion(String userPaperId, String subjectId, String userAnswer , String userSubjectId) {

        if (!isViewAttached()) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("userPaperId", userPaperId);
        map.put("userAnswer", userAnswer);
        map.put("subjectId", subjectId);
        map.put("id", userSubjectId); 
        Log.e(TAG, "submitQuestion: "+ System.currentTimeMillis() );
        getView().showLoading();
        OkGo.<String>post(ApiService.EXAM_SUBMIT_QUESTION)
                .params("userSubjectJson", JSON.toJSONString(map))
                .tag(TAG)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        Log.e(TAG, "submitQuestion: "+ System.currentTimeMillis() );
                        String userSubjectId = JSON.parseObject(result).getString("userSubjectId");
                        String isTrue = JSON.parseObject(result).getString("isTrue");
                        getView().hideLoading();
                        getView().submitQuestionSuccess(userSubjectId,isTrue);
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
    public void submitExam(String userPaperId) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        OkGo.<String>post(ApiService.EXAM_SUBMIT_PAPER)
                .params("userPaperId", userPaperId)
                .tag(TAG)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().submitExamSuccess(JSON.parseObject(result).getString("paperScore"));
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
    public void collection(String userId, String questionId, int state) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        OkGo.<String>get(ApiService.USER_COLLECTION_QUESTION)
                .params("userId", userId)
                .params("subjectId", questionId)
                .params("isCollect", state)
                .tag(TAG)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().toast(code, message);
                        getView().collectionSuccess();

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
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(TAG);
    }
}
