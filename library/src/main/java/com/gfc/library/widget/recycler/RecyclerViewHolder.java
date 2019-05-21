package com.gfc.library.widget.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;


/**
 * @author admin
 * @date 2018/4/21
 */
public class RecyclerViewHolder extends BaseViewHolder {

    private RecyclerViewHolder(View view) {
        super(view);
    }

    public static RecyclerViewHolder create(View view) {
        return new RecyclerViewHolder(view);
    }
}
