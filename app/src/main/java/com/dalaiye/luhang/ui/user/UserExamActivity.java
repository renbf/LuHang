package com.dalaiye.luhang.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.MyExamAdapter;
import com.dalaiye.luhang.bean.ExamBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.user.UserExamContract;
import com.dalaiye.luhang.contract.user.impl.UserExamPresenter;
import com.dalaiye.luhang.ui.train.exam.ExamDetailsActivity;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/10
 * @function 我的考卷
 **/
public class UserExamActivity extends BaseMvpActivity<UserExamContract.IUserExamPresenter>
        implements UserExamContract.IUserExamView, OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    private com.scwang.smartrefresh.layout.SmartRefreshLayout mRefresh;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private MyExamAdapter myExamAdapter;
    private int mCurrentPosition = 1;
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private String total;

    @Override
    protected UserExamContract.IUserExamPresenter createPresenter() {
        return new UserExamPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_user_exam;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mRefresh = findViewById(R.id.refresh);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myExamAdapter = new MyExamAdapter(new ArrayList<ExamBean.RowsBean>());
        mRecyclerView.setAdapter(myExamAdapter);

        mTopBarBack.setOnClickListener(this);
        mRefresh.setOnRefreshListener(this);
        myExamAdapter.setOnItemClickListener(this);
        myExamAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mTopBarTitle.setText("我的考卷");

        mPresenter.getUserExamData(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        total = "";
        mPresenter.getUserExamData(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.getUserExamData(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void setUserExamData(ExamBean examBean) {
        mRefresh.finishRefresh(true);
        total = examBean.getTotal();
        if (examBean.getRows() == null || examBean.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                myExamAdapter.replaceData(new ArrayList<ExamBean.RowsBean>());
            }
            myExamAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            myExamAdapter.replaceData(examBean.getRows());
            myExamAdapter.loadMoreComplete();
        } else {
            myExamAdapter.addData(examBean.getRows());
            myExamAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ExamBean.RowsBean rowsBean = (ExamBean.RowsBean) adapter.getData().get(position);
        Intent intent = new Intent(this, ExamDetailsActivity.class);
        intent.putExtra(ExamDetailsActivity.EXAM_USER_PAPER_ID, rowsBean.getId());
        intent.putExtra(ExamDetailsActivity.EXAM_USER_PAPER_URL, ApiService.QUERY_TRAIN_EXAM_DETAILS);
        startActivity(intent);
    }
}
