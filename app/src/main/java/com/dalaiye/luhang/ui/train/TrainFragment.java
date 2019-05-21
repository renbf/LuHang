package com.dalaiye.luhang.ui.train;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.TrainPageAdapter;
import com.dalaiye.luhang.bean.UpLoadProgressBean;
import com.dalaiye.luhang.contract.train.TrainContract;
import com.dalaiye.luhang.contract.train.impl.TrainPresenter;
import com.dalaiye.luhang.ui.train.course.CourseFragment;
import com.dalaiye.luhang.ui.train.exam.ExamFragment;
import com.dalaiye.luhang.ui.train.rank.RankingFragment;
import com.gfc.library.base.BaseFragment;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.mvp.BaseMvpFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/11
 * @function 培训
 **/
public class TrainFragment extends BaseMvpFragment<TrainContract.ITrainPresenter>
        implements TrainContract.ITrainView, View.OnClickListener {

    private boolean isActivity = false;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TrainPageAdapter mPageAdapter;

    public static TrainFragment newInstance() {
        TrainFragment fragment = new TrainFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isActivity = true;
        }
        EventBus.getDefault().register(this);
    }

    @Override
    public TrainContract.ITrainPresenter createPresenter() {
        return new TrainPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_train;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initTopBar();
        mTabLayout = mRootView.findViewById(R.id.tab_layout);
        mViewPager = mRootView.findViewById(R.id.view_pager);

        List<String> titles = new ArrayList<>();
        titles.add("课程安排");
        titles.add("考试试卷");
        titles.add("课时排名");

        List<BaseFragment> mFragments = new ArrayList<>();
        mFragments.add(new CourseFragment());
        mFragments.add(new ExamFragment());
        mFragments.add(new RankingFragment());

        mPageAdapter = new TrainPageAdapter(getChildFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initTopBar() {
        AppCompatImageView topBarBack = mRootView.findViewById(R.id.top_bar_back);
        topBarBack.setOnClickListener(this);
        topBarBack.setVisibility(isActivity ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMessage(EventMessage message) {
        if (message.getKeysEnum() == EventKeys.UPLOAD_VIDEO_PROGRESS) {
            UpLoadProgressBean upLoadProgressBean = (UpLoadProgressBean) message.getMessage();
            mPresenter.upLoadProgress(upLoadProgressBean.getUserCourseId(),upLoadProgressBean.getProgress(),2);
        }
    }

    @Override
    public void upLoadProgressSuccessful() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
