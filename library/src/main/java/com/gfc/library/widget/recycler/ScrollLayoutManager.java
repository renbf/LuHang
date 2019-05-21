package com.gfc.library.widget.recycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public class ScrollLayoutManager extends LinearLayoutManager {
    public ScrollLayoutManager(Context context) {
        super(context);
    }

    public ScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public ScrollLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
