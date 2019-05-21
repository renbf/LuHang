package com.dalaiye.luhang.ui.train.exam;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.constant.ApiService;
import com.dalaiye.luhang.contract.train.DiscernContract;
import com.dalaiye.luhang.contract.train.impl.DiscernPresenter;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.user.AccountHelper;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.dalaiye.luhang.ui.train.exam.ExamDetailsActivity.EXAM_USER_PAPER_ID;
import static com.dalaiye.luhang.ui.train.exam.ExamDetailsActivity.EXAM_USER_PAPER_URL;

public class DiscernActivity extends BaseMvpActivity<DiscernContract.IDiscernPresenter> implements
        DiscernContract.IDiscernView, View.OnClickListener {
    private AppCompatImageView topBarBack;
    private AppCompatTextView topBarTitle;
    private AppCompatTextView tvTakePhoto;
    private AppCompatTextView tvBeginAuth;
    private AppCompatImageView ivFacePhoto;
    private String facePhotoAddress;
    private String mUrl;
    private String mUserPaperId;

    @Override
    protected DiscernContract.IDiscernPresenter createPresenter() {
        return new DiscernPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_train_discern;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mUrl = getIntent().getStringExtra(EXAM_USER_PAPER_URL);
        mUserPaperId = getIntent().getStringExtra(EXAM_USER_PAPER_ID);

        topBarBack = findViewById(R.id.top_bar_back);
        topBarTitle = findViewById(R.id.top_bar_title);
        tvTakePhoto = findViewById(R.id.tv_take_photo);
        tvBeginAuth = findViewById(R.id.tv_begin_auth);
        ivFacePhoto = findViewById(R.id.iv_face_photo);

        topBarTitle.setText("拍照认证");
        topBarBack.setOnClickListener(this);
        tvTakePhoto.setOnClickListener(this);
        tvBeginAuth.setOnClickListener(this);
    }

    @Override
    public void faceAuthSuccessful() {
        Intent intent = new Intent(this, ExamDetailsActivity.class);
        intent.putExtra(ExamDetailsActivity.EXAM_USER_PAPER_ID, mUserPaperId);
        intent.putExtra(ExamDetailsActivity.EXAM_USER_PAPER_URL, mUrl);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.tv_take_photo:
                takePhoto();
                break;
            case R.id.tv_begin_auth:
                if (!TextUtils.isEmpty(facePhotoAddress)) {
                    mPresenter.faceAuth(AccountHelper.getInstance().getUserId(), facePhotoAddress);
                } else {
                    Toast.makeText(DiscernActivity.this, "请先进行拍照！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && PictureConfig.CHOOSE_REQUEST == requestCode) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            for (LocalMedia localMedia : selectList) {
                facePhotoAddress = localMedia.getCompressPath();
                Glide.with(this)
                        .load(facePhotoAddress)
                        .apply(new RequestOptions().circleCrop())
                        .into(ivFacePhoto);
            }
        }
    }

    private void takePhoto() {
        new RxPermissions(this)
                .request(Manifest.permission.CAMERA
                        , Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            PictureSelector.create(DiscernActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .enableCrop(true)
                                    .circleDimmedLayer(true)
                                    .showCropFrame(false)
                                    .showCropGrid(false)
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
