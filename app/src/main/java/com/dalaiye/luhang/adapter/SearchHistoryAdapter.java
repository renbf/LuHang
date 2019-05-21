package com.dalaiye.luhang.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;

import com.dalaiye.luhang.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/12
 * @function 注释
 **/
public class SearchHistoryAdapter extends TagAdapter<String> {
    
    private List<String> mData;
    
    public SearchHistoryAdapter(List<String> datas) {
        super(datas);
        mData = datas;
    }

    public List<String> getData() {
        return mData;
    }

    @Override
    public View getView(FlowLayout parent, int position, String s) {
        AppCompatTextView textView = (AppCompatTextView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.adapter_app_search_history_item, parent, false);
        textView.setText(s);
        return textView;
    }
}
