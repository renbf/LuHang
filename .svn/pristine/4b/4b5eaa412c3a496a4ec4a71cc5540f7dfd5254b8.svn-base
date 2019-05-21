package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.DangersAcceptanceBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/12
 * @function 隐患验收的adapter
 **/
public class DangersAcceptanceAdapter extends BaseQuickAdapter<DangersAcceptanceBean.RowsBean,BaseViewHolder> {

    public DangersAcceptanceAdapter(int layoutResId, @Nullable List<DangersAcceptanceBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DangersAcceptanceBean.RowsBean item) {
        helper.setText(R.id.tv_do_change_dept,item.getDochangeDeptName());
        helper.setText(R.id.tv_do_change_type,item.getDochangeTypeName());
        helper.setText(R.id.tv_dochange_user,item.getDochangeUserName());
        helper.setText(R.id.tv_end_date,item.getEndDate());

    }
}
