package com.dalaiye.luhang.ui.car.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.ShowPhotoAdapter;
import com.dalaiye.luhang.bean.AcceptanceBean;
import com.dalaiye.luhang.contract.car.DangersAcceptanceDetailContract;
import com.dalaiye.luhang.contract.car.impl.DangersAcceptanceDetailPresenter;
import com.gfc.library.mvp.BaseMvpActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/10
 * @function 隐患验收详情
 **/
public class DangersAcceptanceDetailsActivity extends BaseMvpActivity<DangersAcceptanceDetailContract.IDangersAcceptanceDetailPresenter>
        implements DangersAcceptanceDetailContract.IDangersAcceptanceDetailView, View.OnClickListener {

    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private com.gfc.library.widget.grid.ScrollGridView mGvDangerPhoto;
    private com.gfc.library.widget.grid.ScrollGridView mGvRectificationgPhoto;
    private android.support.v7.widget.AppCompatTextView mTvUploadResult;
    private android.support.v7.widget.AppCompatTextView mTvInspectDept;
    private android.support.v7.widget.AppCompatTextView mTvInspectObj;
    private android.support.v7.widget.AppCompatTextView mTvCheckUserId;
    private android.support.v7.widget.AppCompatTextView mTvCheckDate;
    private android.support.v7.widget.AppCompatTextView mTvCheckType;
    private android.support.v7.widget.AppCompatTextView mTvDangerPosition;
    private android.support.v7.widget.AppCompatTextView mTvDangerDept;
    private android.support.v7.widget.AppCompatTextView mTvDangerType;
    private android.support.v7.widget.AppCompatTextView mTvDangerLevel;
    private android.support.v7.widget.AppCompatTextView mTvRiskResource;
    private android.support.v7.widget.AppCompatTextView mTvMaybeResult;
    private android.support.v7.widget.AppCompatTextView mTvDoChangeStep;
    private android.support.v7.widget.AppCompatTextView mTvDoChangeCapital;
    private List<String> dnagerPhotoList;
    private List<String> doChangerPhotoList;
    private ShowPhotoAdapter dangerPhotoAdapter;
    private ShowPhotoAdapter rectificationgPhotoAdapter;
    private String id;
    private AppCompatTextView mTvDangerPositionName;

    @Override
    protected DangersAcceptanceDetailContract.IDangersAcceptanceDetailPresenter createPresenter() {
        return new DangersAcceptanceDetailPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_dangers_acceptance_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        dnagerPhotoList = new ArrayList<>();
        doChangerPhotoList = new ArrayList<>();

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mGvDangerPhoto = findViewById(R.id.gv_danger_photo);
        dangerPhotoAdapter = new ShowPhotoAdapter(this,dnagerPhotoList);
        mGvDangerPhoto.setAdapter(dangerPhotoAdapter);

        mGvRectificationgPhoto = findViewById(R.id.gv_rectificationg_photo);
        rectificationgPhotoAdapter = new ShowPhotoAdapter(this, doChangerPhotoList);
        mGvRectificationgPhoto.setAdapter(rectificationgPhotoAdapter);

        mTvInspectDept = findViewById(R.id.tv_inspect_dept);
        mTvInspectObj = findViewById(R.id.tv_inspect_obj);
        mTvCheckUserId = findViewById(R.id.tv_check_user_id);
        mTvCheckDate = findViewById(R.id.tv_check_date);
        mTvCheckType = findViewById(R.id.tv_check_type);
        mTvDangerPositionName = findViewById(R.id.tv_dangers_position_name);
        mTvDangerPosition = findViewById(R.id.tv_danger_position);
        mTvDangerDept = findViewById(R.id.tv_danger_dept);
        mTvDangerType = findViewById(R.id.tv_danger_type);
        mTvDangerLevel = findViewById(R.id.tv_danger_level);
        mTvRiskResource = findViewById(R.id.tv_risk_resource);
        mTvMaybeResult = findViewById(R.id.tv_maybe_result);
        mTvDoChangeStep = findViewById(R.id.tv_do_change_step);
        mTvDoChangeCapital = findViewById(R.id.tv_do_change_capital);
        mTvUploadResult = findViewById(R.id.tv_upload_result);

        mTopBarBack.setOnClickListener(this);
        mTvUploadResult.setOnClickListener(this);
        mTopBarTitle.setText("隐患验收详情");

        mPresenter.queryAcceptanceDetail(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back :
                finish();
                break;
            case R.id.tv_upload_result:
                Intent intent = new Intent(this, UploadCheckResultActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void queryAcceptanceDetailSuccessful(AcceptanceBean acceptanceBean) {
        mTvInspectDept.setText(acceptanceBean.getInspectDeptName());
        mTvInspectObj.setText(acceptanceBean.getInspectObj());
        mTvCheckUserId.setText(acceptanceBean.getCheckUserId());
        mTvCheckDate.setText(acceptanceBean.getCheckDate());
        mTvCheckType.setText(acceptanceBean.getCheckTypeName());
        mTvDangerPositionName.setText(acceptanceBean.getDangerName());
        mTvDangerPosition.setText(acceptanceBean.getDangerPosition());
        mTvDangerDept.setText(acceptanceBean.getDangerDeptName());
        mTvDangerType.setText(acceptanceBean.getDangerTypeName());
        mTvDangerLevel.setText(acceptanceBean.getDangerLevelName());
        mTvRiskResource.setText(acceptanceBean.getRiskResource());
        mTvMaybeResult.setText(acceptanceBean.getMaybeResultName());
        mTvDoChangeStep.setText(acceptanceBean.getDochangeStep());
        mTvDoChangeCapital.setText(acceptanceBean.getDochangeCapital());

        String[] split = acceptanceBean.getDangerUrl().split(",");
        List<String> dangerList = Arrays.asList(split);
        dnagerPhotoList.addAll(dangerList);
        dangerPhotoAdapter.notifyDataSetChanged();

        String[] split1 = acceptanceBean.getDochangePicture().split(",");
        List<String> doChangeList = Arrays.asList(split1);
        doChangerPhotoList.addAll(doChangeList);
        rectificationgPhotoAdapter.notifyDataSetChanged();
    }
}
