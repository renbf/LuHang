package com.dalaiye.luhang.contract.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.ExamCollectionBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.ExamCollectionContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 注释
 **/
public class ExamCollectionPresenter extends BasePresenter<ExamCollectionContract.IExamCollectionView>
        implements ExamCollectionContract.IExamCollectionPresenter {


    @Override
    public void getExamCollectionData(String userId, int pageNumber, int total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.EXAM_COLLECTION)
                .params("userId", userId)
                .params("pageNumber", String.valueOf(pageNumber))
                .params("total", String.valueOf(total))
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        ExamCollectionBean bean = JSON.parseObject(result, new TypeReference<ExamCollectionBean>() {
                        });
                        getView().setExamCollectionData(bean);
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
}
