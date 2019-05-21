package com.dalaiye.luhang.contract.user;

import com.dalaiye.luhang.bean.UserBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public interface UserCenterContract {
    interface IUserCenterView extends IView{
        /**
         * 设置是否有新消息数据
         * @param isHaveMsg  0否1是
         */
        void setIsHaveMessage(String isHaveMsg);
        /**
         * 设置用户信息
         * @param userBean
         */
         void setUserInfo(UserBean userBean);
        
    }
     interface IUserCenterPresenter extends IPresenter<IUserCenterView> {
         /**
          * 获取是否有新消息
          */
         void isHaveMessage(String userId);

         /**
          * 查询用户信息
          * @param userId
          */
        void queryUserInfo(String userId);
    }
}
