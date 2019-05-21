package com.dalaiye.luhang.contract.app;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/12
 * @function 注释
 **/
public interface AppSearchRequestContract {
    interface IAppSearchRequestView extends IView {
        void setSearchHistory(List<String> list);
        void historyDeleteSuccess();
    }

    interface IAppSearchRequestPresenter extends IPresenter<IAppSearchRequestView> {
        void querySearchHistory(String userId);
        void addSearchHistory(String content);
        void deleteHistory(String userId);
    }
}
