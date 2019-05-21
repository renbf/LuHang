package com.dalaiye.luhang.ui.train.tips;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.TipsAdapter;
import com.dalaiye.luhang.bean.TipsBean;
import com.dalaiye.luhang.contract.train.TrainTipsContract;
import com.dalaiye.luhang.contract.train.impl.TrainTipsPresenter;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/10
 * @function 小知识
 **/
public class TipsActivity extends BaseMvpActivity<TrainTipsContract.ITrainTipsPresenter>
        implements TrainTipsContract.ITrainTipsView, View.OnClickListener, OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private com.scwang.smartrefresh.layout.SmartRefreshLayout mRefresh;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private TipsAdapter tipsAdapter;
    private int mCurrentPosition = 1;
    private String total = "";
    @Override
    protected TrainTipsContract.ITrainTipsPresenter createPresenter() {
        return new TrainTipsPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_train_tips;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTopBarTitle.setText("小知识");
        mTopBarBack.setOnClickListener(this);

        mRefresh = findViewById(R.id.refresh);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tipsAdapter = new TipsAdapter(R.layout.adapter_train_tips_item,new ArrayList<>());
        mRecyclerView.setAdapter(tipsAdapter);

        mRefresh.setOnRefreshListener(this);
        tipsAdapter.setOnLoadMoreListener(this,mRecyclerView);
        tipsAdapter.setOnItemClickListener(this);

        mPresenter.getTipsData(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
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
        mPresenter.getTipsData(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.getTipsData(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void setTipsData(TipsBean tipsBean) {
        mRefresh.finishRefresh(true);
        total = tipsBean.getTotal();
        if (tipsBean.getRows() == null || tipsBean.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                tipsAdapter.replaceData(new ArrayList<>());
            }
            tipsAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            tipsAdapter.replaceData(tipsBean.getRows());
            tipsAdapter.loadMoreComplete();
        } else {
            tipsAdapter.addData(tipsBean.getRows());
            tipsAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TipsBean.RowsBean rowsBean = tipsAdapter.getData().get(position);
        Intent intent = new Intent(TipsActivity.this,TipsDetailsActivity.class);
        intent.putExtra("rowsBean",rowsBean);
        startActivity(intent);
    }
}
