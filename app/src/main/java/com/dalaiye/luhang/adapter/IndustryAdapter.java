package com.dalaiye.luhang.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.IndustryBean;
import com.dalaiye.luhang.ui.app.BannerDetailsActivity;
import com.gfc.library.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/11
 * @function 行业动态adpater
 **/
public class IndustryAdapter extends BaseQuickAdapter<IndustryBean.RowsBean, RecyclerViewHolder>
        implements BaseQuickAdapter.OnItemClickListener {

    public IndustryAdapter(@Nullable List<IndustryBean.RowsBean> data) {
        super(R.layout.adapter_industry_item, data);
        setOnItemClickListener(this);
    }

    @Override
    protected void convert(RecyclerViewHolder helper, IndustryBean.RowsBean item) {
        AppCompatImageView ivIndustryThumb = helper.getView(R.id.iv_industry_thumb);
        Glide.with(mContext).load(item.getUploadUrl()).into(ivIndustryThumb);
        helper.setText(R.id.iv_industry_title,item.getTitle());
        helper.setText(R.id.iv_industry_time,item.getCreateDate());
        helper.setText(R.id.tv_industry_desc,item.getContext());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(mContext, BannerDetailsActivity.class);
        intent.putExtra("webUrl",getData().get(position).getWebUrl());
        intent.putExtra("type",1);
        mContext.startActivity(intent);
    }
}
