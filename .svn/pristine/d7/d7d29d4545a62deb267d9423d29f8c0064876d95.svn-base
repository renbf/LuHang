package com.dalaiye.luhang.contract.industry.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.IndustryBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.industry.IndustryContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public class IndustryPresenter extends BasePresenter<IndustryContract.IIndustryView>
        implements IndustryContract.IIndustryPresenter {

    @Override
    public void queryIndustryData(int pageNumber, String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.INDUSTRY_DYNAMIC)
                .params("pageNumber",pageNumber)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        Log.i("getIndustryData",result);
                        IndustryBean industryBean = JSON.parseObject(result, new TypeReference<IndustryBean>() {
                        });
                        getView().setIndustryData(industryBean);
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
