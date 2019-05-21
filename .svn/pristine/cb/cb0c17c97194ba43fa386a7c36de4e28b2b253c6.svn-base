package com.dalaiye.luhang.contract.user;

import com.dalaiye.luhang.bean.CourseBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 注释
 **/
public interface UserCourseContract {

    interface IUserCourseView extends IView{
        /**
         * 设置我已完成的课程
         * @param courseBean
         */
        void setMyCompleteCourseData(CourseBean courseBean);
    }
    interface IUserCoursePresenter extends IPresenter<IUserCourseView> {
        /**
         * 获取我已完成的课程
         * @param userId
         * @param number
         * @param total
         */
       void getMyCompleteCourse(String userId,int number,String total);
    }
}
