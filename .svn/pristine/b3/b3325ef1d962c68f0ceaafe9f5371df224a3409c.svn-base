package com.dalaiye.luhang.ui.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.BannerAdapter;
import com.dalaiye.luhang.adapter.IndustryAdapter;
import com.dalaiye.luhang.bean.BannerAndNoticeBean;
import com.dalaiye.luhang.bean.IndustryBean;
import com.dalaiye.luhang.contract.app.HomeContract;
import com.dalaiye.luhang.contract.app.impl.HomePresenter;
import com.dalaiye.luhang.ui.car.check.CheckDangersActivity;
import com.dalaiye.luhang.ui.car.log.LogActivity;
import com.dalaiye.luhang.ui.industry.IndustryActivity;
import com.dalaiye.luhang.ui.train.TrainActivity;
import com.dalaiye.luhang.ui.train.tips.TipsActivity;
import com.dalaiye.luhang.ui.user.UserLoginActivity;
import com.dalaiye.luhang.ui.user.UserMessageActivity;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.widget.recycler.ScrollLayoutManager;
import com.gfc.library.widget.text.ScrollTextView;
import com.leochuan.AutoPlayRecyclerView;
import com.leochuan.CarouselLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public class HomeFragment extends BaseMvpFragment<HomeContract.IHomePresenter>
        implements HomeContract.IHomeView, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {

    private View mViewMsg;
    private AutoPlayRecyclerView mAutoRecyclerView;
    private ScrollTextView mTvBulletin;
    private RelativeLayout mLoadingLayout;
    private RecyclerView mIndustryRecyclerView;

    private BannerAdapter mBannerAdapter;
    private CarouselLayoutManager mCarouselManager;
    private IndustryAdapter mIndustryAdapter;

    @Override
    public HomeContract.IHomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_app_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mViewMsg = mRootView.findViewById(R.id.view_msg);
        mAutoRecyclerView = mRootView.findViewById(R.id.auto_recycler_view);
        mLoadingLayout = mRootView.findViewById(R.id.loading_layout);
        mTvBulletin = mRootView.findViewById(R.id.tv_bulletin);
        mIndustryRecyclerView = mRootView.findViewById(R.id.industry_recycler_view);

        mRootView.findViewById(R.id.tv_search).setOnClickListener(this);
        mRootView.findViewById(R.id.msg_layout).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_tips).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_train).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_check).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_log).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_more).setOnClickListener(this);

        initRecyclerView();

        mPresenter.getTopData(AccountHelper.getInstance().getUserId());

        int pagerNumber = 1;
        mPresenter.getIndustryData(pagerNumber);

    }

    private void initRecyclerView() {
        //轮播图
        mBannerAdapter = new BannerAdapter(new ArrayList<>());
        mCarouselManager = new CarouselLayoutManager(getContext(), SizeUtils.dp2px(100));
        mCarouselManager.setMinScale(0.8F);
        mCarouselManager.setMoveSpeed(0.5f);
        mAutoRecyclerView.setLayoutManager(mCarouselManager);
        mAutoRecyclerView.setAdapter(mBannerAdapter);
        mBannerAdapter.setOnItemClickListener(this);
        //行业动态
        mIndustryAdapter = new IndustryAdapter(new ArrayList<>());
        mIndustryRecyclerView.setLayoutManager(new ScrollLayoutManager(getContext()));
        mIndustryRecyclerView.setAdapter(mIndustryAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccountHelper.getInstance().isLogin()) {
            mPresenter.isHaveMessage(AccountHelper.getInstance().getUserId());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_tips) {
            startActivity(new Intent(getContext(), TipsActivity.class));
        } else if (!AccountHelper.getInstance().isLogin()) {
            startActivity(new Intent(getContext(), UserLoginActivity.class));
        } else if (v.getId() == R.id.tv_search) {
            startActivity(new Intent(getContext(), AppSearchRequestActivity.class));
        } else if (v.getId() == R.id.msg_layout) {
            startActivity(new Intent(getContext(), UserMessageActivity.class));
        } else if (v.getId() == R.id.tv_train) {
            startActivity(new Intent(getContext(), TrainActivity.class));
        } else if (v.getId() == R.id.tv_check) {
            startActivity(new Intent(getContext(), CheckDangersActivity.class));
        } else if (v.getId() == R.id.tv_log) {
            startActivity(new Intent(getContext(), LogActivity.class));
        } else if (v.getId() == R.id.tv_more) {
            startActivity(new Intent(getContext(), IndustryActivity.class));
        }
    }


    @Override
    public void setTopData(BannerAndNoticeBean bannerAndNoticeBean) {
        if (mLoadingLayout.getVisibility() == View.VISIBLE) {
            mLoadingLayout.setVisibility(View.GONE);
        }
        List<String> noticeList = new ArrayList<>();
        for (int i = 0; i < bannerAndNoticeBean.getNoticeList().size(); i++) {
            noticeList.add(bannerAndNoticeBean.getNoticeList().get(i).getName());
        }
        mTvBulletin.startWithList(noticeList);
        mBannerAdapter.replaceData(bannerAndNoticeBean.getBannerList());
    }

    @Override
    public void setIndustryData(IndustryBean industryBean) {
        if (mLoadingLayout.getVisibility() == View.VISIBLE) {
            mLoadingLayout.setVisibility(View.GONE);
        }
        mIndustryAdapter.replaceData(industryBean.getRows());
    }

    @Override
    public void setIsHaveMessage(String isHaveMessage) {
        if ("0".equals(isHaveMessage)) {
            mViewMsg.setVisibility(View.GONE);
        } else {
            mViewMsg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        List<BannerAndNoticeBean.BannerListBean> data = mBannerAdapter.getData();
        String webUrl = data.get(position).getWebUrl();
        Intent intent = new Intent(getContext(),BannerDetailsActivity.class);
        intent.putExtra("type",1);
        intent.putExtra("webUrl",webUrl);
        startActivity(intent);
    }
}
