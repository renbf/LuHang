package com.dalaiye.luhang.ui.car.check;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CheckProjectAdapter;
import com.dalaiye.luhang.bean.CheckPlanBean;
import com.dalaiye.luhang.contract.car.CheckPlanDetailsContract;
import com.dalaiye.luhang.contract.car.impl.CheckPlanDetailsPresenter;
import com.gfc.library.mvp.BaseMvpActivity;

/**
 * @author admin
 * @date 2019/4/10
 * @function 检查计划详情
 **/
public class CheckPlanDetailsActivity extends BaseMvpActivity<CheckPlanDetailsContract.ICheckPlanDetailsPresenter>
        implements CheckPlanDetailsContract.ICheckPlanDetailsView, View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private android.support.v7.widget.AppCompatTextView mTvCheckPlanTitle;
    private android.support.v7.widget.AppCompatTextView mTvCheckPlanType;
    private android.support.v7.widget.AppCompatTextView mTvCheckPlanYear;
    private android.support.v7.widget.AppCompatTextView mTvCheckPlanTime;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private CheckProjectAdapter checkProjectAdapter;
    private android.support.v7.widget.AppCompatTextView mTvUploadDangers;
    private android.support.v7.widget.AppCompatTextView mTvCompleteCheck;
    private android.support.v7.widget.AppCompatTextView tvLookDangersInfo;
    private CheckPlanBean.RowsBean rowsBean;
    private int type;
    @Override
    protected CheckPlanDetailsContract.ICheckPlanDetailsPresenter createPresenter() {
        return new CheckPlanDetailsPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_check_plan_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        rowsBean = (CheckPlanBean.RowsBean) getIntent().getSerializableExtra("rows");
        type = getIntent().getIntExtra("type", 0);

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTvCheckPlanTitle = findViewById(R.id.tv_check_plan_title);
        mTvCheckPlanType = findViewById(R.id.tv_check_plan_type);
        mTvCheckPlanYear = findViewById(R.id.tv_check_plan_year);
        mTvCheckPlanTime = findViewById(R.id.tv_check_plan_time);
        mRecyclerView = findViewById(R.id.recycler_view);

        mTvUploadDangers = findViewById(R.id.tv_upload_dangers);
        mTvCompleteCheck = findViewById(R.id.tv_complete_check);
        tvLookDangersInfo = findViewById(R.id.tv_look_dangers_info);
        switch (type) {
            case 0 :
                mTvUploadDangers.setVisibility(View.VISIBLE);
                mTvCompleteCheck.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvLookDangersInfo.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        mTvCheckPlanTitle.setText(rowsBean.getCheckTitle());
        mTvCheckPlanType.setText(rowsBean.getCheckTypeName());
        mTvCheckPlanYear.setText(rowsBean.getCheckYear());
        mTvCheckPlanTime.setText(rowsBean.getCheckTime());
        mTopBarTitle.setText("检查计划详情");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkProjectAdapter = new CheckProjectAdapter(R.layout.adapter_car_check_project_item,rowsBean.getCheckProjects());
        mRecyclerView.setAdapter(checkProjectAdapter);

        checkProjectAdapter.setOnItemClickListener(this);
        mTopBarBack.setOnClickListener(this);
        mTvUploadDangers.setOnClickListener(this);
        mTvCompleteCheck.setOnClickListener(this);
        tvLookDangersInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.tv_upload_dangers:
                Intent intent = new Intent(this, UpLoadDangersActivity.class);
                intent.putExtra("id",rowsBean.getId());
                startActivity(intent);
                finish();
                break;
            case R.id.tv_complete_check:
                Intent completeCheckIntent = new Intent(this, CompleteCheckActivity.class);
                completeCheckIntent.putExtra("id",rowsBean.getId());
                startActivity(completeCheckIntent);
                finish();
                break;
            case R.id.tv_look_dangers_info:
                Intent intent1 = new Intent(this, UpLoadInfoActivity.class);
                intent1.putExtra("dangerId",rowsBean.getDangerId());
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this, CheckProjectActivity.class);
        intent.putExtra("inspectPlanId",rowsBean.getId());
        intent.putExtra("projectId",rowsBean.getCheckProjects().get(position).getCheckTeam());
        startActivity(intent);
    }
}
