package com.dalaiye.luhang.ui.car.check;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CarPhotoAdapter;
import com.dalaiye.luhang.bean.UpLoadDangersBean;
import com.dalaiye.luhang.bean.UpLoadDangersDefaultInfoBean;
import com.dalaiye.luhang.bean.base.BaseListBean;
import com.dalaiye.luhang.contract.car.UpLoadDangersContract;
import com.dalaiye.luhang.contract.car.impl.UpLoadDangersPresenter;
import com.dalaiye.luhang.utils.DateUtils;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.dalaiye.luhang.widget.dialog.CustomTimePicker;
import com.dalaiye.luhang.widget.dialog.ExpandableListViewDialog;
import com.dalaiye.luhang.widget.dialog.ListViewDialog;
import com.dalaiye.luhang.widget.dialog.PhotoDialog;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author AnYu
 * @date 2019/4/17
 * @function 上报隐患
 **/
public class UpLoadDangersActivity extends BaseMvpActivity<UpLoadDangersContract.IUpLoadDangerPresenter>
        implements UpLoadDangersContract.IUpLoadDangersView, View.OnClickListener, AdapterView.OnItemClickListener,
        PhotoDialog.PhotoListener {
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private android.support.v7.widget.AppCompatTextView mTvSelectorTeam;
    private android.support.v7.widget.AppCompatTextView mTvCheckType;
    private android.support.v7.widget.AppCompatTextView mTvDangersTeam;
    private android.support.v7.widget.AppCompatTextView mTvDangersType;
    private android.support.v7.widget.AppCompatTextView mTvDangersLv;
    private com.gfc.library.widget.grid.ScrollGridView mGvDangerPhoto;
    private AppCompatTextView tvDangersFrom;
    private ListViewDialog selectorTeamDialog;
    private ListViewDialog checkTypeDialog;
    private ListViewDialog dangersTeamDialog;
    private ExpandableListViewDialog dangersTypeDialog;
    private ListViewDialog dangersLvDialog;
    private ListViewDialog dangersFromDialog;

    private android.widget.RadioButton mRbOne;
    private android.widget.RadioButton mRbTwo;
    private android.widget.RadioButton mRbThree;
    private CarPhotoAdapter mPhotoAdapter;
    private PhotoDialog mPhotoDialog;

    private UpLoadDangersBean upLoadDangersBean;
    private AppCompatTextView mTvUpload;
    private android.support.v7.widget.AppCompatEditText mEtInspectObj;
    private android.support.v7.widget.AppCompatEditText mEtCheckUserId;
    private AppCompatTextView mTvCheckTime;
    private android.support.v7.widget.AppCompatEditText etDangersName;
    private android.support.v7.widget.AppCompatEditText mEtDangersPosition;
    private android.support.v7.widget.AppCompatEditText mEtRemark;
    private String id;
    private List<File> mFilesList;


    @Override
    protected UpLoadDangersContract.IUpLoadDangerPresenter createPresenter() {
        return new UpLoadDangersPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_upload_dangers;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        id = getIntent().getStringExtra("id");
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);

        mTvSelectorTeam = findViewById(R.id.tv_selector_team);
        mTvCheckType = findViewById(R.id.tv_check_type);
        mTvDangersTeam = findViewById(R.id.tv_dangers_team);
        mTvDangersType = findViewById(R.id.tv_dangers_type);
        mTvDangersLv = findViewById(R.id.tv_dangers_lv);
        mGvDangerPhoto = findViewById(R.id.gv_danger_photo);
        tvDangersFrom = findViewById(R.id.tv_dangers_from);

        mEtInspectObj = findViewById(R.id.et_inspectObj);
        mEtInspectObj.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mEtCheckUserId = findViewById(R.id.et_checkUserId);
        mEtInspectObj.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mTvCheckTime = findViewById(R.id.tv_check_time);
        etDangersName = findViewById(R.id.et_dangers_name);
        mEtDangersPosition = findViewById(R.id.et_dangers_position);
        mEtInspectObj.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mEtRemark = findViewById(R.id.et_remark);
        mEtInspectObj.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mTvUpload = findViewById(R.id.tv_upload);

        mTopBarBack.setOnClickListener(this);
        mTvSelectorTeam.setOnClickListener(this);
        mTvCheckType.setOnClickListener(this);
        mTvDangersTeam.setOnClickListener(this);
        mTvDangersType.setOnClickListener(this);
        mTvDangersLv.setOnClickListener(this);
        tvDangersFrom.setOnClickListener(this);
        mTvCheckTime.setOnClickListener(this);
        mTvUpload.setOnClickListener(this);

        mTopBarTitle.setText("隐患上报");

        mRbOne = findViewById(R.id.rb_one);
        mRbTwo = findViewById(R.id.rb_two);
        mRbThree = findViewById(R.id.rb_three);

        mPresenter.getInitData(AccountHelper.getInstance().getBusinessId());


    }

    @Override
    public void setInitData(final UpLoadDangersDefaultInfoBean upLoadDangersDefaultInfoBean) {
        //成功获取数据之后，创建bean用来上传
        upLoadDangersBean = new UpLoadDangersBean();
        //创建人
        upLoadDangersBean.setAddUserId(AccountHelper.getInstance().getUserId());
        //企业id
        upLoadDangersBean.setBusinessId(AccountHelper.getInstance().getBusinessId());
        //检查计划id
        if (!TextUtils.isEmpty(id)) {
            upLoadDangersBean.setInspectPlanId(id);
        }
        //受检部门 
        selectorTeamDialog = new ListViewDialog(this
                , new ArrayList<BaseListBean>(upLoadDangersDefaultInfoBean.getDeptList())
                , new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                UpLoadDangersDefaultInfoBean.DeptListBean bean = (UpLoadDangersDefaultInfoBean.DeptListBean) listBean;
                upLoadDangersBean.setInspectDeptId(Long.valueOf(bean.getDeptId()));
                mTvSelectorTeam.setText(bean.getDeptName());
            }
        });
        //检查类型 
        checkTypeDialog = new ListViewDialog(this
                , new ArrayList<BaseListBean>(upLoadDangersDefaultInfoBean.getCheckTypes())
                , new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                UpLoadDangersDefaultInfoBean.CheckTypesBean checkTypesBean = (UpLoadDangersDefaultInfoBean.CheckTypesBean) listBean;
                upLoadDangersBean.setCheckType(checkTypesBean.getId());
                mTvCheckType.setText(checkTypesBean.getDictName());

            }
        });
        //隐患部门
        dangersTeamDialog = new ListViewDialog(this
                , new ArrayList<BaseListBean>(upLoadDangersDefaultInfoBean.getDeptList())
                , new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                UpLoadDangersDefaultInfoBean.DeptListBean bean = (UpLoadDangersDefaultInfoBean.DeptListBean) listBean;
                upLoadDangersBean.setDangerDeptId(Long.valueOf(bean.getDeptId()));
                mTvDangersTeam.setText(bean.getDeptName());
            }
        });
        //隐患类型
        List<MultiItemEntity> list = new ArrayList<>();
        for (UpLoadDangersDefaultInfoBean.DangerTypesBean dangerType : upLoadDangersDefaultInfoBean.getDangerTypes()) {
            list.add(dangerType);
        }
        dangersTypeDialog = new ExpandableListViewDialog(UpLoadDangersActivity.this, list, new ExpandableListViewDialog.onItemPostionClick() {
            @Override
            public void onClick(String childId, String childName) {
                upLoadDangersBean.setDangerType(childId);
                mTvDangersType.setText(childName);
                dangersTypeDialog.dismiss();
            }
        });

        //隐患等级
        dangersLvDialog = new ListViewDialog(this
                , new ArrayList<BaseListBean>(upLoadDangersDefaultInfoBean.getDangerLevels())
                , new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                UpLoadDangersDefaultInfoBean.DangerLevelsBean checkTypesBean = (UpLoadDangersDefaultInfoBean.DangerLevelsBean) listBean;
                upLoadDangersBean.setDangerLevel(checkTypesBean.getId());
                mTvDangersLv.setText(checkTypesBean.getDictName());
            }
        });

        //风险来源 
        dangersFromDialog = new ListViewDialog(this
                , new ArrayList<BaseListBean>(upLoadDangersDefaultInfoBean.getHiddenDangersFrom())
                , new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                UpLoadDangersDefaultInfoBean.HiddenDangersFromBean bean = (UpLoadDangersDefaultInfoBean.HiddenDangersFromBean) listBean;
                upLoadDangersBean.setRiskResource(bean.getId());
                tvDangersFrom.setText(bean.getDictName());
            }
        });

        //可能后果
        mRbOne.setText(upLoadDangersDefaultInfoBean.getMaybeResults().get(0).getDictName());
        mRbTwo.setText(upLoadDangersDefaultInfoBean.getMaybeResults().get(1).getDictName());
        mRbThree.setText(upLoadDangersDefaultInfoBean.getMaybeResults().get(2).getDictName());

        mRbOne.setChecked(true);
        upLoadDangersBean.setMaybeResult(upLoadDangersDefaultInfoBean.getMaybeResults().get(0).getId());

        mRbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    upLoadDangersBean.setMaybeResult(upLoadDangersDefaultInfoBean.getMaybeResults().get(0).getId());
                }
            }
        });
        mRbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    upLoadDangersBean.setMaybeResult(upLoadDangersDefaultInfoBean.getMaybeResults().get(1).getId());
                }
            }
        });
        mRbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    upLoadDangersBean.setMaybeResult(upLoadDangersDefaultInfoBean.getMaybeResults().get(2).getId());
                }
            }
        });

        mPhotoAdapter = new CarPhotoAdapter(this);
        mPhotoAdapter.addData(R.mipmap.ic_photo_add);
        mGvDangerPhoto.setAdapter(mPhotoAdapter);
        mGvDangerPhoto.setOnItemClickListener(this);
    }

    @Override
    public void upLoadSuccessful(String message) {
        Toast.makeText(UpLoadDangersActivity.this, message, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new EventMessage(EventKeys.COMPLETE_CHECK, null));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.tv_selector_team:
                if (selectorTeamDialog != null && !selectorTeamDialog.isShowing()) {
                    selectorTeamDialog.show();
                }
                break;
            case R.id.tv_check_type:
                if (checkTypeDialog != null && !checkTypeDialog.isShowing()) {
                    checkTypeDialog.show();
                }
                break;
            case R.id.tv_dangers_team:
                if (dangersTeamDialog != null && !dangersTeamDialog.isShowing()) {
                    dangersTeamDialog.show();
                }
                break;
            case R.id.tv_dangers_type:
                if (dangersTypeDialog != null && !dangersTypeDialog.isShowing()) {
                    dangersTypeDialog.show();
                }
                break;
            case R.id.tv_dangers_lv:
                if (dangersLvDialog != null && !dangersLvDialog.isShowing()) {
                    dangersLvDialog.show();
                }
                break;
            case R.id.tv_dangers_from:
                if (dangersFromDialog != null && !dangersFromDialog.isShowing()) {
                    dangersFromDialog.show();
                }
                break;
            case R.id.tv_check_time:
                new CustomTimePicker((TextView) v, null, null, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        String time = DateUtils.getInstance().getDate(date, DateUtils.DATE_FORMAT);
                        mTvCheckTime.setText(time);
                        upLoadDangersBean.setCheckDate(time);
                    }
                }, false).show();
                break;
            case R.id.tv_upload:
                upLoadDangers();
                break;
            default:
                break;
        }
    }

    private void upLoadDangers() {
        //受检部门
        if (upLoadDangersBean.getInspectDeptId() == null) {
            Toast.makeText(UpLoadDangersActivity.this, "请选择受检部门", Toast.LENGTH_SHORT).show();
            return;
        }
        //受检对象
        if (TextUtils.isEmpty(Objects.requireNonNull(mEtInspectObj.getText()).toString())) {
            Toast.makeText(UpLoadDangersActivity.this, "请输入受检对象", Toast.LENGTH_SHORT).show();
            return;
        } else {
            upLoadDangersBean.setInspectObj(mEtInspectObj.getText().toString());
        }
        //检查人
        if (TextUtils.isEmpty(Objects.requireNonNull(mEtCheckUserId.getText()).toString())) {
            Toast.makeText(UpLoadDangersActivity.this, "请输入检查人", Toast.LENGTH_SHORT).show();
            return;
        } else {
            upLoadDangersBean.setCheckUserId(mEtCheckUserId.getText().toString());
        }
        //检查日期
        if (upLoadDangersBean.getCheckDate() == null) {
            Toast.makeText(UpLoadDangersActivity.this, "请选择检查日期", Toast.LENGTH_SHORT).show();
            return;
        }
        //隐患名称
        if (TextUtils.isEmpty(Objects.requireNonNull(etDangersName.getText()).toString())) {
            Toast.makeText(UpLoadDangersActivity.this, "请输入隐患名称", Toast.LENGTH_SHORT).show();
            return;
        } else {
            upLoadDangersBean.setDangerName(etDangersName.getText().toString());
        }
        //隐患部位
        if (TextUtils.isEmpty(Objects.requireNonNull(mEtDangersPosition.getText()).toString())) {
            Toast.makeText(UpLoadDangersActivity.this, "请输入隐患部位", Toast.LENGTH_SHORT).show();
            return;
        } else {
            upLoadDangersBean.setDangerPosition(mEtDangersPosition.getText().toString());
        }
        upLoadDangersBean.setRemark(Objects.requireNonNull(mEtRemark.getText()).toString());
        String jsonString = JSON.toJSONString(upLoadDangersBean);
        Log.i("上报隐患上传的json", jsonString);
        mFilesList = new ArrayList<>();
        List<String> entities = mPhotoAdapter.getFilePaths();
        for (String entity : entities) {
            mFilesList.add(new File(entity));
        }
        mPresenter.upLoad(id, jsonString, mFilesList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Object object = mPhotoAdapter.getItem(position);
        if (object instanceof Integer) {
            if (mPhotoDialog == null) {
                mPhotoDialog = new PhotoDialog(this, UpLoadDangersActivity.this);
            }
            mPhotoDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && PictureConfig.CHOOSE_REQUEST == requestCode) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            for (LocalMedia localMedia : selectList) {
                mPhotoAdapter.addData(localMedia.getCompressPath());
            }
            mPhotoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void takePhoto() {
        new RxPermissions(this)
                .request(Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            PictureSelector.create(UpLoadDangersActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .compress(true)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        } else {
                            toast(0, "获取拍照权限失败！");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void album() {
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            int size = mPhotoAdapter.getCount() - 1;
                            PictureSelector.create(UpLoadDangersActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .selectionMode(PictureConfig.MULTIPLE)
                                    .maxSelectNum(4 - size)
                                    .compress(true)
                                    .forResult(PictureConfig.CHOOSE_REQUEST);
                        } else {
                            toast(0, "获取拍照权限失败！");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
