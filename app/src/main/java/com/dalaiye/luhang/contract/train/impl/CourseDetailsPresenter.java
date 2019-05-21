package com.dalaiye.luhang.contract.train.impl;

import android.util.Log;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.CourseDetailsContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 课程详情的presenter
 **/
public class CourseDetailsPresenter extends BasePresenter<CourseDetailsContract.ICourseDetailsView>
        implements CourseDetailsContract.ICourseDetailsPresenter {

    @Override
    public void addOrDeleteCollection(String courseId, String isCollect) {
        OkGo.<String>get(ApiService.VIDEO_COLLECTION)
                .params("userCourseId", courseId)
                .params("isCollect", isCollect)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().setCollection();
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
    public void upLoadProgress(String courseId, int totalTimes) {
        OkGo.<String>post(ApiService.SEND_VIDEO_TOTAL_TIMES)
                .params("courseId", courseId)
                .params("totalTimes", String.valueOf(totalTimes))
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().setUpLoadSuccessful(totalTimes);
                    }

                    @Override
                    protected void failure(int code, String message, String result) {
                        getView().setUpLoadFail(totalTimes);
                    }

                    @Override
                    protected void error(int code, String message) {
                        getView().setUpLoadFail(totalTimes);
                    }
                });
    }

    @Override
    public void upLoadStartProgress(String courseId, String progress, int type) {
        OkGo.<String>post(ApiService.UPLOAD_VIDEO_PROGRESS)
                .params("userCourseId", courseId)
                .params("type", type)
                .params("progress", progress)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        Log.i("tag", result);
                    }

                    @Override
                    protected void failure(int code, String message, String result) {
                        getView().upLoadStartTimefail();
                    }

                    @Override
                    protected void error(int code, String message) {
                        getView().upLoadStartTimefail();
                    }
                });
    }
}
