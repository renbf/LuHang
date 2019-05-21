package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.BannerAndNoticeBean;
import com.gfc.library.widget.recycler.RecyclerViewHolder;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public class BannerAdapter extends BaseQuickAdapter<BannerAndNoticeBean.BannerListBean, RecyclerViewHolder> {

    private AppCompatImageView mImageView;

    public BannerAdapter(@Nullable List<BannerAndNoticeBean.BannerListBean> data) {
        super(R.layout.adapter_banner_item, data);
    }

    @Override
    protected void convert(RecyclerViewHolder helper, BannerAndNoticeBean.BannerListBean item) {
        mImageView = helper.getView(R.id.image_view);
        Glide.with(mContext)
                .load(item.getImgUrl())
                .into(mImageView);
    }
}
