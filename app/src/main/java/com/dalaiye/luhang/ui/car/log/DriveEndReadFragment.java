package com.dalaiye.luhang.ui.car.log;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.FixedCarPhotoAdapter;
import com.dalaiye.luhang.adapter.FixedOptionAdapter;
import com.dalaiye.luhang.bean.log.LogEndBean;
import com.dalaiye.luhang.contract.car.LogDriveContract;
import com.dalaiye.luhang.contract.car.impl.DriveEndReadPresenter;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.widget.grid.ScrollGridView;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * @author admin
 * @date 2019/4/10
 * @function 行车后读取
 **/
public class DriveEndReadFragment extends BaseMvpFragment<LogDriveContract.IDriveEndReadPresenter>
        implements LogDriveContract.IDriveEndReadView {

    private View mLoadingLayout;
    private TagFlowLayout mEndCheckLayout;
    private AppCompatTextView mTvEndFaultHandling; 
    private AppCompatImageView mIvEndSign;
    private AppCompatTextView mTvEndStartCheckSite;
    private AppCompatTextView mTvEndStartCheckDesc;
    private AppCompatTextView mTvEndMiddleCheckSite;
    private AppCompatTextView mTvEndMiddleCheckDesc;
    private ScrollGridView mEndCarGridView; 

    private ILogDetailsListener mDetailsListener;
    
    private FixedOptionAdapter mOptionAdapter;
    private FixedCarPhotoAdapter mCarPhotoAdapter;

    @Override
    public LogDriveContract.IDriveEndReadPresenter createPresenter() {
        return new DriveEndReadPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_log_drive_end_read;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (getContext() instanceof LogDriveDetailsActivity) {
            mDetailsListener = (ILogDetailsListener) getContext();
        }
        mLoadingLayout = mRootView.findViewById(R.id.loading_layout);
        mEndCheckLayout = mRootView.findViewById(R.id.end_check_layout);
        mTvEndFaultHandling = mRootView.findViewById(R.id.tv_end_fault_handling); 
        mIvEndSign = mRootView.findViewById(R.id.iv_end_sign);
        mTvEndStartCheckSite = mRootView.findViewById(R.id.tv_end_start_check_site);
        mTvEndStartCheckDesc = mRootView.findViewById(R.id.tv_end_start_check_desc);
        mTvEndMiddleCheckSite = mRootView.findViewById(R.id.tv_end_middle_check_site);
        mTvEndMiddleCheckDesc = mRootView.findViewById(R.id.tv_end_middle_check_desc);
        mEndCarGridView = mRootView.findViewById(R.id.end_car_grid_view); 

        mPresenter.getLogData(  mDetailsListener.getLogId());
    }

    @Override
    public void setEndData(LogEndBean endData) {
        
        mOptionAdapter = new FixedOptionAdapter(endData.getCarCheckProjectList());
        mEndCheckLayout.setAdapter(mOptionAdapter);
        
        mTvEndFaultHandling.setText(endData.getFaultHandling());
        Glide.with(getActivity())
                .load(endData.getAutograph())
                .into(mIvEndSign);
        
        mTvEndStartCheckSite.setText(endData.getStartFromStation());
        mTvEndStartCheckDesc.setText(endData.getStartFromCheck());
        mTvEndMiddleCheckSite.setText(endData.getEnRouteAddress());
        mTvEndMiddleCheckDesc.setText(endData.getEnRouteCheck());
        
        if (!TextUtils.isEmpty(endData.getDrivingPhoto())){
            mCarPhotoAdapter = new FixedCarPhotoAdapter(getActivity(),endData.getDrivingPhoto().split(","));
            mEndCarGridView.setAdapter(mCarPhotoAdapter);
        }
        
        mLoadingLayout.setVisibility(View.GONE);
    }
}
