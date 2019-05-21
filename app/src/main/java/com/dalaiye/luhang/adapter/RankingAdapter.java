package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.RankingBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 注释
 **/
public class RankingAdapter extends BaseQuickAdapter<RankingBean.UserCourseVosBean, BaseViewHolder> {

    public RankingAdapter(int layoutResId, @Nullable List<RankingBean.UserCourseVosBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RankingBean.UserCourseVosBean item) {
        if (helper.getAdapterPosition() == 1) {
            helper.setVisible(R.id.tv_number, false);
            helper.setVisible(R.id.iv_icon, true);
            helper.setImageResource(R.id.iv_icon, R.mipmap.ic_gold_medal);
        } else if (helper.getAdapterPosition() == 2) {
            helper.setVisible(R.id.tv_number, false);
            helper.setVisible(R.id.iv_icon, true);
            helper.setImageResource(R.id.iv_icon, R.mipmap.ic_silver_medal);
        } else if (helper.getAdapterPosition() == 3) {
            helper.setVisible(R.id.tv_number, false);
            helper.setVisible(R.id.iv_icon, true);
            helper.setImageResource(R.id.iv_icon, R.mipmap.ic_bronze_medal);
        } else {
            helper.setVisible(R.id.tv_number, true);
            helper.setText(R.id.tv_number, String.valueOf(helper.getAdapterPosition()));
            helper.setVisible(R.id.iv_icon, false);
        }
        helper.setText(R.id.tv_name,item.getUserName());
        helper.setText(R.id.tv_team,item.getDeptName());
        helper.setText(R.id.tv_class_hour,item.getClassHour());
    }
}
