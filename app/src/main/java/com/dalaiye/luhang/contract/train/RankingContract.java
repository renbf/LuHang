package com.dalaiye.luhang.contract.train;

import com.dalaiye.luhang.bean.RankingBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public interface RankingContract {

    interface IRankingView extends IView{
        /**
         * 课程排名设置数据
         */
        void setRankingData(RankingBean rankingBean);
    }

    interface IRankingPresenter extends IPresenter<IRankingView> {
        /**
         * 课程排名
         * @param userId
         */
        void getRankingData(String userId);
    }
}
