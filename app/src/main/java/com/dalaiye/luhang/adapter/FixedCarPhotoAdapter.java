package com.dalaiye.luhang.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;

import java.util.List;

/**
 * @author admin
 * @date 2019/4/29
 * @function 注释
 **/
public class FixedCarPhotoAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private String[] mEntities;

    public FixedCarPhotoAdapter(Context context, String[] entities) {
        mContext = context;
        mEntities = entities;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mEntities.length;
    }

    @Override
    public Object getItem(int i) {
        return mEntities[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Object entity = mEntities[position];
        FixedCarPhotoAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_car_photo_item, parent, false);
            holder = new FixedCarPhotoAdapter.ViewHolder();
            holder.mIvCarPhoto = convertView.findViewById(R.id.iv_car_photo);
            holder.mIvDelegate = convertView.findViewById(R.id.iv_delete);

            convertView.setTag(holder);

        } else {
            holder = (FixedCarPhotoAdapter.ViewHolder) convertView.getTag();
        }

        holder.mIvDelegate.setVisibility(View.GONE);
        Glide.with(mContext).load(entity).into(holder.mIvCarPhoto);
        return convertView;
    }

    class ViewHolder {
        AppCompatImageView mIvCarPhoto;
        AppCompatImageView mIvDelegate;
    }
}
