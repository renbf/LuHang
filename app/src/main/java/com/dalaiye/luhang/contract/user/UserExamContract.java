package com.dalaiye.luhang.contract.user;

import com.dalaiye.luhang.bean.ExamBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 注释
 **/
public interface UserExamContract {
    interface IUserExamView extends IView{
        void setUserExamData(ExamBean bean);
    }

    interface IUserExamPresenter extends IPresenter<IUserExamView>{
        /**
         * 获取我的试卷接口
         * @param userId
         * @param pagerNumber
         * @param total
         */
        void getUserExamData(String userId,int pagerNumber,String total);
    }
}
