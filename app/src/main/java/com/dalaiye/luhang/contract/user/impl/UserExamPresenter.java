package com.dalaiye.luhang.contract.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dalaiye.luhang.bean.ExamBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.UserExamContract;
import com.gfc.library.mvp.BasePresenter;
import com.gfc.library.utils.net.CustomCallback;
import com.lzy.okgo.OkGo;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 注释
 **/
public class UserExamPresenter extends BasePresenter<UserExamContract.IUserExamView>
        implements UserExamContract.IUserExamPresenter {

    @Override
    public void getUserExamData(String userId, int pagerNumber, String total) { 
        OkGo.<String>get(ApiService.GET_MINE_EXAM)
                .params("userId",userId)
                .params("pageNumber",pagerNumber)
                .params("total",total)
                .execute(new CustomCallback() {
                    @Override
                    protected void success(int code, String message, String result) { 
                        ExamBean examBean = JSON.parseObject(result, new TypeReference<ExamBean>() {
                        });
                        getView().setUserExamData(examBean);
                    }

                    @Override
                    protected void failure(int code, String message, String result) { 
                        getView().toast(code,message);
                    }

                    @Override
                    protected void error(int code, String message) { 
                        getView().toast(code,message);
                    }
                });
    }
}
