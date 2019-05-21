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
 * @author AnYu
 * @date 2019/4/26
 * @function 注释
 **/
public class ShowPhotoAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    public ShowPhotoAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_car_photo_item, parent, false);
            holder = new ViewHolder();
            holder.mIvCarPhoto = convertView.findViewById(R.id.iv_car_photo);
            holder.mIvDelegate = convertView.findViewById(R.id.iv_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mIvDelegate.setVisibility(View.GONE);
        Glide.with(mContext).load(mList.get(position)).into(holder.mIvCarPhoto);
        return convertView;
    }
    static class ViewHolder {
        AppCompatImageView mIvCarPhoto;
        AppCompatImageView mIvDelegate;
    }
}
