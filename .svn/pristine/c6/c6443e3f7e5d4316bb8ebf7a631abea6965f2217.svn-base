package com.dalaiye.luhang.contract.user;

import com.dalaiye.luhang.bean.CourseBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 注释
 **/
public interface CourseCollectionContract {
    interface ICourseCollectionView extends IView{
        /**
         * 设置收藏的数据
         */
        void setCollectionData(CourseBean courseBean);
    }
    interface ICourseCollectionPresenter extends IPresenter<ICourseCollectionView>{
        /**
         * 课程收藏接口
         */
        void courseCollection(String userId,int pageNumber,String total);
    }
}
