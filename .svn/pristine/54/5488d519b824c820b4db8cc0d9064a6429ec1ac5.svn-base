package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.ListViewDialogAdapter;
import com.dalaiye.luhang.bean.base.BaseListBean;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/16
 * @function 列表的dialog
 **/
public class ListViewDialog extends AppCompatDialog implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private List<BaseListBean> mStringList;
    private OnItemClickListener mItemClickListener;

    public ListViewDialog(Context context, List<BaseListBean> list, OnItemClickListener listener) {
        this(context, R.style.styles_dialog);
        mStringList = list;
        mItemClickListener = listener;
    }

    private ListViewDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_list_view);
        initWindow();
        mRecyclerView = findViewById(R.id.recycler_view);
        ListViewDialogAdapter adapter = new ListViewDialogAdapter(mStringList);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initWindow() {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.CENTER;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        BaseListBean baseListBean = (BaseListBean) adapter.getData().get(position);
        if (mItemClickListener != null) {
            mItemClickListener.onClick(baseListBean);
        }
        cancel();
    }

    public interface OnItemClickListener {
        void onClick(BaseListBean listBean);
    }
}
