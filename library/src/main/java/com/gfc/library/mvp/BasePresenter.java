package com.gfc.library.mvp;

import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * @author 王文波
 * @date 2019/1/17
 * @function 功能
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> viewRef;

    protected V getView() {
        return viewRef.get();
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    private void attach(V view, Bundle savedInstanceState) {
        viewRef = new WeakReference<V>(view);
    }

    @Override
    public void onAttachView(V view, Bundle savedInstanceState) {
        attach(view, savedInstanceState);
    }

    @Override
    public void onDetachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void onDestroy() {
    }
}
