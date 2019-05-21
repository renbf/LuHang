package com.dalaiye.luhang.ui.car.check;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.ShowPhotoAdapter;
import com.dalaiye.luhang.bean.AcceptanceBean;
import com.dalaiye.luhang.bean.UploadInfoBean;
import com.dalaiye.luhang.contract.car.UpLoadInfoContract;
import com.dalaiye.luhang.contract.car.impl.UpLoadInfoPresenter;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.widget.grid.ScrollGridView;

import java.util.Arrays;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/26
 * @function 注释
 **/
public class UpLoadInfoActivity extends BaseMvpActivity<UpLoadInfoContract.IUpLoadInfoPresenter>
        implements UpLoadInfoContract.IUpLoadInfoView,View.OnClickListener {

    private AppCompatImageView mTopBarBack;
    private AppCompatTextView mTopBarTitle;
    private AppCompatTextView mTvSelectorTeam;
    private AppCompatTextView mTvInspectObj;
    private AppCompatTextView mTvCheckUserId;
    private AppCompatTextView mTvCheckTime;
    private AppCompatTextView mTvCheckType;
    private AppCompatTextView mTvDangersPosition;
    private AppCompatTextView mTvDangersPositionName;
    private AppCompatTextView mTvDangersTeam;
    private AppCompatTextView mTvDangersType;
    private AppCompatTextView mTvDangersLv;
    private AppCompatTextView mTvDangersFrom;
    private ScrollGridView mGvDangerPhoto;
    private AppCompatTextView mTvRemark;

    private String mDangerId;

    @Override
    protected UpLoadInfoContract.IUpLoadInfoPresenter createPresenter() {
        return new UpLoadInfoPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_upload_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mDangerId = getIntent().getStringExtra("dangerId");

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTvSelectorTeam = findViewById(R.id.tv_selector_team);
        mTvInspectObj = findViewById(R.id.tv_inspectObj);
        mTvCheckUserId = findViewById(R.id.tv_checkUserId);
        mTvCheckTime = findViewById(R.id.tv_check_time);
        mTvCheckType = findViewById(R.id.tv_check_type);
        mTvDangersPosition = findViewById(R.id.tv_dangers_position);
        mTvDangersPositionName = findViewById(R.id.tv_dangers_position_name);
        mTvDangersTeam = findViewById(R.id.tv_dangers_team);
        mTvDangersType = findViewById(R.id.tv_dangers_type);
        mTvDangersLv = findViewById(R.id.tv_dangers_lv);
        mTvDangersFrom = findViewById(R.id.tv_dangers_from);
        mGvDangerPhoto = findViewById(R.id.gv_danger_photo);
        mTvRemark = findViewById(R.id.tv_remark);
        
        mTopBarBack.setOnClickListener(this);
        mTopBarTitle.setText("上报信息");
        
        mPresenter.queryUploadInfo(mDangerId);
    }

    @Override
    public void setUploadInfo(UploadInfoBean bean) {
        mTvSelectorTeam.setText(bean.getInspectDeptName());
        mTvInspectObj.setText(bean.getInspectObj());
        mTvCheckUserId.setText(bean.getCheckUserId());
        mTvCheckTime.setText(bean.getCheckDate());
        mTvCheckType.setText(bean.getCheckTypeName());
        mTvDangersPositionName.setText(bean.getDangerName());
        mTvDangersPosition.setText(bean.getDangerPosition());
        mTvDangersTeam.setText(bean.getDangerDeptName());
        mTvDangersType.setText(bean.getDangerTypeName());
        mTvDangersLv.setText(bean.getDangerLevelName());
        mTvDangersLv.setText(bean.getDangerLevelName());
        mTvDangersFrom.setText(bean.getRiskResource());

        String[] split = bean.getDangerUrl().split(",");
        List<String> dangerList = Arrays.asList(split);
        ShowPhotoAdapter  adapter = new ShowPhotoAdapter(this,dangerList);
        mGvDangerPhoto.setAdapter(adapter);
        
        mTvRemark.setText(bean.getRemark());
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
