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
import com.dalaiye.luhang.contract.user.UserCourseContract;
import com.dalaiye.luhang.contract.user.impl.UserCoursePresenter;
import com.dalaiye.luhang.ui.train.course.BrowseFileActivity;
import com.dalaiye.luhang.ui.train.course.CourseDetailsActivity;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/10
 * @function 我的课程
 **/
public class UserCourseActivity extends BaseMvpActivity<UserCourseContract.IUserCoursePresenter>
        implements UserCourseContract.IUserCourseView, View.OnClickListener, OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private com.scwang.smartrefresh.layout.SmartRefreshLayout mRefresh;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private CourseAdapter mCourseAdapter;
    private int mCurrentPosition = 1;
    private String total;
    @Override
    protected UserCourseContract.IUserCoursePresenter createPresenter() {
        return new UserCoursePresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_user_course;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mRefresh = findViewById(R.id.refresh);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCourseAdapter = new CourseAdapter(new ArrayList<>(),1);
        mRecyclerView.setAdapter(mCourseAdapter);

        mTopBarBack.setOnClickListener(this);
        mRefresh.setOnRefreshListener(this);
        mCourseAdapter.setOnLoadMoreListener(this,mRecyclerView);
        mCourseAdapter.setOnItemClickListener(this);
        mCourseAdapter.setOnItemChildClickListener(this);
        mTopBarTitle.setText("我的课程");

        mPresenter.getMyCompleteCourse(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back :
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        total = "";
        mPresenter.getMyCompleteCourse(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.getMyCompleteCourse(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        int itemViewType = adapter.getItemViewType(position);
        CourseBean.RowsBean rowsBean = (CourseBean.RowsBean) adapter.getData().get(position);

        Intent intentCourse = new Intent(this,CourseDetailsActivity.class);
        Intent intentFile = new Intent(this,BrowseFileActivity.class);
        intentCourse.getIntExtra("type",1);
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
    public void setMyCompleteCourseData(CourseBean courseBean) {
        mRefresh.finishRefresh(true);
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        CourseBean.RowsBean rowsBean = (CourseBean.RowsBean) adapter.getData().get(position);
        Intent intent = new Intent(this,BrowseFileActivity.class);
        intent.putExtra("rows",rowsBean);
        startActivity(intent);
    }
}
