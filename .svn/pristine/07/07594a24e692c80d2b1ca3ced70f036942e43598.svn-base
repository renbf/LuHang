package com.dalaiye.luhang.ui.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.MessageAdapter;
import com.dalaiye.luhang.bean.MessageBean;
import com.dalaiye.luhang.constant.Constant;
import com.dalaiye.luhang.contract.user.UserMessageContract;
import com.dalaiye.luhang.contract.user.impl.UserMessagePresenter;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/12
 * @function 消息页面
 **/
public class UserMessageActivity extends BaseMvpActivity<UserMessageContract.IUserMessagePresenter>
        implements UserMessageContract.IUserMessageView, View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener,
        OnRefreshListener, BaseQuickAdapter.OnItemClickListener {

    private AppCompatImageView mTopBarBack;
    private AppCompatTextView mTopBarTitle;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;

    private MessageAdapter mMessageAdapter;
    private int mCurrentPosition = 1;
    private String total = "";
    private AppCompatTextView mTopBarText;

    @Override
    protected UserMessageContract.IUserMessagePresenter createPresenter() {
        return new UserMessagePresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_user_message;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mTopBarText = findViewById(R.id.top_bar_text);

        mRefreshLayout.setOnRefreshListener(this);
        mTopBarBack.setOnClickListener(this);
        mTopBarText.setOnClickListener(this);

        mTopBarTitle.setText("消息");
        mTopBarText.setText("全部已读");

        mMessageAdapter = new MessageAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mMessageAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMessageAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mMessageAdapter.setOnItemClickListener(this);

        mPresenter.queryMessageData(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.top_bar_text:
                mPresenter.readAllMessage(AccountHelper.getInstance().getUserId());
                break;
            default:
                break;
        }
    }

    @Override
    public void setMessageData(MessageBean messageBean) {
        total = messageBean.getTotal();
        mRefreshLayout.finishRefresh(true);
        if (messageBean.getRows() == null || messageBean.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                mMessageAdapter.replaceData(new ArrayList<>());
            }
            mMessageAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            mMessageAdapter.replaceData(messageBean.getRows());
            mMessageAdapter.loadMoreComplete();
        } else {
            mMessageAdapter.addData(messageBean.getRows());
            mMessageAdapter.loadMoreComplete();
        }
    }

    @Override
    public void readAllMsgSuccessful() {
        List<MessageBean.RowsBean> data = mMessageAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setIsRead("1");
        }
        mMessageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.queryMessageData(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPosition = 1;
        total = "";
        mPresenter.queryMessageData(AccountHelper.getInstance().getUserId(), mCurrentPosition, total);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String messageType = mMessageAdapter.getData().get(position).getMessageType();


        switch (Integer.parseInt(messageType)) {
            case Constant.MESSAGE_STATE_COURSE:

                break;
            case Constant.MESSAGE_STATE_EXAM:

                break;
            case Constant.MESSAGE_STATE_CHECK:

                break;
            case Constant.MESSAGE_STATE_RECTIFICATION:

                break;
            case Constant.MESSAGE_STATE_ACCEPTANCE:

                break;
            default:
                break;
        }
    }
}
