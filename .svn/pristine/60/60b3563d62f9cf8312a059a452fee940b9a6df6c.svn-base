package com.dalaiye.luhang.ui.car.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.DangersRectificationAdapter;
import com.dalaiye.luhang.bean.DangersRectificationBean;
import com.dalaiye.luhang.contract.car.DangersRectificationContract;
import com.dalaiye.luhang.contract.car.impl.DangersRectificationPresenter;
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
import java.util.List;

/**
 * @author admin
 * @date 2019/4/10
 * @function 隐患整改
 **/
public class DangersRectificationFragment extends BaseMvpFragment<DangersRectificationContract.IDangersRectificationPresenter> implements
        DangersRectificationContract.IDangersRectificationView, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    private com.scwang.smartrefresh.layout.SmartRefreshLayout mRefresh;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private int mCurrentPosition = 1;
    private DangersRectificationAdapter dangersRectificationAdapter;
    private String total;

    @Override
    public DangersRectificationContract.IDangersRectificationPresenter createPresenter() {
        return new DangersRectificationPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_dangers_rectification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mRefresh = mRootView.findViewById(R.id.refresh);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dangersRectificationAdapter = new DangersRectificationAdapter(R.layout.adapter_car_dangers_rectification_item, new ArrayList<>());
        View view = View.inflate(getContext(), R.layout.adapter_all_head_view, null);
        dangersRectificationAdapter.addHeaderView(view);
        mRecyclerView.setAdapter(dangersRectificationAdapter);

        mRefresh.setOnRefreshListener(this);
        dangersRectificationAdapter.setOnLoadMoreListener(this, mRecyclerView);
        dangersRectificationAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void onLazyData() {
        mPresenter.queryDangersRectification(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        total = "";
        mPresenter.queryDangersRectification(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.queryDangersRectification(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<DangersRectificationBean.RowsBean> data = dangersRectificationAdapter.getData();
        Intent intent = new Intent(getContext(), DangersRectificationDetailsActivity.class);
        intent.putExtra("id",data.get(position).getId());
        switch (data.get(position).getStatus()) {
            case "1":
                intent.putExtra("type",1);
                break;
            case "2":
                intent.putExtra("type",2);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    @Override
    public void setDangersRectification(DangersRectificationBean dangersRectificationBean) {
        mRefresh.finishRefresh(true);
        total = dangersRectificationBean.getTotal();
        if (dangersRectificationBean.getRows() == null || dangersRectificationBean.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                dangersRectificationAdapter.replaceData(new ArrayList<>());
            }
            dangersRectificationAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            dangersRectificationAdapter.replaceData(dangersRectificationBean.getRows());
            dangersRectificationAdapter.loadMoreComplete();
        } else {
            dangersRectificationAdapter.addData(dangersRectificationBean.getRows());
            dangersRectificationAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMessage(EventMessage message) {
        if (message.getKeysEnum() == EventKeys.DANGERS_SUBMIT_REFUSE) {
            total = "";
            mCurrentPosition = 1;
            mPresenter.queryDangersRectification(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
        }
    }
}
