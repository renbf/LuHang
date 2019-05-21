package com.dalaiye.luhang.contract.train.impl;

import android.util.Log;

import com.dalaiye.luhang.contract.train.BorwseFileContract;
import com.gfc.library.mvp.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;

import java.io.File;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 注释
 **/
public class BorwseFilePresenter extends BasePresenter<BorwseFileContract.IBorwseFileView>
        implements BorwseFileContract.IBorwseFilePresenter {

    @Override
    public void getBorwseFile(String url) {
        getView().showLoading();
        OkGo.<File>get(url)
                .tag(this)
                .execute(new FileCallback() {
                    @Override
                    public void onSuccess(Response<File> response) {
                        getView().hideLoading();
                        getView().setBorwseFile(response.body());
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        Log.i("TAG","失败了"+response);
                        getView().hideLoading();
                        getView().toast(response.code(),response.message());
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                    }
                });
    }
}
