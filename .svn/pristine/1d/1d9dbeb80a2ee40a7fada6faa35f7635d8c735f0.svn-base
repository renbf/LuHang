package com.dalaiye.luhang.contract.train;

import com.dalaiye.luhang.bean.ExamBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public interface ExamContract {

    interface IExamView extends IView {
        /**
         * 设置考试的数据
         * @param examBean
         */
        void setExamData(ExamBean examBean);

        /**
         * 是否开启了认证
         * @param isVerify  0否1是
         */
        void setIsVerifyOn(String isVerify,int postion);
    }

    interface IExamPresenter extends IPresenter<IExamView> {
        /**
         * 获取考试试卷
         *
         * @param userId
         * @param pagerNumber
         * @param total
         */
        void getExamData(String userId, int pagerNumber,String total);

        /**
         * 查询是否开启认证接口
         */
        void queryIsVerifyOn(String businessId,int position);
    }
}
