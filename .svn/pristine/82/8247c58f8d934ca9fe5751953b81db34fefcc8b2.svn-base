package com.dalaiye.luhang.contract.train.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.TipsBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.TrainTipsContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public class TrainTipsPresenter extends BasePresenter<TrainTipsContract.ITrainTipsView>
        implements TrainTipsContract.ITrainTipsPresenter {

    @Override
    public void getTipsData(String userId, int pagerNumber, String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.LITTLE_KNOWLEDGE)
                .params("userId",userId)
                .params("pageNumber",pagerNumber)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        TipsBean tipsBean = JSON.parseObject(result, new TypeReference<TipsBean>() {
                        });
                        getView().setTipsData(tipsBean);
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
