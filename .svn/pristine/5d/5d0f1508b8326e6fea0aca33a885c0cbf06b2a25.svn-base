package com.dalaiye.luhang.contract.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.constant.ApiService;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 注释
 **/
public class UserCoursePresenter extends BasePresenter<com.dalaiye.luhang.contract.user.UserCourseContract.IUserCourseView>
        implements com.dalaiye.luhang.contract.user.UserCourseContract.IUserCoursePresenter {

    @Override
    public void getMyCompleteCourse(String userId, int number, String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.GET_MINE_COURSE)
                .params("userId",userId)
                .params("pageNumber",number)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        CourseBean courseBean = JSON.parseObject(result,new TypeReference<CourseBean>(){});
                        getView().setMyCompleteCourseData(courseBean);
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
