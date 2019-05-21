package com.dalaiye.luhang.ui.car.check;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CarPhotoAdapter;
import com.dalaiye.luhang.contract.car.UploadCheckResultContract;
import com.dalaiye.luhang.contract.car.impl.UploadCheckResultPresenter;
import com.dalaiye.luhang.utils.DateUtils;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.dalaiye.luhang.widget.dialog.PhotoDialog;
import com.gfc.library.mvp.BaseMvpActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 隐患关闭，上传隐患验收结果跳转
 **/
public class UploadCheckResultActivity extends BaseMvpActivity<UploadCheckResultContract.IUploadCheckResultPresenter>
        implements UploadCheckResultContract.IUploadCheckResultView, View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemClickListener, PhotoDialog.PhotoListener {
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private android.widget.RadioButton mRbPassThrough;
    private android.widget.RadioButton mRbNoPass;
    private com.gfc.library.widget.grid.ScrollGridView mGvAcceptancePhoto;
    private android.support.v7.widget.AppCompatTextView mTvDangersState;
    private android.support.v7.widget.LinearLayoutCompat mLlRemarks;
    private CarPhotoAdapter mPhotoAdapter;
    private PhotoDialog mPhotoDialog;
    private String id;
    private int checkAcceptResult;
    private android.support.v7.widget.AppCompatTextView mTvCheckAcceptDate;
    private android.support.v7.widget.AppCompatEditText mEdRemark;
    private List<File> mFilesList;

    @Override
    protected UploadCheckResultContract.IUploadCheckResultPresenter createPresenter() {
        return new UploadCheckResultPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_upload_check_result;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mRbPassThrough = findViewById(R.id.rb_pass_through);
        mRbNoPass = findViewById(R.id.rb_no_pass);
        mGvAcceptancePhoto = findViewById(R.id.gv_acceptance_photo);
        mLlRemarks = findViewById(R.id.ll_remarks);
        mTvDangersState = findViewById(R.id.tv_dangers_state);
        mTvCheckAcceptDate = findViewById(R.id.tv_check_accept_date);
        mEdRemark = findViewById(R.id.ed_remark);
        mEdRemark.setFilters(new InputFilter[]{new EmojiFilter(this)});

        mTvCheckAcceptDate.setText(DateUtils.getInstance().getToday(DateUtils.DATE_FORMAT));
        mRbPassThrough.setChecked(true);
        checkAcceptResult = 1;

        mTopBarBack.setOnClickListener(this);
        mTopBarTitle.setText("隐患验收");
        mRbPassThrough.setOnCheckedChangeListener(this);
        mTvDangersState.setOnClickListener(this);

        mPhotoAdapter = new CarPhotoAdapter(this);
        mPhotoAdapter.addData(R.mipmap.ic_photo_add);
        mGvAcceptancePhoto.setAdapter(mPhotoAdapter);
        mGvAcceptancePhoto.setOnItemClickListener(this);

        mRbPassThrough.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkAcceptResult = 1;
                }
            }
        });
        mRbNoPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkAcceptResult = 0;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.tv_dangers_state:
                String remark = Objects.requireNonNull(mEdRemark.getText()).toString();
                mFilesList = new ArrayList<>();
                List<String> entities = mPhotoAdapter.getFilePaths();
                for (String entity : entities) {
                    mFilesList.add(new File(entity));
                }
                mPresenter.closeDangers(id, mTvCheckAcceptDate.getText().toString(), String.valueOf(checkAcceptResult), remark, mFilesList);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mTvDangersState.setText("关闭隐患");
            mLlRemarks.setVisibility(View.GONE);
        } else {
            mTvDangersState.setText("重新整改");
            mLlRemarks.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Object object = mPhotoAdapter.getItem(position);
        if (object instanceof Integer) {
            if (mPhotoDialog == null) {
                mPhotoDialog = new PhotoDialog(this, UploadCheckResultActivity.this);
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
                            PictureSelector.create(UploadCheckResultActivity.this)
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
                            PictureSelector.create(UploadCheckResultActivity.this)
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

    @Override
    public void closeDangersSuccessful(String message) {
        Toast.makeText(UploadCheckResultActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
}
