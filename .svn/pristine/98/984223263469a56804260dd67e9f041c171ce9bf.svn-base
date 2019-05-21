package com.dalaiye.luhang.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.log.LogBean;
import com.dalaiye.luhang.constant.Constant;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/12
 * @function 注释
 **/
public class LogAdapter extends BaseQuickAdapter<LogBean.RowsBean, BaseViewHolder> {

    private AppCompatTextView mTvLogDriveState;
    private AppCompatTextView mTvCarLicensePlate;
    private AppCompatTextView mTvDateStart;

    public LogAdapter(@Nullable List<LogBean.RowsBean> data) {
        super(R.layout.adapter_car_log_item, data); 
    }

    @Override
    protected void convert(BaseViewHolder helper, LogBean.RowsBean item) {
        mTvLogDriveState = helper.getView(R.id.tv_log_drive_state);
        mTvCarLicensePlate = helper.getView(R.id.tv_car_license_plate);
        mTvDateStart = helper.getView(R.id.tv_date_start);

        String licensePlate = "车牌号：" + item.getCarNumber();
        SpannableString ss = new SpannableString("出行时间：" + item.getTransportDate());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.color_text_dark));
        ss.setSpan(colorSpan, 0, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        mTvCarLicensePlate.setText(licensePlate);
        mTvDateStart.setText(ss);

        switch (item.getStatus()) {
            case Constant.DRIVE_STATE_IN:
                mTvLogDriveState.setText("行车中");
                mTvLogDriveState.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_log_state_red));
                break;
            case Constant.DRIVE_STATE_START:
                mTvLogDriveState.setText("行车前");
                mTvLogDriveState.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_log_state_blue));
                break;
            case Constant.DRIVE_STATE_END:
                mTvLogDriveState.setText("行车后");
                mTvLogDriveState.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_log_state_blue));
                break;
            case Constant.DRIVE_STATE_FILE:
                mTvLogDriveState.setText("已归档");
                mTvLogDriveState.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_log_state_blue));
                break;
            default:
                mTvLogDriveState.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.shape_log_state_blue));
                break;
        }
    }
}
