package com.dalaiye.luhang.ui.car.log;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.FixedCarPhotoAdapter;
import com.dalaiye.luhang.adapter.FixedOptionAdapter;
import com.dalaiye.luhang.bean.log.LogStartBean;
import com.dalaiye.luhang.contract.car.LogDriveContract;
import com.dalaiye.luhang.contract.car.impl.DriveStartReadPresenter;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.widget.grid.ScrollGridView;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * @author admin
 * @date 2019/4/10
 * @function 行车前读取
 **/
public class DriveStartReadFragment extends BaseMvpFragment<LogDriveContract.IDriveStartReadPresenter>
        implements LogDriveContract.IDriveStartReadView {

    private View mLoadingLayout;
    private AppCompatTextView mTvStartFillDate;
    private AppCompatTextView mTvStartCarPlate;
    private AppCompatTextView mTvStartCarLoadNuclear;
    private AppCompatTextView mTvStartCarLoadActual;
    private AppCompatTextView mTvStartGoodsName;
    private LinearLayoutCompat mGoodsTypeLayout;
    private AppCompatTextView mTvStartGoodsType;
    private AppCompatTextView mTvStartDriveFirst;
    private AppCompatTextView mTvStartDriveSecond;
    private LinearLayoutCompat mEscortLayout;
    private AppCompatTextView mTvStartEscortName;
    private AppCompatTextView mTvStartTemperature;

    private AppCompatTextView[] mWeathers;


    private AppCompatTextView mTvStartShipmentDate;
    private AppCompatTextView mTvStartShipmentLocation;
    private AppCompatTextView mTvStartDestination;
    private AppCompatTextView mTvStartMileage;
    private AppCompatTextView mTvStartAtDate;
    private TagFlowLayout mStartCheckLayout;
    private AppCompatTextView mTvStartNotMeet;
    private AppCompatTextView mTvStartDriveConclusion;
    private AppCompatTextView mTvStartRemarks;
    private ScrollGridView mStartCarGridView;
    private AppCompatTextView mTvStartSign;
    private AppCompatImageView mIvStartSign;

    private ILogDetailsListener mDetailsListener;

    private FixedOptionAdapter mOptionAdapter;
    private FixedCarPhotoAdapter mCarPhotoAdaper;

    @Override
    public LogDriveContract.IDriveStartReadPresenter createPresenter() {
        return new DriveStartReadPresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_log_drive_start_read;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        if (getContext() instanceof LogDriveDetailsActivity) {
            mDetailsListener = (ILogDetailsListener) getContext();
        }

        mLoadingLayout = mRootView.findViewById(R.id.loading_layout);
        mTvStartFillDate = mRootView.findViewById(R.id.tv_start_fill_date);
        mTvStartCarPlate = mRootView.findViewById(R.id.tv_start_car_plate);
        mTvStartCarLoadNuclear = mRootView.findViewById(R.id.tv_start_car_load_nuclear);
        mTvStartCarLoadActual = mRootView.findViewById(R.id.tv_start_car_load_actual);
        mTvStartGoodsName = mRootView.findViewById(R.id.tv_start_goods_name);
        mGoodsTypeLayout = mRootView.findViewById(R.id.goods_type_layout);
        mTvStartGoodsType = mRootView.findViewById(R.id.tv_start_goods_type);
        mTvStartDriveFirst = mRootView.findViewById(R.id.tv_start_drive_first);
        mTvStartDriveSecond = mRootView.findViewById(R.id.tv_start_drive_second);
        mEscortLayout = mRootView.findViewById(R.id.escort_layout);
        mTvStartEscortName = mRootView.findViewById(R.id.tv_start_escort_name);
        mTvStartTemperature = mRootView.findViewById(R.id.tv_start_temperature);

        mWeathers = new AppCompatTextView[6];
        mWeathers[0] = mRootView.findViewById(R.id.tv_start_fine);
        mWeathers[1] = mRootView.findViewById(R.id.tv_start_yin);
        mWeathers[2] = mRootView.findViewById(R.id.tv_start_rain);
        mWeathers[3] = mRootView.findViewById(R.id.tv_start_fog);
        mWeathers[4] = mRootView.findViewById(R.id.tv_start_snow);
        mWeathers[5] = mRootView.findViewById(R.id.tv_start_frozen);

        mTvStartShipmentDate = mRootView.findViewById(R.id.tv_start_shipment_date);
        mTvStartShipmentLocation = mRootView.findViewById(R.id.tv_start_shipment_location);
        mTvStartDestination = mRootView.findViewById(R.id.tv_start_destination);
        mTvStartMileage = mRootView.findViewById(R.id.tv_start_mileage);
        mTvStartAtDate = mRootView.findViewById(R.id.tv_start_at_date);
        mStartCheckLayout = mRootView.findViewById(R.id.start_check_layout);
        mTvStartNotMeet = mRootView.findViewById(R.id.tv_start_not_meet);
        mTvStartDriveConclusion = mRootView.findViewById(R.id.tv_start_drive_conclusion);
        mTvStartRemarks = mRootView.findViewById(R.id.tv_start_remarks);
        mStartCarGridView = mRootView.findViewById(R.id.start_car_grid_view);
        mTvStartSign = mRootView.findViewById(R.id.tv_start_sign);
        mIvStartSign = mRootView.findViewById(R.id.iv_start_sign);

        mPresenter.getLogData(mDetailsListener.getLogId());

    }

    @Override
    public void setLogData(LogStartBean startBean) {

        mTvStartFillDate.setText(startBean.getWriteTime());//填报日志
        mTvStartCarPlate.setText(startBean.getCarNumber());//车牌号
        mTvStartCarLoadNuclear.setText(startBean.getIdealTonnage());//核载吨位
        mTvStartCarLoadActual.setText(startBean.getActualTonnage());//实载吨位
        mTvStartGoodsName.setText(startBean.getGoodsNameValue());//货物名称
        if (!TextUtils.isEmpty(startBean.getTypeName())) {
            mGoodsTypeLayout.setVisibility(View.VISIBLE);
            mTvStartGoodsType.setText(startBean.getTypeName());//类项名称
            mEscortLayout.setVisibility(View.VISIBLE);
            mTvStartEscortName.setText(startBean.getEscortName());//押运员

        }
        mTvStartDriveFirst.setText(startBean.getFirstDriverName());//驾驶员1
        mTvStartDriveSecond.setText(startBean.getSecondDriverName());//驾驶员2
        mTvStartTemperature.setText(startBean.getTemperature());//气温
        //天气
        for (int i = 0; i < mWeathers.length; i++) {
            if (startBean.getWeather() != null) {
                mWeathers[i].setSelected(i == startBean.getWeather());
            }
        }
        mTvStartShipmentDate.setText(startBean.getTransportDate());//启运日期
        mTvStartShipmentLocation.setText(startBean.getTransporAddress());//启运地
        mTvStartDestination.setText(startBean.getGoalAddress());//目的地
        mTvStartMileage.setText(startBean.getKm());//里程
        mTvStartAtDate.setText(startBean.getArriveDate());//到达日期
        //检查事项
        mOptionAdapter = new FixedOptionAdapter(startBean.getCarCheckProjectList());
        mStartCheckLayout.setAdapter(mOptionAdapter);
        mTvStartNotMeet.setText(startBean.getNoAccord());//不符合项
        mTvStartDriveConclusion.setText(startBean.getSureCommentName());//确认结论
        mTvStartRemarks.setText(startBean.getRemark());//备注
        //行车照片
        if (!TextUtils.isEmpty(startBean.getDrivePhotoUrl())) {
            mCarPhotoAdaper = new FixedCarPhotoAdapter(getContext(), startBean.getDrivePhotoUrl().split(","));
            mStartCarGridView.setAdapter(mCarPhotoAdaper);
        }
        //签名照片
        Glide.with(getContext())
                .load(startBean.getAutograph())
                .into(mIvStartSign);

        mLoadingLayout.setVisibility(View.GONE);
    }
}
