package com.dalaiye.luhang.ui.train.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CourseAdapter;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.contract.train.CourseContract;
import com.dalaiye.luhang.contract.train.impl.CoursePresenter;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/10
 * @function 课程
 **/
public class CourseFragment extends BaseMvpFragment<CourseContract.ICoursePresenter> implements
        CourseContract.ICourseView, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    private SmartRefreshLayout mRefresh;
    private RecyclerView mRecyclerView;
    private CourseAdapter mCourseAdapter;
    private int mCurrentPosition = 1;
    private String total;
    @Override
    public CourseContract.ICoursePresenter createPresenter() {
        return new CoursePresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_train_course;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRefresh = mRootView.findViewById(R.id.refresh);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCourseAdapter = new CourseAdapter(new ArrayList<>(),0);
        mRecyclerView.setAdapter(mCourseAdapter);

        mRefresh.setOnRefreshListener(this);
        mCourseAdapter.setOnLoadMoreListener(this,mRecyclerView);
       
    }

    @Override
    protected void onLazyData() {
        mPresenter.getCourse(AccountHelper.getInstance().getUserId(), mCurrentPosition,total);
    }

    @Override
    public void setCourse(CourseBean courseBean) {
        mRefresh.finishRefresh(true);
        total = courseBean.getTotal();

        if (courseBean.getRows() == null || courseBean.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                mCourseAdapter.replaceData(new ArrayList<>());
            }
            mCourseAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            mCourseAdapter.replaceData(courseBean.getRows());
            mCourseAdapter.loadMoreComplete();
        } else {
            mCourseAdapter.addData(courseBean.getRows());
            mCourseAdapter.loadMoreComplete();
        }
    }
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        total = "";
        mPresenter.getCourse(AccountHelper.getInstance().getUserId(), mCurrentPosition,total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.getCourse(AccountHelper.getInstance().getUserId(), mCurrentPosition,total);
    }

    
}
