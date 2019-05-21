package com.dalaiye.luhang.contract.train.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.CourseContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public class CoursePresenter extends BasePresenter<CourseContract.ICourseView> implements
        CourseContract.ICoursePresenter {

    @Override
    public void getCourse(String userId, int position,String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.TRAINED_COURSE_ARRANGE)
                .params("userId",userId)
                .params("pageNumber",position)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        CourseBean courseBean = JSON.parseObject(result, new TypeReference<CourseBean>() {
                        });
                        getView().setCourse(courseBean);
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


//        List<CourseBean> list = new ArrayList<>();
//        CourseBean courseBean = new CourseBean();
//        courseBean.setImg("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2052097634,3612453277&fm=26&gp=0.jpg");
//        courseBean.setType(2);
//        courseBean.setName("这是一个带文件的视频");
//        courseBean.setIntroduce("简介简介简介简介简介简介简介简介简介简介简介简介");
//        courseBean.setVideoAddress("视频地址");
//        courseBean.setFileAddress("文件地址");
//        courseBean.setIsCollection("2");
//        courseBean.setProgress("6.66");
//        courseBean.setTime("2019-4-11 11:11");
//        CourseBean courseBean1 = new CourseBean();
//        courseBean1.setImg("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2052097634,3612453277&fm=26&gp=0.jpg");
//        courseBean1.setType(1);
//        courseBean1.setName("这是文件");
//        courseBean1.setTime("2019-4-11 22:22");
//        courseBean.setIntroduce("简介简介简介简介简介简介简介简介简介简介简介简介");
//        courseBean.setVideoAddress("视频地址");
//        courseBean.setFileAddress("文件地址");
//        courseBean.setIsCollection("0");
//        courseBean.setProgress("6.66");
//        CourseBean courseBean2 = new CourseBean();
//        courseBean2.setImg("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2052097634,3612453277&fm=26&gp=0.jpg");
//        courseBean2.setType(0);
//        courseBean2.setName("这是视频");
//        courseBean2.setIntroduce("简介简介简介简介简介简介简介简介简介简介简介简介");
//        courseBean2.setVideoAddress("视频地址");
//        courseBean.setFileAddress("文件地址");
//        courseBean2.setIsCollection("0");
//        courseBean2.setProgress("6.66");
//        courseBean2.setTime("2019-4-11 33:33");
//        list.add(courseBean);
//        list.add(courseBean1);
//        list.add(courseBean2);
//        list.add(courseBean);
//        list.add(courseBean1);
//        list.add(courseBean2);
//        list.add(courseBean);
//        list.add(courseBean1);
//        list.add(courseBean2);
//        getView().setCourse(list);
    }
}
