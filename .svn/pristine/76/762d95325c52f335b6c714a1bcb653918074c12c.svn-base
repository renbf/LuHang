package com.dalaiye.luhang.contract.car;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.io.File;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 注释
 **/
public interface UploadCheckResultContract {

    interface IUploadCheckResultView extends IView{
        /**
         * 关闭隐患成功返回
         * @param message
         */
        void closeDangersSuccessful(String message);
    }

    interface IUploadCheckResultPresenter extends IPresenter<IUploadCheckResultView> {
        /**
         * 关闭隐患，重新整改接口
         * @param dangerId
         * @param checkAcceptDate
         * @param checkAcceptResult
         * @param remark
         * @param fileList
         */
        void closeDangers(String dangerId, String checkAcceptDate, String checkAcceptResult, String remark, List<File> fileList);
    }
}
