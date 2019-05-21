package com.dalaiye.luhang.contract.train.impl;

import android.util.Log;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.TrainContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/11
 * @function 培训
 **/
public class TrainPresenter extends BasePresenter<TrainContract.ITrainView>
        implements TrainContract.ITrainPresenter {
    @Override
    public void upLoadProgress(String userCourseId, String progress,int type) {
        OkGo.<String>post(ApiService.UPLOAD_VIDEO_PROGRESS)
                .params("userCourseId",userCourseId)
                .params("type",type)
                .params("progress",progress)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        Log.i("tag",result);
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
