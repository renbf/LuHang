package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.CheckPlanBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 检查计划的adapter
 **/
public class CheckPlanAdapter extends BaseQuickAdapter<CheckPlanBean.RowsBean,BaseViewHolder> {

    public CheckPlanAdapter(int layoutResId, @Nullable List<CheckPlanBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckPlanBean.RowsBean item) {
        switch (item.getCheckStatus()) {
            case "0" :
                helper.setText(R.id.tv_plan_state,"待检查");
                helper.setBackgroundRes(R.id.tv_plan_state,R.drawable.shape_log_state_red);
                break;
            case "1":
                helper.setText(R.id.tv_plan_state,"已完成");
                helper.setBackgroundRes(R.id.tv_plan_state,R.drawable.shape_log_state_blue);
                break;
            case "2":
                helper.setText(R.id.tv_plan_state,"已上报");
                helper.setBackgroundRes(R.id.tv_plan_state,R.drawable.shape_log_state_blue);
                break;
            default:
                break;
        }
        helper.setText(R.id.tv_check_title,item.getCheckTitle());
        String checkTime = item.getCheckTime().substring(0, 10);
        helper.setText(R.id.tv_check_plan_time,checkTime);
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < item.getCheckProjects().size(); i++) {
            String checkTeamName = item.getCheckProjects().get(i).getCheckTeamName();
            stringBuilder.append(checkTeamName).append("、");
        }
        String checkName = stringBuilder.substring(0, stringBuilder.length() - 1);
        helper.setText(R.id.tv_check_project_name,checkName);
    }
}
