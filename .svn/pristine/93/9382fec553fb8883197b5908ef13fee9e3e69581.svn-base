package com.dalaiye.luhang.contract.train;

import com.gfc.library.mvp.IPresenter;
import com.gfc.library.mvp.IView;

import java.io.File;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 注释
 **/
public interface BorwseFileContract {
    interface IBorwseFileView extends IView{
        void setBorwseFile(File file);
        void setBorwseFileFailure();
    }
    interface IBorwseFilePresenter extends IPresenter<IBorwseFileView>{
        void getBorwseFile(String url);
    }
}
