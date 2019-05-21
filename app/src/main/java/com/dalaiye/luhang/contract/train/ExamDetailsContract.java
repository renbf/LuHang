package com.dalaiye.luhang.contract.train;

import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/19
 * @function 考试详情
 **/
public interface ExamDetailsContract {
    interface IExamDetailsView extends IView {
        /**
         * 考试详情
         */
        void setExamDetails(ExamDetailsBean details);

        /**
         * 交卷成功
         */
        void submitExamSuccess(String score);

        /**
         * 提交问题结果成功
         */
        void submitQuestionSuccess(String answerId,String isTrue);

        /**
         * 收藏成功
         */
        void collectionSuccess();
    }

    interface IExamDetailsPresenter extends IPresenter<IExamDetailsView> {
        /**
         * 获取详情
         *
         * @param userPaperId
         */
        void getExamDetails(String url,String userPaperId);

        /**
         * 提交问题
         *
         * @param userPaperId
         * @param subjectId
         * @param userAnswer
         * @param isTrue
         */
        void submitQuestion(String userPaperId, String subjectId, String userAnswer ,String userSubjectId);

        /**
         * 交卷
         *
         * @param userPaperId 
         */
        void submitExam(String userPaperId);

        /**
         * 收藏考题
         *
         * @param userId
         * @param questionId
         * @param state      Constant
         */
        void collection(String userId, String questionId, int state);
    }
}
