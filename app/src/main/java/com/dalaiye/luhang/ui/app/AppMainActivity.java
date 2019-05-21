package com.dalaiye.luhang.ui.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.contract.app.AppMainContract;
import com.dalaiye.luhang.contract.app.impl.AppMainPresenter;
import com.dalaiye.luhang.ui.car.check.CheckDangersFragment;
import com.dalaiye.luhang.ui.car.log.LogFragment;
import com.dalaiye.luhang.ui.train.TrainFragment;
import com.dalaiye.luhang.ui.user.UserCenterFragment;
import com.dalaiye.luhang.ui.user.UserLoginActivity;
import com.gfc.library.base.BaseFragment;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.qmui.QMUIStatusBarHelper;
import com.gfc.library.utils.user.AccountHelper;
import com.igexin.sdk.PushManager;

/**
 * @author admin
 * @date 2019/4/10
 * @function 主页面
 **/
public class AppMainActivity extends BaseMvpActivity<AppMainContract.IAppMainPresenter>
        implements AppMainContract.IAppMainView, View.OnClickListener {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private int mPrePosition = -1;
    private View[] mViews = new View[5];
    private BaseFragment[] mFragments = new BaseFragment[5];

    private String mCid;

    @Override
    protected AppMainContract.IAppMainPresenter createPresenter() {
        return new AppMainPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_app_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        removeOtherActivity();

        mViews[FIRST] = findViewById(R.id.tv_home);
        mViews[SECOND] = findViewById(R.id.tv_check);
        mViews[THIRD] = findViewById(R.id.tv_train);
        mViews[FOURTH] = findViewById(R.id.tv_log);
        mViews[FIFTH] = findViewById(R.id.tv_user);

        mViews[FIRST].setOnClickListener(this);
        mViews[SECOND].setOnClickListener(this);
        mViews[THIRD].setOnClickListener(this);
        mViews[FOURTH].setOnClickListener(this);
        mViews[FIFTH].setOnClickListener(this);

        showHideFragment(FIRST);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindCid();
    }

    private void bindCid() {
        if (AccountHelper.getInstance().isLogin()
                && TextUtils.isEmpty(AccountHelper.getInstance().getCId())) {
            mCid = PushManager.getInstance().getClientid(this);
            mPresenter.bindCID(AccountHelper.getInstance().getUserId(), mCid);
        }
    }

    @Override
    protected void setStatusBarMode() {
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_home) {
            showHideFragment(FIRST);
        } else if (!AccountHelper.getInstance().isLogin()) {
            startActivity(new Intent(this, UserLoginActivity.class));
        } else if (v.getId() == R.id.tv_check) {
            showHideFragment(SECOND);
        } else if (v.getId() == R.id.tv_train) {
            showHideFragment(THIRD);
        } else if (v.getId() == R.id.tv_log) {
            showHideFragment(FOURTH);
        } else if (v.getId() == R.id.tv_user) {
            showHideFragment(FIFTH);
        }
    }

    public void showHideFragment(int position) {
        if (mPrePosition == position) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mViews.length; i++) {
            mViews[i].setSelected(i == position);

            final boolean isPosition = i == position;
            final boolean isNull = mFragments[i] == null;

            if (isPosition && isNull && i == FIRST) {

                mFragments[i] = new HomeFragment();
                transaction.add(R.id.container_layout, mFragments[i]);
            } else if (isPosition && isNull && i == SECOND) {

                mFragments[i] = new CheckDangersFragment();
                transaction.add(R.id.container_layout, mFragments[i]);
            } else if (isPosition && isNull && i == THIRD) {

                mFragments[i] = new TrainFragment();
                transaction.add(R.id.container_layout, mFragments[i]);
            } else if (isPosition && isNull && i == FOURTH) {

                mFragments[i] = new LogFragment();
                transaction.add(R.id.container_layout, mFragments[i]);
            } else if (isPosition && isNull && i == FIFTH) {

                mFragments[i] = new UserCenterFragment();
                transaction.add(R.id.container_layout, mFragments[i]);
            } else if (isPosition && !isNull) {
                transaction.show(mFragments[i]);
            } else if (!isNull) {
                transaction.hide(mFragments[i]);
            }

        }
        transaction.commit();
        mPrePosition = position;
    }

    @Override
    public void bindCIDSuccess() {
        AccountHelper.getInstance().setCID(mCid);
    }
}