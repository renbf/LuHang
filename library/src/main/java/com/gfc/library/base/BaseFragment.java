package com.gfc.library.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class BaseFragment extends Fragment {
    /**
     * 标记已加载完成，保证懒加载只能加载一次
     */
    private boolean isVisible = false;
    /**
     * 是否准备完成
     */
    private boolean isPrepared = false;
    /**
     * 是否是第一次加载数据
     */
    private boolean isFirst = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //可见时执行
            isVisible = true;
            checkLazy();
        } else {
            //不可见是执行
            isVisible = false;
            onInVisible();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        checkLazy();
    }

    

    private void checkLazy() {
        //当onActivityCreated（）和setUserVisibleHint()这两个方法都执行之后才会去加载数据
        if (isFirst || !isVisible || !isPrepared) {
            return;
        }
        onLazyData();
        isFirst = true;
    }

    /**
     * 用来懒加载数据的方法
     */
    protected void onLazyData() {

    }

    /**
     * 不可见时执行具体的方法
     */
    protected void onInVisible() {
    }
}
