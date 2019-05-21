package com.dalaiye.luhang.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CourseAdapter;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.contract.user.CourseCollectionContract;
import com.dalaiye.luhang.contract.user.impl.CourseCollectionPresenter;
import com.dalaiye.luhang.ui.train.course.BrowseFileActivity;
import com.dalaiye.luhang.ui.train.course.CourseDetailsActivity;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 课程收藏
 **/
public class CourseCollectionFragment extends BaseMvpFragment<CourseCollectionContract.ICourseCollectionPresenter>
        implements CourseCollectionContract.ICourseCollectionView, BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, OnRefreshListener {

    private com.scwang.smartrefresh.layout.SmartRefreshLayout mRefresh;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private CourseAdapter mCourseAdapter;
    private int mCurrentPosition = 1;
    private String total;
    @Override
    public CourseCollectionContract.ICourseCollectionPresenter createPresenter() {
        return new CourseCollectionPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_user_course_collection;
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
        mCourseAdapter.setOnItemClickListener(this);
        mCourseAdapter.setOnItemChildClickListener(this);

        mPresenter.courseCollection(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void setCollectionData(CourseBean courseBean) {
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
        mPresenter.courseCollection(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.courseCollection(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        int itemViewType = adapter.getItemViewType(position);
        CourseBean.RowsBean rowsBean = (CourseBean.RowsBean) adapter.getData().get(position);

        Intent intentCourse = new Intent(getContext(),CourseDetailsActivity.class);
        Intent intentFile = new Intent(getContext(),BrowseFileActivity.class);

        intentCourse.putExtra("type",1);
        intentCourse.putExtra("rows",rowsBean);
        intentFile.putExtra("rows",rowsBean);

        switch (itemViewType) {
            case 0 :
                startActivity(intentCourse);
                break;
            case 1:
                startActivity(intentFile);
                break;
            case 2:
                startActivity(intentCourse);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        CourseBean.RowsBean rowsBean = (CourseBean.RowsBean) adapter.getData().get(position);
        Intent intent = new Intent(getContext(),BrowseFileActivity.class);
        intent.putExtra("rows",rowsBean);
        startActivity(intent);
    }

}
