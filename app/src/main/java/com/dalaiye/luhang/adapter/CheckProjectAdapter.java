package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.CheckPlanBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 检查计划详情中检查项目的adapter
 **/
public class CheckProjectAdapter extends BaseQuickAdapter<CheckPlanBean.RowsBean.CheckProjectsBean,BaseViewHolder> {

    public CheckProjectAdapter(int layoutResId, @Nullable List<CheckPlanBean.RowsBean.CheckProjectsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckPlanBean.RowsBean.CheckProjectsBean item) {
        helper.setText(R.id.tv_project_name,item.getCheckTeamName());
    }
}
