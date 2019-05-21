package com.dalaiye.luhang.contract.user;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public interface AuthContract {
    interface IAuthView extends IView{
        /**
         * 上传用户头像成功
         */
        void uploadAuthPhotoSuccessful();
    }

    interface IAuthPresenter extends IPresenter<IAuthView> {
        /**
         * 用户认证上传图片
         * @param userId
         * @param authImg
         */
        void uploadAuthPhoto(String userId,String authImg);
    }
}
