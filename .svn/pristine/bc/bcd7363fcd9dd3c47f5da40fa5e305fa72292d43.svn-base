package com.dalaiye.luhang.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.contract.user.LoginContract;
import com.dalaiye.luhang.contract.user.impl.LoginPresenter;
import com.dalaiye.luhang.ui.app.AppMainActivity;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.qmui.QMUIStatusBarHelper;
import com.gfc.library.utils.user.AccountHelper;

import java.util.Objects;

/**
 * @author admin
 * @date 2019/4/11
 * @function 登录
 **/
public class UserLoginActivity extends BaseMvpActivity<LoginContract.ILoginViewPresenter>
        implements LoginContract.ILoginView, View.OnClickListener {
    private android.support.v7.widget.AppCompatEditText mEtUserName;
    private android.support.v7.widget.AppCompatEditText mEtPassword;
    private android.support.v7.widget.AppCompatTextView mTvLogin;

    @Override
    protected LoginContract.ILoginViewPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mEtUserName = findViewById(R.id.et_user_name);
        mEtPassword = findViewById(R.id.et_password);
        mTvLogin = findViewById(R.id.tv_login);
        mTvLogin.setOnClickListener(this);
        mTvLogin.setEnabled(true);
    }

    @Override
    protected void setStatusBarMode() {
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarDarkMode(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login :
                String userName = Objects.requireNonNull(mEtUserName.getText()).toString().trim();
                String passWord = Objects.requireNonNull(mEtPassword.getText()).toString().trim();
                mPresenter.userLogin(userName,passWord);
                break;
            default:
                break;
        }
    }

    @Override
    public void userLoginSuccessful(String userId, String isAuth, String businessId) {
        if("0".equals(isAuth)) {
            AccountHelper.getInstance().setUserId(userId,false,businessId);
            startActivity(new Intent(UserLoginActivity.this,UserAuthActivity.class));
        }else {
            AccountHelper.getInstance().setUserId(userId,true,businessId);
            startActivity(new Intent(UserLoginActivity.this,AppMainActivity.class));
        }
        finish();
    }
}
