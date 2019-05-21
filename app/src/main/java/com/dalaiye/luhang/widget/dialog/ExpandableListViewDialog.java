package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.ExpandableListViewAdapter;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/23
 * @function 注释
 **/
public class ExpandableListViewDialog extends AppCompatDialog{
    private RecyclerView mRecyclerView;
    private List<MultiItemEntity> list;
    private onItemPostionClick mOnItemPostionClick;
    public ExpandableListViewDialog(Context context, List<MultiItemEntity> list,onItemPostionClick mOnItemPostionClick) {
        this(context, R.style.styles_dialog);
        this.list = list;
        this.mOnItemPostionClick = mOnItemPostionClick;
    }

    public ExpandableListViewDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_expandable);
        initWindow();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(list,mOnItemPostionClick);
        mRecyclerView.setAdapter(expandableListViewAdapter);
    }
    private void initWindow() {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.CENTER;
        }
    }


    public interface onItemPostionClick{
        void onClick(String childId,String childName);
    }
}
