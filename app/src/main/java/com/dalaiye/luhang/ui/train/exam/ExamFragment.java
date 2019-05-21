package com.dalaiye.luhang.ui.train.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.ExamListAdapter;
import com.dalaiye.luhang.bean.ExamBean;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.ExamContract;
import com.dalaiye.luhang.contract.train.impl.ExamPresenter;
import com.dalaiye.luhang.utils.DateUtils;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.widget.recycler.ScrollLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/10
 * @function 考试试卷
 **/
public class ExamFragment extends BaseMvpFragment<ExamContract.IExamPresenter> implements
        ExamContract.IExamView, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener
        , BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private ExamListAdapter mListAdapter;

    private int mCurrentPage = 1;
    private String total;

    @Override
    public ExamContract.IExamPresenter createPresenter() {
        return new ExamPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_train_exam;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRefreshLayout = mRootView.findViewById(R.id.refresh_layout);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);

        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView.setLayoutManager(new ScrollLayoutManager(getContext()));
        mListAdapter = new ExamListAdapter(new ArrayList<ExamBean.RowsBean>());
        View view = View.inflate(getContext(), R.layout.adapter_all_head_view, null);
        mListAdapter.addHeaderView(view);
        mListAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mListAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mListAdapter);

    }

    @Override
    protected void onLazyData() {
        mPresenter.getExamData(AccountHelper.getInstance().getUserId(), mCurrentPage, total);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPage = 1;
        total = "";
        mPresenter.getExamData(AccountHelper.getInstance().getUserId(), mCurrentPage, total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPage++;
        mPresenter.getExamData(AccountHelper.getInstance().getUserId(), mCurrentPage, total);
    }

    @Override
    public void setExamData(ExamBean examBean) {
        mRefreshLayout.finishRefresh(true);
        total = examBean.getTotal();
        if (examBean.getRows() == null || examBean.getRows().size() == 0) {
            if (mCurrentPage == 1) {
                mListAdapter.replaceData(new ArrayList<ExamBean.RowsBean>());
            }
            mListAdapter.loadMoreEnd();
        } else if (mCurrentPage == 1) {
            mListAdapter.replaceData(examBean.getRows());
            mListAdapter.loadMoreComplete();
        } else {
            mListAdapter.addData(examBean.getRows());
            mListAdapter.loadMoreComplete();
        }
    }

    @Override
    public void setIsVerifyOn(String isVerify, int position) {
            final ExamBean.RowsBean rowsBean = mListAdapter.getData().get(position);
        if ("1".endsWith(isVerify)) {
            Intent intent = new Intent(getActivity(), DiscernActivity.class);
            intent.putExtra(ExamDetailsActivity.EXAM_USER_PAPER_ID, rowsBean.getId());
            intent.putExtra(ExamDetailsActivity.EXAM_USER_PAPER_URL, ApiService.GET_TRAIN_EXAM_DETAILS);
            startActivity(intent);
        } else {
            try {
                long startDate = DateUtils.getInstance().dateToStamp(rowsBean.getBeginDate(), DateUtils.DATE_FORMAT);
                long endDate = DateUtils.getInstance().dateToStamp(rowsBean.getEndDate(), DateUtils.DATE_FORMAT);

                endDate = endDate + 86390000L;

                long currentDate = System.currentTimeMillis();
                if (currentDate < startDate) {
                    toast(0, "考试尚未开始");
                } else if (currentDate > startDate && currentDate < endDate) {
                    Intent intent = new Intent(getActivity(), ExamDetailsActivity.class);
                    intent.putExtra(ExamDetailsActivity.EXAM_USER_PAPER_ID, rowsBean.getId());
                    intent.putExtra(ExamDetailsActivity.EXAM_USER_PAPER_URL, ApiService.GET_TRAIN_EXAM_DETAILS);
                    startActivity(intent);
                } else if (currentDate > endDate) {
                    toast(0, "考试已经结束");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        mPresenter.queryIsVerifyOn(AccountHelper.getInstance().getBusinessId(), position);

    }
}
