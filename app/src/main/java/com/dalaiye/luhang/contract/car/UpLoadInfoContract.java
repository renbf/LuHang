package com.dalaiye.luhang.contract.car;

import com.dalaiye.luhang.bean.AcceptanceBean;
import com.dalaiye.luhang.bean.UploadInfoBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/26
 * @function 注释
 **/
public interface UpLoadInfoContract {
    interface IUpLoadInfoView extends IView {
        void setUploadInfo(UploadInfoBean bean);
    }

    interface IUpLoadInfoPresenter extends IPresenter<IUpLoadInfoView>{
        void queryUploadInfo(String dangerId);
    }
}
