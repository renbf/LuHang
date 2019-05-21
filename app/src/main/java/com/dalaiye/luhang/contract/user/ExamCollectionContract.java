package com.dalaiye.luhang.contract.user;

import com.dalaiye.luhang.bean.ExamBean;
import com.dalaiye.luhang.bean.ExamCollectionBean;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 注释
 **/
public interface ExamCollectionContract {
    interface IExamCollectionView extends IView{
        /**
         * 设置考题收藏数据
         */
        void setExamCollectionData(ExamCollectionBean bean);
    }
    interface IExamCollectionPresenter extends IPresenter<IExamCollectionView> {
        /**
         * 获取我的考题收藏接口
         * @param userId 用id
         * @param pageNumber 分页
         */
        void getExamCollectionData(String userId,int pageNumber,int total);
    }
}
