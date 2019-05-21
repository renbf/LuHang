package com.dalaiye.luhang.contract.train;

import com.dalaiye.luhang.bean.TipsBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public interface TrainTipsContract {
    
    interface ITrainTipsView extends IView {
        /**
         * 设置小知识数据
         * @param tipsBean
         */
        void setTipsData(TipsBean tipsBean);
    }

    interface ITrainTipsPresenter extends IPresenter<ITrainTipsView> {
        /**
         * 获取小知识数据
         * @param userId
         * @param pagerNumber
         */
        void getTipsData(String userId,int pagerNumber,String total);
    }
}
