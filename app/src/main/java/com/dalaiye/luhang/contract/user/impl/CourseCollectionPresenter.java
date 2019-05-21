package com.dalaiye.luhang.contract.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.CourseCollectionContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 注释
 **/
public class CourseCollectionPresenter extends BasePresenter<CourseCollectionContract.ICourseCollectionView>
        implements CourseCollectionContract.ICourseCollectionPresenter {

    @Override
    public void courseCollection(String userId, int pageNumber, String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.COURSE_COLLECTION)
                .params("userId",userId)
                .params("pageNumber",pageNumber)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        CourseBean courseBean = JSON.parseObject(result, new TypeReference<CourseBean>() {});
                        getView().setCollectionData(courseBean);
                        getView().hideLoading();
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
