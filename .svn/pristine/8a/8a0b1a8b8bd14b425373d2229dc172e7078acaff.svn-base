package com.dalaiye.luhang.contract.train;

import com.dalaiye.luhang.bean.CourseBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public interface CourseContract {
    interface ICourseView extends IView{
        /**
         * she设置课程安排
         * @param courseBean
         */
        void setCourse(CourseBean courseBean);
    }
    interface ICoursePresenter extends IPresenter<ICourseView> {
        /**
         * 获取课程安排接口
         * @param userId
         * @param position
         * @param total
         */
        void getCourse(String userId,int position,String total);
    }
}
