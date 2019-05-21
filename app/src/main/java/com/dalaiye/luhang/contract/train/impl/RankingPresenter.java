package com.dalaiye.luhang.contract.train.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.RankingBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.RankingContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public class RankingPresenter extends BasePresenter<RankingContract.IRankingView> implements
        RankingContract.IRankingPresenter {

    @Override
    public void getRankingData(String userId) {
        getView().showLoading();
        OkGo.<String>get(ApiService.TRAINED_COURSE_RANKING)
                .params("userId", userId)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        RankingBean rankingBean = JSON.parseObject(result, new TypeReference<RankingBean>() {
                        });
                        getView().setRankingData(rankingBean);
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
