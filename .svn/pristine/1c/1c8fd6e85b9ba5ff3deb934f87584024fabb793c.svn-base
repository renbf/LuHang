package com.dalaiye.luhang.ui.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CourseAdapter;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.contract.app.AppSearchResultContract;
import com.dalaiye.luhang.contract.app.impl.AppSearchResultPresenter;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/12
 * @function 搜索结果界面
 **/
public class AppSearchResultActivity extends BaseMvpActivity<AppSearchResultContract.IAppSearchResultPresenter>
        implements AppSearchResultContract.IAppSearchResultView, View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    public static final String APP_SEARCH_CONTENT = "app_search_content";

    private AppCompatEditText mEtSearchContent;
    private RecyclerView mRecyclerView;

    private String mSearchContent;
    private int mCurrentPosition = 1;
    private String total = "1";
    private CourseAdapter mCourseAdapter;

    @Override
    protected AppSearchResultContract.IAppSearchResultPresenter createPresenter() {
        return new AppSearchResultPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_app_search_result;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mSearchContent = getIntent().getStringExtra(APP_SEARCH_CONTENT);

        mEtSearchContent = findViewById(R.id.et_search_content);
        mEtSearchContent.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mRecyclerView = findViewById(R.id.recycler_view);

        mEtSearchContent.setText(mSearchContent);
        mEtSearchContent.setFocusable(false);

        findViewById(R.id.top_bar_back).setOnClickListener(this);
        findViewById(R.id.et_search_content).setOnClickListener(this);
        findViewById(R.id.iv_search_clear).setOnClickListener(this);
        findViewById(R.id.top_bar_cancel).setOnClickListener(this);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCourseAdapter = new CourseAdapter(new ArrayList<>(), 0);
        mRecyclerView.setAdapter(mCourseAdapter);

        mPresenter.querySearchResult(AccountHelper.getInstance().getUserId(), mSearchContent, mCurrentPosition, total);
    }

    @Override
    public void setSearchResult(CourseBean courseBean) {
        total = courseBean.getTotal();
        if (courseBean.getRows() == null || courseBean.getRows().size() == 0) {
            if (mCurrentPosition == 1) {
                mCourseAdapter.replaceData(new ArrayList<>());
            }
            mCourseAdapter.loadMoreEnd();
        } else if (mCurrentPosition == 1) {
            mCourseAdapter.replaceData(courseBean.getRows());
            mCourseAdapter.loadMoreComplete();
        } else {
            mCourseAdapter.addData(courseBean.getRows());
            mCourseAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            default:
                startActivity(new Intent(this, AppSearchRequestActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mCurrentPosition++;
        mPresenter.querySearchResult(AccountHelper.getInstance().getUserId(), mSearchContent, mCurrentPosition, total);
    }
}
