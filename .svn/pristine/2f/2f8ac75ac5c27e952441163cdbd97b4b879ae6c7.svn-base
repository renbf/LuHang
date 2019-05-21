package com.dalaiye.luhang.contract.user;

import com.dalaiye.luhang.bean.MessageBean;
import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author admin
 * @date 2019/4/12
 * @function 注释
 **/
public interface UserMessageContract {
    interface IUserMessageView extends IView {
        /**
         * 设置信息数据
         * @param messageBean
         */
        void setMessageData(MessageBean messageBean);

        /**
         * 消息全部已读成功返回
         */
        void readAllMsgSuccessful();
    }

    interface IUserMessagePresenter extends IPresenter<IUserMessageView> {
        /**
         * 查询我的信息
         * @param userId
         * @param position
         * @param total
         */
        void queryMessageData(String userId, int position,String total);

        /**
         * 消息全部已读
         * @param userId
         */
        void readAllMessage(String userId);
    }
}
