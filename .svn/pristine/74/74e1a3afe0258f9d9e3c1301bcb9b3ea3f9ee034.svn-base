package com.dalaiye.luhang.contract.user.impl;

import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.QuestionDetailsContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/30
 * @function 注释
 **/
public class QuestionDetailsPresenter extends BasePresenter<QuestionDetailsContract.IQuestionDetailsView>
        implements QuestionDetailsContract.IQuestionDetailsPresenter {
    @Override
    public void collection(String userId, String questionId, int state) {
        if (!isViewAttached()) {
            return;
        }
        getView().showLoading();
        OkGo.<String>get(ApiService.USER_COLLECTION_QUESTION)
                .params("userId", userId)
                .params("subjectId", questionId)
                .params("isCollect", state)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        getView().toast(code, message);
                        getView().collectionSuccess();

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
