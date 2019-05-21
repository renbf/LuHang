package com.dalaiye.luhang.ui.industry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.IndustryAdapter;
import com.dalaiye.luhang.bean.IndustryBean;
import com.dalaiye.luhang.contract.industry.IndustryContract;
import com.dalaiye.luhang.contract.industry.impl.IndustryPresenter;
import com.gfc.library.mvp.BaseMvpActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/11
 * @function 行业动态
 **/
public class IndustryActivity extends BaseMvpActivity<IndustryContract.IIndustryPresenter>
        implements IndustryContract.IIndustryView, View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener
        , OnRefreshListener {

    private AppCompatImageView mTopBarBack;
    private AppCompatTextView mTopBarTitle;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private IndustryAdapter mAdapter;
    private int mCurrentPosition = 1;
    private String total = "";

    @Override
    protected IndustryContract.IIndustryPresenter createPresenter() {
        return new IndustryPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_industry;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        mRefreshLayout.setOnRefreshListener(this);
        mTopBarBack.setOnClickListener(this);
        mTopBarTitle.setText("行业动态");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new IndustryAdapter(new ArrayList<>());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.queryIndustryData(mCurrentPosition, total);
    }

    @Override
    public void onClick(View v) {
        finish();
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        total = "";
        mPresenter.queryIndustryData(mCurrentPosition, total);
    }

    @Override
    public void setIndustryData(IndustryBean industryBean) {
        total = industryBean.getTotal();
        mRefreshLayout.finishRefresh(true);
        if (industryBean.getRows() == null || industryBean.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                mAdapter.replaceData(new ArrayList<>());
            }
            mAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            mAdapter.replaceData(industryBean.getRows());
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.addData(industryBean.getRows());
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.queryIndustryData(mCurrentPosition, total);
    }
}
