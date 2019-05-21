package com.dalaiye.luhang.ui.car.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.DangersAcceptanceAdapter;
import com.dalaiye.luhang.bean.DangersAcceptanceBean;
import com.dalaiye.luhang.contract.car.DangersAcceptanceContract;
import com.dalaiye.luhang.contract.car.impl.DangersAcceptancePresenter;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/10
 * @function 隐患验收
 **/
public class DangersAcceptanceFragment extends BaseMvpFragment<DangersAcceptanceContract.IDangersAcceptancePresenter>
        implements DangersAcceptanceContract.IDangersAcceptanceView, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    private com.scwang.smartrefresh.layout.SmartRefreshLayout mRefresh;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private DangersAcceptanceAdapter dangersAcceptanceAdapter;
    private int mCurrentPosition = 1;
    private String total;
    @Override
    public DangersAcceptanceContract.IDangersAcceptancePresenter createPresenter() {
        return new DangersAcceptancePresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_dangers_acceptance;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mRefresh = mRootView.findViewById(R.id.refresh);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dangersAcceptanceAdapter = new DangersAcceptanceAdapter(R.layout.adapter_car_dangers_acceptance_item,new ArrayList<>());
        View view = View.inflate(getContext(), R.layout.adapter_all_head_view, null);
        dangersAcceptanceAdapter.addHeaderView(view);
        mRecyclerView.setAdapter(dangersAcceptanceAdapter);
        mRefresh.setOnRefreshListener(this);
        dangersAcceptanceAdapter.setOnLoadMoreListener(this,mRecyclerView);
        dangersAcceptanceAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void onLazyData() {
        mPresenter.getDangersAcceptanceData(AccountHelper.getInstance().getUserId(),mCurrentPosition,total);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        total = "";
        mPresenter.getDangersAcceptanceData(AccountHelper.getInstance().getUserId(), mCurrentPosition,total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.getDangersAcceptanceData(AccountHelper.getInstance().getUserId(), mCurrentPosition,total);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getContext(), DangersAcceptanceDetailsActivity.class);
        intent.putExtra("id",dangersAcceptanceAdapter.getData().get(position).getId());
        startActivity(intent);
    }

    @Override
    public void setDangersAcceptanceData(DangersAcceptanceBean dangersAcceptanceData) {
        mRefresh.finishRefresh(true);
        total = dangersAcceptanceData.getTotal();
        if (dangersAcceptanceData.getRows() == null || dangersAcceptanceData.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                dangersAcceptanceAdapter.replaceData(new ArrayList<>());
            }
            dangersAcceptanceAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            dangersAcceptanceAdapter.replaceData(dangersAcceptanceData.getRows());
            dangersAcceptanceAdapter.loadMoreComplete();
        } else {
            dangersAcceptanceAdapter.addData(dangersAcceptanceData.getRows());
            dangersAcceptanceAdapter.loadMoreComplete();
        }
    }
}
