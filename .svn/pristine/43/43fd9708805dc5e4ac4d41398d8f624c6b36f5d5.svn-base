package com.dalaiye.luhang.contract.train;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public interface TrainContract {
    interface ITrainView extends IView{
        /**
         * 上传进度成功
         */
        void upLoadProgressSuccessful();
    }
    interface ITrainPresenter extends IPresenter<ITrainView>{
        /**
         * 上传视频进度接口
         * @param courseId
         * @param progress
         * @param type
         */
        void upLoadProgress(String courseId,String progress,int type);
    }
}
