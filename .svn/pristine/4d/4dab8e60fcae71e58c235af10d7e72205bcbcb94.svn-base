package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.TipsBean;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/12
 * @function 小知识的adapter
 **/
public class TipsAdapter extends BaseQuickAdapter<TipsBean.RowsBean,BaseViewHolder> {
    public TipsAdapter(int layoutResId, @Nullable List<TipsBean.RowsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TipsBean.RowsBean item) {
        helper.setText(R.id.tv_course_name,item.getCourseName());
        AppCompatImageView imageView = helper.getView(R.id.tv_img);
        Glide.with(mContext).load(item.getCourseUrl()).into(imageView);
        helper.setText(R.id.tv_introduce,item.getIntroduction());
    }
}
