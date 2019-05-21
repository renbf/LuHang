package com.dalaiye.luhang.ui.car.log;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.LogAdapter;
import com.dalaiye.luhang.bean.log.LogBean;
import com.dalaiye.luhang.contract.car.LogContract;
import com.dalaiye.luhang.contract.car.impl.LogPresenter;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/10
 * @function 行车日志列表
 **/
public class LogFragment extends BaseMvpFragment<LogContract.ILogPresenter>
        implements LogContract.ILogView, View.OnClickListener, OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    private boolean isActivity = false;
    private SmartRefreshLayout mRefresh;
    private RecyclerView mRecyclerView;
    private LogAdapter logAdapter;
    private int mTotalPage = 1;
    private int mCurrentPosition = 1;

    public static LogFragment newInstance() {
        LogFragment fragment = new LogFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isActivity = true;
        }
    }

    @Override
    public LogContract.ILogPresenter createPresenter() {
        return new LogPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_log;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTopBar();
        mRefresh = mRootView.findViewById(R.id.refresh);
        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        logAdapter = new LogAdapter(new ArrayList<LogBean.RowsBean>());
        logAdapter.setOnLoadMoreListener(this, mRecyclerView);
        logAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(logAdapter);

        mRefresh.setOnRefreshListener(this);

        mPresenter.getLogData(AccountHelper.getInstance().getUserId(), mTotalPage, mCurrentPosition);
    }

    private void initTopBar() {
        AppCompatImageView topBarBack = mRootView.findViewById(R.id.top_bar_back);
        AppCompatImageView topBarAdd = mRootView.findViewById(R.id.top_bar_add);
        topBarAdd.setOnClickListener(this);
        topBarBack.setOnClickListener(this);
        topBarBack.setVisibility(isActivity ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                getActivity().finish();
                break;
            case R.id.top_bar_add:
                EventBus.getDefault().postSticky(new EventMessage(EventKeys.LOG_DRIVE_BEAN, new LogBean()));
                Intent intent = new Intent(getContext(), LogDriveDetailsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mCurrentPosition = 1;
            mTotalPage = 1;
            mPresenter.getLogData(AccountHelper.getInstance().getUserId(), mTotalPage, mCurrentPosition);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurrentPosition = 1;
        mTotalPage = 1;
        mPresenter.getLogData(AccountHelper.getInstance().getUserId(), mTotalPage, mCurrentPosition);
        if (logAdapter != null && logAdapter.getData().size() > 0) {
            logAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        mTotalPage = 1;
        mPresenter.getLogData(AccountHelper.getInstance().getUserId(), mTotalPage, mCurrentPosition);
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.getLogData(AccountHelper.getInstance().getUserId(), mTotalPage, mCurrentPosition);
    }

    @Override
    public void setLogData(LogBean logData) {
        mRefresh.finishRefresh(true);
        mTotalPage = logData.getTotal();
        if (logData.getRows() == null || logData.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                logAdapter.replaceData(new ArrayList<LogBean.RowsBean>());
            }
            logAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            logAdapter.replaceData(logData.getRows());
            logAdapter.loadMoreComplete();
        } else {
            logAdapter.addData(logData.getRows());
            logAdapter.loadMoreComplete();
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        LogBean.RowsBean logBean = logAdapter.getData().get(position);
        EventBus.getDefault().postSticky(new EventMessage(EventKeys.LOG_DRIVE_BEAN, logBean));
        Intent intent = new Intent(getContext(), LogDriveDetailsActivity.class);
        startActivity(intent);
    }
}
