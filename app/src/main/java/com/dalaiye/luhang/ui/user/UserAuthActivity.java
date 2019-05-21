package com.dalaiye.luhang.ui.user;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.contract.user.AuthContract;
import com.dalaiye.luhang.contract.user.impl.AuthPresenter;
import com.dalaiye.luhang.widget.dialog.PhotoDialog;
import com.gfc.library.config.ECLibrary;
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

/**
 * @author admin
 * @date 2019/4/10
 * @function 认证页面
 **/
public class UserAuthActivity extends BaseMvpActivity<AuthContract.IAuthPresenter> implements
        AuthContract.IAuthView, View.OnClickListener, PhotoDialog.PhotoListener {

    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private android.support.v7.widget.AppCompatImageView mIvUploadPhoto;
    private android.support.v7.widget.AppCompatTextView mTvAuth;
    private PhotoDialog mPhotoDialog;
    private String imgPath;

    @Override
    protected AuthContract.IAuthPresenter createPresenter() {
        return new AuthPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_user_auth;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        String authUrl = getIntent().getStringExtra("authUrl");
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mIvUploadPhoto = findViewById(R.id.iv_upload_photo);
        mTvAuth = findViewById(R.id.tv_auth);
        if(TextUtils.isEmpty(authUrl)) {
            mTvAuth.setText("立即认证");
        }else {
            mTvAuth.setText("修改认证");
        }
        Glide.with(ECLibrary.getApplication())
                .load(authUrl)
                .into(mIvUploadPhoto);

        mIvUploadPhoto.setOnClickListener(this);
        mTopBarBack.setOnClickListener(this);
        mTopBarTitle.setText("认证");
        mTvAuth.setEnabled(true);
        mTvAuth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.iv_upload_photo:
                if (mPhotoDialog == null) {
                    mPhotoDialog = new PhotoDialog(this, UserAuthActivity.this);
                }
                mPhotoDialog.show();
                break;
            case R.id.tv_auth:
                if (!TextUtils.isEmpty(imgPath)) {
                    mPresenter.uploadAuthPhoto(AccountHelper.getInstance().getUserId(), imgPath);
                } else {
                    Toast.makeText(UserAuthActivity.this, "请重新上传图片", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    if (localMedia != null && localMedia.size() > 0) {
                        imgPath = localMedia.get(0).getCompressPath();
                        Glide.with(this).asBitmap().load(imgPath).into(mIvUploadPhoto);
                    }
                    break;
                default:
                    break;
            }
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
                            PictureSelector.create(UserAuthActivity.this)
                                    .openCamera(PictureMimeType.ofImage())
                                    .enableCrop(true)
                                    .compress(true)
                                    .isDragFrame(true)
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
                            PictureSelector.create(UserAuthActivity.this)
                                    .openGallery(PictureMimeType.ofImage())
                                    .selectionMode(PictureConfig.MULTIPLE)
                                    .maxSelectNum(1)
                                    .isDragFrame(true)
                                    .enableCrop(true)
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
    public void uploadAuthPhotoSuccessful() {
//        startActivity(new Intent(UserAuthActivity.this, AppMainActivity.class));
        finish();
    }
}
