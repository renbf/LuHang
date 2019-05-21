package com.dalaiye.luhang.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/16
 * @function 注释
 **/
public class CarPhotoAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<Object> mEntities;

    public CarPhotoAdapter(Context context) {
        mContext = context;
        mEntities = new ArrayList<>();
        mInflater = LayoutInflater.from(mContext);
    }

    public void addData(Object object) {
        if (mEntities.size() == 4) {
            mEntities.remove(3);
        }
        mEntities.add(0, object);
    }
 

    public List<String> getFilePaths() {
        List<String> list = new ArrayList<>();
        for (Object entity : mEntities) {
            if (entity instanceof String) {
                list.add((String) entity);
            }
        }
        return list;
    }


    @Override
    public int getCount() {
        return mEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return mEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Object entity = mEntities.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_car_photo_item, parent, false);
            holder = new ViewHolder();
            holder.mIvCarPhoto = convertView.findViewById(R.id.iv_car_photo);
            holder.mIvDelegate = convertView.findViewById(R.id.iv_delete);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (entity instanceof Integer) {
            holder.mIvDelegate.setVisibility(View.GONE);
        } else {
            holder.mIvDelegate.setVisibility(View.VISIBLE);
        }

        holder.mIvDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(entity instanceof Integer)) {
                    mEntities.remove(position);
                    Object lastEntity = mEntities.get(mEntities.size() - 1);
                    if (!(lastEntity instanceof Integer)) {
                        mEntities.add(R.mipmap.ic_photo_add);
                    }
                    notifyDataSetChanged();

                }
            }
        });

        Glide.with(mContext).load(entity).into(holder.mIvCarPhoto);
        return convertView;
    }

    class ViewHolder {
        AppCompatImageView mIvCarPhoto;
        AppCompatImageView mIvDelegate;
    }
}
