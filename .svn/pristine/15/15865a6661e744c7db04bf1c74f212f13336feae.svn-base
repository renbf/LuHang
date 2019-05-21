package com.dalaiye.luhang.contract.car.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.CheckProjectBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.car.CheckProjectContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 注释
 **/
public class CheckProjectPresenter extends BasePresenter<CheckProjectContract.ICheckProjectView>
        implements CheckProjectContract.ICheckProjectPresenter {

    @Override
    public void queryCheckProject(String inspectPlanId, String projectId) {
        getView().showLoading();
        OkGo.<String>get(ApiService.QUERY_CHECK_PROJECT)
                .params("inspectPlanId",inspectPlanId)
                .params("projectId",projectId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        List<CheckProjectBean> checkProjectBeans = JSON.parseObject(result, new TypeReference<List<CheckProjectBean>>() {
                        });
                        getView().setCheckProject(checkProjectBeans);
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
    public void saveCheckProject(String json) {
        getView().showLoading();
        OkGo.<String>post(ApiService.SAVE_CHECK_PROJECT)
                .params("inspectTeamProjectJson",json)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().saveCheckProjectSuccessful();
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
