package com.gfc.library.mvp;

import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.gfc.library.base.BaseActivity;
import com.gfc.library.utils.net.CustomCallback;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.widget.dialog.LoaderDialog;

/**
 * @author 王文波
 * @date 2019/1/17
 * @function 功能
 */
public abstract class BaseMvpActivity<P extends IPresenter> extends BaseActivity implements IView {

    protected P mPresenter;

    private LoaderDialog mLoaderDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }
        mPresenter.onAttachView(this, savedInstanceState);
        setContentView(layoutRes());
        initView(savedInstanceState);

    }

    protected abstract P createPresenter();

    protected abstract int layoutRes();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void showLoading() {
        if (mLoaderDialog == null) {
            mLoaderDialog = new LoaderDialog(this);
        }
        if (!mLoaderDialog.isShowing()) {
            mLoaderDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mLoaderDialog != null && mLoaderDialog.isShowing()) {
            mLoaderDialog.cancel();
        }
    }

    @Override
    public void toast(int code, String message) {
        if (code == CustomCallback.LOGIN_INVALID) {
            ToastUtils.showShort("用户信息异常，请重新登录！");
            AccountHelper.getInstance().clearUser();
        } else {
            ToastUtils.showShort(message);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter.onDetachView();
        }
    }
}
