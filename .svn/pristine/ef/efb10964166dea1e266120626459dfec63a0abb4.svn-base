package com.dalaiye.luhang.ui.car.check;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CheckPlanFormAdapter;
import com.dalaiye.luhang.bean.CheckProjectBean;
import com.dalaiye.luhang.contract.car.CheckProjectContract;
import com.dalaiye.luhang.contract.car.impl.CheckProjectPresenter;
import com.gfc.library.mvp.BaseMvpActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 检查计划详情中检查项目跳转
 **/
public class CheckProjectActivity extends BaseMvpActivity<CheckProjectContract.ICheckProjectPresenter>
        implements CheckProjectContract.ICheckProjectView, View.OnClickListener {

    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private android.support.v7.widget.RecyclerView mRecyclerView;
    private android.support.v7.widget.AppCompatTextView mTvSave;
    private CheckPlanFormAdapter checkPlanFormAdapter;
    private List<CheckProjectBean> mList;
    @Override
    protected CheckProjectContract.ICheckProjectPresenter createPresenter() {
        return new CheckProjectPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_check_project;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String inspectPlanId = getIntent().getStringExtra("inspectPlanId");
        String projectId = getIntent().getStringExtra("projectId");
        mList = new ArrayList<>();
        
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mRecyclerView = findViewById(R.id.recycler_view);
        mTvSave = findViewById(R.id.tv_save);
        
        mTvSave.setOnClickListener(this);
        mTopBarBack.setOnClickListener(this);
        mTopBarTitle.setText("检查计划检查表");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkPlanFormAdapter = new CheckPlanFormAdapter(R.layout.adapter_car_check_plan_form_item,new ArrayList<>());
        mRecyclerView.setAdapter(checkPlanFormAdapter);
        mPresenter.queryCheckProject(inspectPlanId,projectId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.top_bar_back:
                finish();
                break;
            case R.id.tv_save:
                mList.clear();
                for(int i = 0; i < checkPlanFormAdapter.getData().size(); i++) {
                    CheckProjectBean checkProjectBean = new CheckProjectBean();
                    checkProjectBean.setIsOk(checkPlanFormAdapter.getData().get(i).getIsOk());
                    checkProjectBean.setCheckProjectId(checkPlanFormAdapter.getData().get(i).getCheckProjectId());
                    checkProjectBean.setCheckTeamId(checkPlanFormAdapter.getData().get(i).getCheckTeamId());
                    checkProjectBean.setInspectPlanId(checkPlanFormAdapter.getData().get(i).getInspectPlanId());
                    mList.add(checkProjectBean);
                }
                String jsonString = JSON.toJSONString(mList);
                mPresenter.saveCheckProject(jsonString);
                break;
            default:
                break;
        }
    }

    @Override
    public void setCheckProject(List<CheckProjectBean> checkProjectBeans) {
        checkPlanFormAdapter.replaceData(checkProjectBeans);
        checkPlanFormAdapter.notifyDataSetChanged();
    }

    @Override
    public void saveCheckProjectSuccessful() {
        finish();
    }

}
