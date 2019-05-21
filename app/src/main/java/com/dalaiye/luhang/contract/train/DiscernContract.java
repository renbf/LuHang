package com.dalaiye.luhang.contract.train;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

public interface DiscernContract {
    interface IDiscernView extends IView{
        void faceAuthSuccessful();
    }
    interface IDiscernPresenter extends IPresenter<IDiscernView>{
        void faceAuth(String userId,String fileUrl);
    }
}
