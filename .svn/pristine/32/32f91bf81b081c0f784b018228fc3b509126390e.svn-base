package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.log.LogBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/11
 * @function 检查隐患
 **/
public interface LogContract {
    interface ILogView extends IView {
        /**
         * 设置数据
         * @param logData
         */
        void setLogData(LogBean logData);
    }

    interface ILogPresenter extends IPresenter<ILogView> {
        /**
         * 获取日志信息
         * @param userId
         * @param pagerNumber
         */
        void getLogData(String userId,int totalPage,int pagerNumber);
    }
}
