package com.dalaiye.luhang.ui.car.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.ShowPhotoAdapter;
import com.dalaiye.luhang.bean.RectificationBean;
import com.dalaiye.luhang.contract.car.DangersRectificationDetailsContract;
import com.dalaiye.luhang.contract.car.impl.DangersRectificationDetailPresenter;
import com.dalaiye.luhang.widget.dialog.RemarksDialog;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.mvp.BaseMvpActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 * @date 2019/4/10
 * @function 隐患整改详情，隐患接受
 **/
public class DangersRectificationDetailsActivity extends BaseMvpActivity<DangersRectificationDetailsContract.IDangersRectificationDetailsPresenter>
        implements DangersRectificationDetailsContract.IDangersRectificationDetailsView, View.OnClickListener,
        RemarksDialog.DialogClickCallBack{
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private com.gfc.library.widget.grid.ScrollGridView mGvDangerPhoto;
    private RemarksDialog mRemarksDialog;
    private ShowPhotoAdapter mShowPhotoAdapter;
    private List<String> photoList;
    private android.support.v7.widget.AppCompatTextView mTvAccept;
    private android.support.v7.widget.AppCompatTextView mTvRefuse;
    private android.support.v7.widget.AppCompatTextView mTvInspectDept;
    private android.support.v7.widget.AppCompatTextView mTvInspectObj;
    private android.support.v7.widget.AppCompatTextView mTvCheckUserId;
    private android.support.v7.widget.AppCompatTextView mTvCheckDate;
    private android.support.v7.widget.AppCompatTextView mTvCheckType;
    private AppCompatTextView mTvDangerPositionName;
    private android.support.v7.widget.AppCompatTextView mTvDangerPosition;
    private android.support.v7.widget.AppCompatTextView mTvDangerDept;
    private android.support.v7.widget.AppCompatTextView mTvDangerType;
    private android.support.v7.widget.AppCompatTextView mTvDangerLevel;
    private android.support.v7.widget.AppCompatTextView mTvRiskResource;
    private android.support.v7.widget.AppCompatTextView mTvMaybeResult;
    private android.support.v7.widget.AppCompatTextView mTvRemark;
    private android.support.v7.widget.AppCompatTextView mTvDoChangeType;
    private android.support.v7.widget.AppCompatTextView mTvEndDate;
    private android.support.v7.widget.AppCompatTextView mTvCheckAcceptDept;
    private android.support.v7.widget.AppCompatTextView mTvCheckAcceptUser;
    private String id;

    @Override
    protected DangersRectificationDetailsContract.IDangersRectificationDetailsPresenter createPresenter() {
        return new DangersRectificationDetailPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_dangers_rectification_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        int type = getIntent().getIntExtra("type", 0);
        id = getIntent().getStringExtra("id");
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTvInspectDept = findViewById(R.id.tv_inspect_dept);
        mTvInspectObj = findViewById(R.id.tv_inspect_obj);
        mTvCheckUserId = findViewById(R.id.tv_check_user_id);
        mTvCheckDate = findViewById(R.id.tv_check_date);
        mTvCheckType = findViewById(R.id.tv_check_type);
        mTvDangerPosition = findViewById(R.id.tv_danger_position);
        mTvDangerPositionName = findViewById(R.id.tv_dangers_position_name);
        mTvDangerDept = findViewById(R.id.tv_danger_dept);
        mTvDangerType = findViewById(R.id.tv_danger_type);
        mTvDangerLevel = findViewById(R.id.tv_danger_level);
        mTvRiskResource = findViewById(R.id.tv_risk_resource);
        mTvMaybeResult = findViewById(R.id.tv_maybe_result);
        mGvDangerPhoto = findViewById(R.id.gv_danger_photo);
        mTvRemark = findViewById(R.id.tv_remark);
        mTvDoChangeType = findViewById(R.id.tv_do_change_type);
        mTvEndDate = findViewById(R.id.tv_end_date);
        mTvCheckAcceptDept = findViewById(R.id.tv_check_accept_dept);
        mTvCheckAcceptUser = findViewById(R.id.tv_check_accept_user);
        mTvAccept = findViewById(R.id.tv_accept);
        mTvRefuse = findViewById(R.id.tv_refuse);

        photoList = new ArrayList<>();
        mShowPhotoAdapter = new ShowPhotoAdapter(this,photoList);
        mGvDangerPhoto.setAdapter(mShowPhotoAdapter);
        switch (type) {
            case 1 :
                mTvAccept.setVisibility(View.VISIBLE);
                mTvRefuse.setVisibility(View.VISIBLE);
                break;
            case 2:
                mTvAccept.setVisibility(View.GONE);
                mTvRefuse.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        mTopBarTitle.setText("隐患整改详情");
        mTopBarBack.setOnClickListener(this);
        mTvAccept.setOnClickListener(this);
        mTvRefuse.setOnClickListener(this);

        mRemarksDialog = new RemarksDialog(this, "请输入拒绝原因",this);
        mRemarksDialog.setCancelable(false);

        mPresenter.queryHiddenDangerDetail(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.tv_accept:
                Intent intent = new Intent(this, DangersAccpetRectificationActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_refuse:
                if (mRemarksDialog != null && !mRemarksDialog.isShowing()) {
                    mRemarksDialog.show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onConfirmClick(String refuseContent) {
        mPresenter.refuseHiddenDanger(id,refuseContent);
    }

    @Override
    public void onCancelClick() {
        if (mRemarksDialog != null && mRemarksDialog.isShowing()) {
            mRemarksDialog.dismiss();
        }
    }

    @Override
    public void queryHiddenDangerDetailSuccessful(RectificationBean rectificationBean) {
        mTvInspectDept.setText(rectificationBean.getInspectDeptName());
        mTvInspectObj.setText(rectificationBean.getInspectObj());
        mTvCheckUserId.setText(rectificationBean.getCheckUserId());
        mTvCheckDate.setText(rectificationBean.getCheckDate());
        mTvCheckType.setText(rectificationBean.getCheckTypeName());
        mTvDangerPositionName.setText(rectificationBean.getDangerName());
        mTvDangerPosition.setText(rectificationBean.getDangerPosition());
        mTvDangerDept.setText(rectificationBean.getDangerDeptName());
        mTvDangerType.setText(rectificationBean.getDangerTypeName());
        mTvDangerLevel.setText(rectificationBean.getDangerLevelName());
        mTvMaybeResult.setText(rectificationBean.getMaybeResultName());
        mTvRiskResource.setText(rectificationBean.getRiskResourceName());
        mTvRemark.setText(rectificationBean.getRemark());
        mTvDoChangeType.setText(rectificationBean.getDochangeTypeName());
        mTvEndDate.setText(rectificationBean.getEndDate());
        mTvCheckAcceptDept.setText(rectificationBean.getCheckAcceptDeptName());
        mTvCheckAcceptUser.setText(rectificationBean.getCheckAcceptUserName());

        String dangerUrl = rectificationBean.getDangerUrl();
        String[] split = dangerUrl.split(",");
        List<String> list = Arrays.asList(split);
        photoList.addAll(list);
        mShowPhotoAdapter.notifyDataSetChanged();
    }

    @Override
    public void refuseHiddenDangerSuccessful(String message) {
        if (mRemarksDialog != null && mRemarksDialog.isShowing()) {
            mRemarksDialog.dismiss();
        }
        Toast.makeText(DangersRectificationDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new EventMessage(EventKeys.DANGERS_SUBMIT_REFUSE,null));
        finish();
    }
}
