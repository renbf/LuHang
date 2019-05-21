package com.dalaiye.luhang.ui.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.ExamCollectionAdapter;
import com.dalaiye.luhang.bean.ExamBean;
import com.dalaiye.luhang.bean.ExamCollectionBean;
import com.dalaiye.luhang.bean.ExamDetailsBean;
import com.dalaiye.luhang.contract.user.ExamCollectionContract;
import com.dalaiye.luhang.contract.user.impl.ExamCollectionPresenter;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 考试收藏
 **/
public class ExamCollectionFragment extends BaseMvpFragment<ExamCollectionContract.IExamCollectionPresenter>
        implements ExamCollectionContract.IExamCollectionView, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private int mCurrentPage = 1;
    private int mTotalPage = 1;
    private ExamCollectionAdapter mCollectionAdapter;

    @Override
    public ExamCollectionContract.IExamCollectionPresenter createPresenter() {
        return new ExamCollectionPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_user_exam_collection;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRefreshLayout = mRootView.findViewById(R.id.refresh_layout);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);

        mCollectionAdapter = new ExamCollectionAdapter(new ArrayList<>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCollectionAdapter);

        mRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mTotalPage = 1;
        mCurrentPage = 1;
        mPresenter.getExamCollectionData(AccountHelper.getInstance().getUserId(), mCurrentPage, mTotalPage);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mTotalPage = 1;
        mCurrentPage = 1;
        mPresenter.getExamCollectionData(AccountHelper.getInstance().getUserId(), mCurrentPage, mTotalPage);
    }

    @Override
    public void onLoadMoreRequested() {
            mCurrentPage++;
            mPresenter.getExamCollectionData(AccountHelper.getInstance().getUserId(), mCurrentPage, mTotalPage);
    }

    @Override
    public void setExamCollectionData(ExamCollectionBean examBean) {
        mRefreshLayout.finishRefresh(true);
        mTotalPage = examBean.getTotal();
        if (examBean.getRows() == null || examBean.getRows().size() == 0) {
            if (mCurrentPage == 1) {
                mCollectionAdapter.replaceData(new ArrayList<ExamCollectionBean.RowsBean>());
            }
            mCollectionAdapter.loadMoreEnd();
        } else if (mCurrentPage == 1) {
            mCollectionAdapter.replaceData(examBean.getRows());
            mCollectionAdapter.loadMoreComplete();
        } else {
            mCollectionAdapter.addData(examBean.getRows());
            mCollectionAdapter.loadMoreComplete();
        }
    }
}
