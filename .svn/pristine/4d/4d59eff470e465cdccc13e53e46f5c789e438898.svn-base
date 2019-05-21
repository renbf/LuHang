package com.dalaiye.luhang.ui.car.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CheckPlanAdapter;
import com.dalaiye.luhang.bean.CheckPlanBean;
import com.dalaiye.luhang.contract.car.CheckPlanContract;
import com.dalaiye.luhang.contract.car.impl.CheckPlanPresenter;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/10
 * @function 检查计划
 **/
public class CheckPlanFragment extends BaseMvpFragment<CheckPlanContract.ICheckPlanPresenter> implements
        CheckPlanContract.ICheckPlanView, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    private com.scwang.smartrefresh.layout.SmartRefreshLayout mRefresh;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private CheckPlanAdapter mCheckPlanAdapter;
    private int mCurrentPosition = 1;
    private String total;

    @Override
    public CheckPlanContract.ICheckPlanPresenter createPresenter() {
        return new CheckPlanPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_check_plan;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mRefresh = mRootView.findViewById(R.id.refresh);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCheckPlanAdapter = new CheckPlanAdapter(R.layout.adapter_car_check_plan_item, new ArrayList<>());
        View view = View.inflate(getContext(), R.layout.adapter_all_head_view, null);
        mCheckPlanAdapter.addHeaderView(view);
        mRecyclerView.setAdapter(mCheckPlanAdapter);

        mRefresh.setOnRefreshListener(this);
        mCheckPlanAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mCheckPlanAdapter.setOnItemClickListener(this);

    }

    @Override
    protected void onLazyData() {
        mPresenter.queryCheckPlan(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        total = "";
        mPresenter.queryCheckPlan(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.queryCheckPlan(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void setCheckPlanData(CheckPlanBean checkPlanBean) {
        mRefresh.finishRefresh(true);
        total = checkPlanBean.getTotal();
        if (checkPlanBean.getRows() == null || checkPlanBean.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                mCheckPlanAdapter.replaceData(new ArrayList<>());
            }
            mCheckPlanAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            mCheckPlanAdapter.replaceData(checkPlanBean.getRows());
            mCheckPlanAdapter.loadMoreComplete();
        } else {
            mCheckPlanAdapter.addData(checkPlanBean.getRows());
            mCheckPlanAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CheckPlanBean.RowsBean rowsBean = (CheckPlanBean.RowsBean) adapter.getData().get(position);
        Intent intent = new Intent(getContext(), CheckPlanDetailsActivity.class);
        intent.putExtra("rows", rowsBean);
        switch (rowsBean.getCheckStatus()) {
            case "0":
                intent.putExtra("type", 0);
                break;
            case "1":
                intent.putExtra("type", 1);
                break;
            case "2":
                intent.putExtra("type", 2);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMessage(EventMessage message) {
        if (message.getKeysEnum() == EventKeys.COMPLETE_CHECK) {
            total = "";
            mCurrentPosition = 1;
            mPresenter.queryCheckPlan(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
        }
    }
}
