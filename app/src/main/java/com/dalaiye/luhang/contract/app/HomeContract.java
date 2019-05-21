package com.dalaiye.luhang.contract.app;

import com.dalaiye.luhang.bean.BannerAndNoticeBean;
import com.dalaiye.luhang.bean.IndustryBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public interface HomeContract {
    interface IHomeView extends IView {
        /**
         * 顶部数据
         * @param bannerAndNoticeBean 数据
         */
        void setTopData(BannerAndNoticeBean bannerAndNoticeBean);

        /**
         * 行业动态数据
         *
         * @param industryBean 数据
         */
        void setIndustryData(IndustryBean industryBean);

        /**
         * 设置是否有新消息数据
         * @param isHaveMsg  0否1是
         */
        void setIsHaveMessage(String isHaveMsg);
    }

    interface IHomePresenter extends IPresenter<IHomeView> {
        /**
         * 轮播图以及公告
         *
         * @param userId
         */
        void getTopData(String userId);

        /**
         * 行业动态
         * @param pagerNumber 分页
         */
        void getIndustryData(int pagerNumber);

        /**
         * 获取是否有新消息
         */
        void isHaveMessage(String userId);
    }
}
