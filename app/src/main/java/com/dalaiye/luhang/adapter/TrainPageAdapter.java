package com.dalaiye.luhang.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.gfc.library.base.BaseFragment;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/11
 * @function 培训页面viewpager的adapter
 **/
public class TrainPageAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mFragments;
    private List<String> titles;

    public TrainPageAdapter(FragmentManager fm,List<BaseFragment> mFragments,List<String> titles) {
        super(fm);
        this.mFragments = mFragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }
}
