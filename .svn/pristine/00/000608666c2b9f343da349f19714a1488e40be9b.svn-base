package com.gfc.library.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.gfc.library.base.BaseFragment;
import com.gfc.library.widget.dialog.LoaderDialog;

/**
 * @author 王文波
 * @date 2019/1/17
 * @function 功能
 */
public abstract class BaseMvpFragment<P extends IPresenter> extends BaseFragment implements IView {

    /**
     * 根view
     */
    protected View mRootView;
    
    public P mPresenter;
    
    private LoaderDialog mLoaderDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (layoutRes() instanceof Integer) {
            mRootView = inflater.inflate((int) layoutRes(), container, false);
        } else if (layoutRes() instanceof View) {
            mRootView = (View) layoutRes();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter == null) {
            throw new NullPointerException("Presenter is null! Do you return null in createPresenter()?");
        }
        mPresenter.onAttachView(this, savedInstanceState);
        initView(savedInstanceState);
    }

    public abstract P createPresenter();

    protected abstract Object layoutRes();

    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void showLoading() {
        if (mLoaderDialog == null) {
            mLoaderDialog = new LoaderDialog(getContext());
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
        ToastUtils.showShort(message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter.onDetachView();
        }
    }
}
