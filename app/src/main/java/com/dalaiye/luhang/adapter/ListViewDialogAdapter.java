package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.base.BaseListBean;
import com.gfc.library.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/16
 * @function 注释
 **/
public class ListViewDialogAdapter extends BaseQuickAdapter<BaseListBean, RecyclerViewHolder> {
    public ListViewDialogAdapter(@Nullable List<BaseListBean> data) {
        super(R.layout.adapter_list_view_item, data);
    }

    @Override
    protected void convert(RecyclerViewHolder helper, BaseListBean item) {
        helper.setText(R.id.text1, item.getListItem());
    }
}
