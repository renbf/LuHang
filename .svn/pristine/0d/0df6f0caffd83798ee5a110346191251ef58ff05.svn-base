package com.dalaiye.luhang.contract.app.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.BannerAndNoticeBean;
import com.dalaiye.luhang.bean.IndustryBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.app.HomeContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/11
 * @function 首页
 **/
public class HomePresenter extends BasePresenter<HomeContract.IHomeView>
        implements HomeContract.IHomePresenter {
    @Override
    public void getTopData(String userId) {
        getView().showLoading();
        OkGo.<String>get(ApiService.BANNER_AND_NOTICE)
                .params("userId",userId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        BannerAndNoticeBean bannerAndNoticeBean = JSON.parseObject(result, new TypeReference<BannerAndNoticeBean>() {});
                        getView().setTopData(bannerAndNoticeBean);
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
    public void getIndustryData(int pagerNumber) {
        getView().showLoading();
        OkGo.<String>get(ApiService.INDUSTRY_DYNAMIC)
                .params("pageNumber",pagerNumber)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        IndustryBean industryBean = JSON.parseObject(result, new TypeReference<IndustryBean>() {
                        });
                        getView().setIndustryData(industryBean);
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

    @Override
    public void isHaveMessage(String userId) {
        OkGo.<String>get(ApiService.IS_HAVE_MESSAGE)
                .params("userId",userId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        String isHaveMsg = JSON.parseObject(result).getString("isHaveMsg");
                        getView().setIsHaveMessage(isHaveMsg);
                    }

                    @Override
                    protected void failure(int code, String message, String result) {

                    }

                    @Override
                    protected void error(int code, String message) {

                    }
                });
    }
}
