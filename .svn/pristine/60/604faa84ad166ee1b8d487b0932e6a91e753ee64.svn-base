package com.dalaiye.luhang.ui.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.contract.app.UpDataPwdContract;
import com.dalaiye.luhang.contract.app.impl.UpDataPwdPresenter;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;

import java.util.Objects;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 修改密码
 **/
public class UpDataPwdActivity extends BaseMvpActivity<UpDataPwdContract.IUpDataPresenter>
        implements UpDataPwdContract.IUpDataPwdView, View.OnClickListener {
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private android.support.v7.widget.AppCompatEditText mEdOriginalPwd;
    private android.support.v7.widget.AppCompatEditText mEdNewPwd;
    private android.support.v7.widget.AppCompatEditText mEdSurePwd;
    private android.support.v7.widget.AppCompatTextView mTvSureUpdata;

    @Override
    protected UpDataPwdContract.IUpDataPresenter createPresenter() {
        return new UpDataPwdPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_app_updata_pwd;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mEdOriginalPwd = findViewById(R.id.ed_original_pwd);
        mEdOriginalPwd.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mEdNewPwd = findViewById(R.id.ed_new_pwd);
        mEdNewPwd.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mEdSurePwd = findViewById(R.id.ed_sure_pwd);
        mEdSurePwd.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mTvSureUpdata = findViewById(R.id.tv_sure_updata);

        mTopBarBack.setOnClickListener(this);
        mTvSureUpdata.setOnClickListener(this);
        mTopBarTitle.setText("修改密码");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back :
                finish();
                break;
            case R.id.tv_sure_updata:
                String oldPassWord = Objects.requireNonNull(mEdOriginalPwd.getText()).toString();
                String newPassWord = Objects.requireNonNull(mEdNewPwd.getText()).toString();
                String surePassWord = Objects.requireNonNull(mEdSurePwd.getText()).toString();
                mPresenter.updataPwd(AccountHelper.getInstance().getUserId(),oldPassWord,newPassWord,surePassWord);
                break;
            default:
                break;
        }
    }

    @Override
    public void updataPwdSuccessful() {
        AccountHelper.getInstance().clearUser();
        Intent intent = new Intent(this,AppMainActivity.class);
        startActivity(intent);
    }
}
