package com.dalaiye.luhang.ui.car.log;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.LogDriveDetailsPageAdapter;
import com.dalaiye.luhang.bean.log.LogBean;
import com.dalaiye.luhang.constant.Constant;
import com.gfc.library.base.BaseActivity;
import com.gfc.library.base.BaseFragment;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.utils.user.AccountHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/10
 * @function 行车日志
 **/
public class LogDriveDetailsActivity extends BaseActivity implements View.OnClickListener
        , ILogDetailsListener {

    public static final String LOG_DRIVE_ID = "log_drive_id";

    private LogBean.RowsBean mRowsBean;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LogDriveDetailsPageAdapter mPageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_log_drive);
        EventBus.getDefault().register(this);
        initView();
        initChange();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventMessage(EventMessage message) {
        if (message.getKeysEnum() == EventKeys.LOG_DRIVE_BEAN) {
            mRowsBean = (LogBean.RowsBean) message.getMessage();
            EventBus.getDefault().removeStickyEvent(message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initChange() {

        List<BaseFragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        if (mRowsBean == null) {
            mRowsBean = new LogBean.RowsBean();
        }
        switch (mRowsBean.getStatus()) {
            case Constant.DRIVE_STATE_START:
                titles.add("行车前");
                fragments.add(new DriveStartWriteFragment());
                mTabLayout.setVisibility(View.GONE);
                break;
            case Constant.DRIVE_STATE_IN:
                if (mRowsBean.getFirstDriverId().equals(AccountHelper.getInstance().getUserId())) {
                    titles.add("行车前");
                    titles.add("行车中");
                    fragments.add(new DriveStartReadFragment());
                    fragments.add(new DriveInWriteFragment());
                } else {
                    titles.add("行车前");
                    fragments.add(new DriveStartReadFragment());
                }
                break;
            case Constant.DRIVE_STATE_END:
                if (mRowsBean.getFirstDriverId().equals(AccountHelper.getInstance().getUserId())) {
                    titles.add("行车前");
                    titles.add("行车中");
                    titles.add("行车后");
                    fragments.add(new DriveStartReadFragment());
                    fragments.add(new DriveInReadFragment());
                    fragments.add(new DriveEndWriteFragment());
                } else {
                    titles.add("行车前");
                    titles.add("行车中");
                    fragments.add(new DriveStartReadFragment());
                    fragments.add(new DriveInReadFragment());
                }
                break;
            case Constant.DRIVE_STATE_FILE:
                titles.add("行车前");
                titles.add("行车中");
                titles.add("行车后");
                fragments.add(new DriveStartReadFragment());
                fragments.add(new DriveInReadFragment());
                fragments.add(new DriveEndReadFragment());
                break;
            default:
                break;
        }
        mPageAdapter.setFragments(fragments, titles);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initView() {
        AppCompatImageView topBarBack = findViewById(R.id.top_bar_back);
        AppCompatTextView topBarTitle = findViewById(R.id.top_bar_title);
        topBarTitle.setText("日志");

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);

        topBarBack.setOnClickListener(this);

        mPageAdapter = new LogDriveDetailsPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPageAdapter);
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
    public Activity getActivity() {
        return this;
    }

    @Override
    public String getLogId() {
        return mRowsBean.getId();
    }

}
