package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.DangersRectificationBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/12
 * @function 隐患整改的adapter
 **/
public class DangersRectificationAdapter extends BaseQuickAdapter<DangersRectificationBean.RowsBean,BaseViewHolder> {


    public DangersRectificationAdapter(int layoutResId, @Nullable List<DangersRectificationBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DangersRectificationBean.RowsBean item) {
        helper.setText(R.id.tv_region,item.getDangerPosition());
        helper.setText(R.id.tv_rank,item.getDangerLevelName());
        helper.setText(R.id.tv_type,item.getDochangeTypeName());
        helper.setText(R.id.tv_time,item.getEndDate().substring(0,10));
        switch (item.getStatus()) {
            case "1" :
                helper.setText(R.id.tv_status,"待整改");
                helper.setBackgroundRes(R.id.tv_status,R.drawable.shape_log_state_red);
                break;
            case "2":
                helper.setText(R.id.tv_status,"已整改");
                helper.setBackgroundRes(R.id.tv_plan_state,R.drawable.shape_log_state_blue);
                break;
            default:
                break;
        }
    }
}
