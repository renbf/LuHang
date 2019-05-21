package com.dalaiye.luhang.contract.user;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/30
 * @function 注释
 **/
public interface QuestionDetailsContract  {
    interface IQuestionDetailsView extends IView{
        /**
         * 收藏成功
         */
        void collectionSuccess();
    }
    
    interface IQuestionDetailsPresenter extends IPresenter<IQuestionDetailsView>{
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
