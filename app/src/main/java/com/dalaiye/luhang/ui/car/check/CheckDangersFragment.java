package com.dalaiye.luhang.ui.car.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.TrainPageAdapter;
import com.dalaiye.luhang.contract.car.CheckDangersContract;
import com.dalaiye.luhang.contract.car.impl.CheckDangersPresenter;
import com.gfc.library.base.BaseFragment;
import com.gfc.library.mvp.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/10
 * @function 检查隐患fragment
 **/
public class CheckDangersFragment extends BaseMvpFragment<CheckDangersContract.ICheckDangersPresenter>
        implements CheckDangersContract.ICheckDangersView, View.OnClickListener {

    private boolean isActivity = false;
    private android.support.design.widget.TabLayout mTabLayout;
    private android.support.v4.view.ViewPager mViewPager;
    private TrainPageAdapter mPageAdapter;

    public static CheckDangersFragment newInstance() {
        CheckDangersFragment fragment = new CheckDangersFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isActivity = true;
        }
    }

    @Override
    public CheckDangersContract.ICheckDangersPresenter createPresenter() {
        return new CheckDangersPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_check_dangers;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTopBar();
        mTabLayout = mRootView.findViewById(R.id.tab_layout);
        mViewPager = mRootView.findViewById(R.id.view_pager);

        List<String> titles = new ArrayList<>();
        titles.add("检查计划");
        titles.add("隐患整改");
        titles.add("隐患验收");

        List<BaseFragment> mFragments = new ArrayList<>();
        mFragments.add(new CheckPlanFragment());
        mFragments.add(new DangersRectificationFragment());
        mFragments.add(new DangersAcceptanceFragment());

        mPageAdapter = new TrainPageAdapter(getChildFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initTopBar() {
        AppCompatImageView topBarBack = mRootView.findViewById(R.id.top_bar_back);
        topBarBack.setOnClickListener(this);
        topBarBack.setVisibility(isActivity ? View.VISIBLE : View.GONE);
        AppCompatTextView uploadDangersTitle = mRootView.findViewById(R.id.tv_upload_dangers_title);
        uploadDangersTitle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                getActivity().finish();
                break;
            case R.id.tv_upload_dangers_title:
                startActivity(new Intent(getContext(),UpLoadDangersActivity.class));
                break;
            default:
                break;
        }
    }
}
