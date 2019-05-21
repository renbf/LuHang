package com.dalaiye.luhang.ui.train.rank;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.RankingAdapter;
import com.dalaiye.luhang.bean.RankingBean;
import com.dalaiye.luhang.contract.train.RankingContract;
import com.dalaiye.luhang.contract.train.impl.RankingPresenter;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;

import java.util.ArrayList;

/**
 * @author admin
 * @date 2019/4/10
 * @function 排名
 **/
public class RankingFragment extends BaseMvpFragment<RankingContract.IRankingPresenter> implements
        RankingContract.IRankingView {
    private RecyclerView mRecyclerView;
    private RankingAdapter rankingAdapter;
    private android.support.v7.widget.AppCompatTextView mTvMyRank;
    private android.support.v7.widget.AppCompatTextView mTvProSum;

    @Override
    public RankingContract.IRankingPresenter createPresenter() {
        return new RankingPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_train_ranking;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mRecyclerView = mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rankingAdapter = new RankingAdapter(R.layout.adapter_ranking_item, new ArrayList<>());
        View view = View.inflate(getContext(), R.layout.adapter_ranking_head, null);
        mTvMyRank = view.findViewById(R.id.tv_my_rank);
        mTvProSum = view.findViewById(R.id.tv_pro_sum);
        view.setVisibility(View.GONE);
        rankingAdapter.addHeaderView(view);
        mRecyclerView.setAdapter(rankingAdapter);

    }

    @Override
    protected void onLazyData() {
        mPresenter.getRankingData(AccountHelper.getInstance().getUserId());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setRankingData(RankingBean rankingBean) {
        if(rankingBean.getUserCourseVo()!=null) {
            rankingAdapter.getHeaderLayout().setVisibility(View.VISIBLE);
            mTvMyRank.setText("我的当前排名"+rankingBean.getUserCourseVo().getRank()+"名");
            mTvProSum.setText(rankingBean.getUserCourseVo().getClassHourSum()+"课时");
        }else {
            rankingAdapter.getHeaderLayout().setVisibility(View.GONE);
        }
        rankingAdapter.replaceData(rankingBean.getUserCourseVos());

    }
}
