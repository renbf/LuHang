package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.log.LogEndBean;
import com.dalaiye.luhang.bean.log.LogEndData;
import com.dalaiye.luhang.bean.log.LogEndParams;
import com.dalaiye.luhang.bean.log.LogInBean;
import com.dalaiye.luhang.bean.log.LogInData;
import com.dalaiye.luhang.bean.log.LogInParams;
import com.dalaiye.luhang.bean.log.LogStartBean;
import com.dalaiye.luhang.bean.log.LogStartData;
import com.dalaiye.luhang.bean.log.LogStartParams;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.io.File;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/15
 * @function 行车日志
 **/
public interface LogDriveContract {


    //=========================================行车前================================================

    /**
     * 行车前写入 view
     */
    interface IDriveStartWriteView extends IView {
        void setInitData(LogStartData startData);

        void submitParamsSuccess();
    }

    /**
     * 行车前写入 presenter
     */
    interface IDriveStartWritePresenter extends IPresenter<IDriveStartWriteView> {
        void getInitData(String userId);

        void submitParams(LogStartParams.LogStartBasisParams basisParams, LogStartParams.LogStartDriveParams driveParams,
                          List<String> carPhoto, String sign);
    }

    /**
     * 行车前读取 view
     */
    interface IDriveStartReadView extends IView {
        void setLogData(LogStartBean startBean);
    }

    /**
     * 行车前读取 presenter
     */
    interface IDriveStartReadPresenter extends IPresenter<IDriveStartReadView> {
        void getLogData(String logId);
    }


    //=========================================行车中================================================

    /**
     * 行车中写入 view
     */
    interface IDriveInWriteView extends IView {

        void setInitData(LogInData initData);

        void submitParamsSuccess();
    }

    /**
     * 行车中写入 presenter
     */
    interface IDriveInWritePresenter extends IPresenter<IDriveInWriteView> {

        void getInitData(String userId);

        void handover(String logId, String remark);

        void submitParams(LogInParams params, List<String> carPhoto, String sign);
    }

    /**
     * 行车中读取 view
     */
    interface IDriveInReadView extends IView {
        void setLogData(LogInBean inBean);
    }

    /**
     * 行车中读取 presenter
     */
    interface IDriveInReadPresenter extends IPresenter<IDriveInReadView> {
        void getLogData(String logId);
    }


    //=========================================行车后================================================

    /**
     * 行车后写入 view
     */
    interface IDriveEndWriteView extends IView {

        void setInitData(LogEndData endData);

        void submitParamsSuccess();
    }

    /**
     * 行车后写入 presenter
     */
    interface IDriveEndWritePresenter extends IPresenter<IDriveEndWriteView> {

        void getInitDate(String userId);

        void submitParams(LogEndParams params, List<String> carPhoto, String sign);
    }

    /**
     * 行车后读取 view
     */
    interface IDriveEndReadView extends IView {
        void setEndData(LogEndBean endData);
    }

    /**
     * 行车后读取 presenter
     */
    interface IDriveEndReadPresenter extends IPresenter<IDriveEndReadView> {
        void getLogData(String logId);
    }

}
