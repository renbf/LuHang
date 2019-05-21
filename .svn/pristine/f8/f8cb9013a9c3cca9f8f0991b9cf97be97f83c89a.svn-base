package com.dalaiye.luhang.ui.car.log;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.FixedCarPhotoAdapter;
import com.dalaiye.luhang.adapter.FixedOptionAdapter;
import com.dalaiye.luhang.bean.log.LogInBean;
import com.dalaiye.luhang.contract.car.LogDriveContract;
import com.dalaiye.luhang.contract.car.impl.DriveInReadPresenter;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.widget.grid.ScrollGridView;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * @author admin
 * @date 2019/4/10
 * @function 行车中读取
 **/
public class DriveInReadFragment extends BaseMvpFragment<LogDriveContract.IDriveInReadPresenter>
        implements LogDriveContract.IDriveInReadView {

    private RelativeLayout mLoadingLayout;
    private AppCompatTextView mTvInFillDate;
    private TagFlowLayout mInCheckLayout;
    private AppCompatTextView mTvInChangeLocation;
    private AppCompatTextView mTvInCarStopDate;
    private AppCompatTextView mTvInRestLocation;
    private AppCompatTextView mTvInCarStartDate;
    private AppCompatTextView mTvInFaultHandling;
    private ScrollGridView mInCarGridView;
    private AppCompatImageView mIvInSign;
    private AppCompatTextView mTvInHandoverDate;
    private AppCompatTextView mTvInRemarks;

    private ILogDetailsListener mDetailsListener;

    private FixedOptionAdapter mOptionAdapter;
    private FixedCarPhotoAdapter mCarPhotoAdapter;


    @Override
    public LogDriveContract.IDriveInReadPresenter createPresenter() {
        return new DriveInReadPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_log_drive_in_read;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        if (getContext() instanceof LogDriveDetailsActivity) {
            mDetailsListener = (ILogDetailsListener) getContext();
        }

        mLoadingLayout = mRootView.findViewById(R.id.loading_layout);
        mTvInFillDate = mRootView.findViewById(R.id.tv_in_fill_date);
        mInCheckLayout = mRootView.findViewById(R.id.in_check_layout);
        mTvInChangeLocation = mRootView.findViewById(R.id.tv_in_change_location);
        mTvInCarStopDate = mRootView.findViewById(R.id.tv_in_car_stop_date);
        mTvInRestLocation = mRootView.findViewById(R.id.tv_in_rest_location);
        mTvInCarStartDate = mRootView.findViewById(R.id.tv_in_car_start_date);
        mTvInFaultHandling = mRootView.findViewById(R.id.tv_in_fault_handling);
        mInCarGridView = mRootView.findViewById(R.id.in_car_grid_view);
        mIvInSign = mRootView.findViewById(R.id.iv_in_sign);
        mTvInHandoverDate = mRootView.findViewById(R.id.tv_in_handover_date);
        mTvInRemarks = mRootView.findViewById(R.id.tv_in_remarks);

        mPresenter.getLogData(mDetailsListener.getLogId());
    }

    @Override
    public void setLogData(LogInBean inBean) {

        mTvInFillDate.setText(inBean.getWriteTime());
        mOptionAdapter = new FixedOptionAdapter(inBean.getCarCheckProjectList());
        mInCheckLayout.setAdapter(mOptionAdapter);
        mTvInChangeLocation.setText(inBean.getChangeAddress());
        mTvInCarStopDate.setText(inBean.getStopCarTime());
        mTvInRestLocation.setText(inBean.getRestAddress());
        mTvInCarStartDate.setText(inBean.getStartCarTime());
        mTvInFaultHandling.setText(inBean.getTroubleShooting());
        if (!TextUtils.isEmpty(inBean.getDrivingPhoto())) {
            mCarPhotoAdapter = new FixedCarPhotoAdapter(getContext(), inBean.getDrivingPhoto().split(","));
            mInCarGridView.setAdapter(mCarPhotoAdapter);
        }

        Glide.with(getContext())
                .load(inBean.getAutograph())
                .into(mIvInSign);

        mTvInHandoverDate.setText(inBean.getUpdateDate());
        mTvInRemarks.setText(inBean.getExchangeRemark());

        mLoadingLayout.setVisibility(View.GONE);
    }
}
