package com.dalaiye.luhang.ui.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.SearchHistoryAdapter;
import com.dalaiye.luhang.contract.app.AppSearchRequestContract;
import com.dalaiye.luhang.contract.app.AppSearchResultContract;
import com.dalaiye.luhang.contract.app.impl.AppSearchRequestPresenter;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.gfc.library.config.ConfigKeys;
import com.gfc.library.config.ECLibrary;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.utils.watcher.CustomTextWatcher;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/12
 * @function 搜索请求界面
 **/
public class AppSearchRequestActivity extends BaseMvpActivity<AppSearchRequestContract.IAppSearchRequestPresenter>
        implements AppSearchRequestContract.IAppSearchRequestView, View.OnClickListener
        , TagFlowLayout.OnTagClickListener, TextView.OnEditorActionListener {

    private AppCompatEditText mEtSearchContent;

    private TagFlowLayout mFlowLayout;
    private SearchHistoryAdapter mHistoryAdapter;

    @Override
    protected AppSearchRequestContract.IAppSearchRequestPresenter createPresenter() {
        return new AppSearchRequestPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_app_search_request;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        removeThis();

        mEtSearchContent = findViewById(R.id.et_search_content);
        mEtSearchContent.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mFlowLayout = findViewById(R.id.flow_layout);

        findViewById(R.id.top_bar_back).setOnClickListener(this);
        findViewById(R.id.iv_search_clear).setOnClickListener(this);
        findViewById(R.id.top_bar_cancel).setOnClickListener(this);
        findViewById(R.id.iv_search_delete).setOnClickListener(this);

        mEtSearchContent.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (TextUtils.isEmpty(s.toString())) {
                    findViewById(R.id.iv_search_clear).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.iv_search_clear).setVisibility(View.VISIBLE);
                }
            }
        });
        mEtSearchContent.setOnEditorActionListener(this);
        
        mPresenter.querySearchHistory(AccountHelper.getInstance().getUserId());

    }

    private void removeThis() {
        ArrayList<Activity> activities = ECLibrary.getConfiguration(ConfigKeys.ACTIVITIES);

        for (Activity activity : activities) {
            if (activity instanceof AppSearchResultContract) {
                activity.finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.iv_search_clear:
                mEtSearchContent.setText("");
                v.setVisibility(View.GONE);
                break;
            case R.id.top_bar_cancel:
                finish();
                break;
            case R.id.iv_search_delete:
                if (mHistoryAdapter != null && mHistoryAdapter.getData().size() > 0) {
                    mPresenter.deleteHistory(AccountHelper.getInstance().getUserId());
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void setSearchHistory(List<String> list) {
        mHistoryAdapter = new SearchHistoryAdapter(list);
        mFlowLayout.setAdapter(mHistoryAdapter);
        mFlowLayout.setOnTagClickListener(this);
    }

    @Override
    public void historyDeleteSuccess() {
        mHistoryAdapter.getData().clear();
        mHistoryAdapter.notifyDataChanged();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            String content = mEtSearchContent.getText().toString().trim();
            if (TextUtils.isEmpty(content)) {
                toast(1, "搜索内容不能为空！");
            } else {
                mPresenter.addSearchHistory(content);
                Intent intent = new Intent(this, AppSearchResultActivity.class);
                intent.putExtra(AppSearchResultActivity.APP_SEARCH_CONTENT, content);
                startActivity(intent);
                finish();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        String content = mHistoryAdapter.getData().get(position);
        Intent intent = new Intent(this, AppSearchResultActivity.class);
        intent.putExtra(AppSearchResultActivity.APP_SEARCH_CONTENT, content);
        startActivity(intent);
        finish();
        return true;
    }
}
