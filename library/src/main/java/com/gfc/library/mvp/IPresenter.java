package com.gfc.library.mvp;

import android.os.Bundle;

/**
 * @author 王文波
 * @date 2019/1/17
 * @function 功能
 */
public interface IPresenter<V extends IView> {

    void onAttachView(V view, Bundle savedInstanceState);

    void onDetachView();

    void onDestroy();
}
