package com.dalaiye.luhang.ui.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.utils.DataCleanManagers;
import com.gfc.library.base.BaseActivity;
import com.gfc.library.utils.user.AccountHelper;

/**
 * @author admin
 * @date 2019/4/10
 * @function 设置界面
 **/
public class AppSettingActivity extends BaseActivity implements View.OnClickListener {
    private AppCompatImageView mTopBarBack;
    private AppCompatTextView mTopBarTitle;
    private AppCompatTextView mTvUpdatePwd;
    private AppCompatTextView mTvCleanCache;
    private RelativeLayout mRlUserHelp;
    private AppCompatTextView tvOutLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);
        initView();
    }

    private void initView() {
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTvUpdatePwd = findViewById(R.id.tv_update_pwd);
        mTvCleanCache = findViewById(R.id.tv_clean_cache);
        mRlUserHelp = findViewById(R.id.rl_user_help);
        tvOutLogin = findViewById(R.id.tv_out_login);

        mTopBarTitle.setText("系统设置");
        mTopBarBack.setOnClickListener(this);
        mTvUpdatePwd.setOnClickListener(this);
        mTvCleanCache.setOnClickListener(this);
        mRlUserHelp.setOnClickListener(this);
        tvOutLogin.setOnClickListener(this);

        try {
            mTvCleanCache.setText(DataCleanManagers.getTotalCacheSize(this));
        } catch (Exception e) {
            mTvCleanCache.setText("0k");
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.tv_clean_cache:
                if ("0K".equals(mTvCleanCache.getText().toString().trim())) {
                    ToastUtils.showShort("当前没有需要清除的缓存");
                } else {
                    DataCleanManagers.clearAllCache(AppSettingActivity.this);
                    ToastUtils.showShort("清除缓存成功！");
                    mTvCleanCache.setText("0k");
                }
                break;
            case R.id.tv_update_pwd:
                startActivity(new Intent(this, UpDataPwdActivity.class));
                break;
            case R.id.tv_out_login:
                AccountHelper.getInstance().clearUser();
                Intent intent = new Intent(this, AppMainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
