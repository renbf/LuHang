package com.dalaiye.luhang.contract.app;

import com.dalaiye.luhang.bean.CourseBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/12
 * @function 注释
 **/
public interface AppSearchResultContract {
    interface IAppSearchResultView extends IView {
        /**
         * 设置搜索页面回调
         * @param courseBean
         */
        void setSearchResult(CourseBean courseBean);

    }

    interface IAppSearchResultPresenter extends IPresenter<IAppSearchResultView> {
        /**
         * 搜索页面接口
         * @param userId
         * @param content
         * @param currentPage
         * @param total
         */
        void querySearchResult(String userId, String content, int currentPage,String total);
    }
}
