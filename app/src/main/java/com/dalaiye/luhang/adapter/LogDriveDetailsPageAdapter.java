package com.dalaiye.luhang.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.gfc.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/15
 * @function 注释
 **/
public class LogDriveDetailsPageAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mFragments;
    private List<String> mTitles;
    public LogDriveDetailsPageAdapter(FragmentManager fm) {
        super(fm);
        this.mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();
    }

    public void setFragments(List<BaseFragment> fragments,List<String> titles) {
        mTitles.addAll(titles);
        mFragments.addAll(fragments);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}
