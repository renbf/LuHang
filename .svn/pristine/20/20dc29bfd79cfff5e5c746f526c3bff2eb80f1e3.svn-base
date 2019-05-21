package com.dalaiye.luhang.contract.train;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 注释
 **/
public interface CourseDetailsContract {

    interface ICourseDetailsView extends IView{
        /**
         * 设置回调
         */
        void setCollection();
        /**
         * 视频总时长成功
         */
        void setUpLoadSuccessful(int duration);
        /**
         * 视频总时长失败
         */
        void setUpLoadFail(int duration);
        /**
         *上传视频开始进度成功
         */
        void upLoadStartTimeSuccessful();
         /**
         * 上传视频开始进度失败
         */
        void upLoadStartTimefail();
    }

    interface ICourseDetailsPresenter extends IPresenter<ICourseDetailsView> {
        /**
         * 添加或取消收藏
         * @param courseId
         * @param isCollect
         */
        void addOrDeleteCollection(String courseId,String isCollect);

        /**
         * 上传视频总进度
         */
        void upLoadProgress(String courseId,int totalTimes);

        /**
         * 上传视频进度接口
         * @param courseId
         * @param progress
         * @param type
         */
        void upLoadStartProgress(String courseId,String progress,int type);
    }
}
