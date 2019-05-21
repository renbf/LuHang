package com.dalaiye.luhang.contract.app.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.app.AppSearchResultContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author admin
 * @date 2019/4/12
 * @function 搜索结果
 **/
public class AppSearchResultPresenter extends BasePresenter<AppSearchResultContract.IAppSearchResultView>
        implements AppSearchResultContract.IAppSearchResultPresenter {

    @Override
    public void querySearchResult(String userId, String content, int currentPage,String total) {
        getView().showLoading();
        OkGo.<String>get(ApiService.COURSE_SEARCH)
                .params("userId",userId)
                .params("searchKey",content)
                .params("pageNumber",currentPage)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) {
                        getView().hideLoading();
                        CourseBean bean = JSON.parseObject(result,new TypeReference<CourseBean>(){});
                        getView().setSearchResult(bean);
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
