package com.dalaiye.luhang.ui.car.check;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CarPhotoAdapter;
import com.dalaiye.luhang.contract.car.DangersAccpetRectificationContract;
import com.dalaiye.luhang.contract.car.impl.IDangersAccpetRectificationPresenter;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.dalaiye.luhang.widget.dialog.PhotoDialog;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.mvp.BaseMvpActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 隐患整改提交页面
 **/
public class DangersAccpetRectificationActivity extends BaseMvpActivity<DangersAccpetRectificationContract.IDangersAccpetRectificationPresenter>
        implements DangersAccpetRectificationContract.IDangersAccpetRectificationView, View.OnClickListener,
        AdapterView.OnItemClickListener, PhotoDialog.PhotoListener {

    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private com.gfc.library.widget.grid.ScrollGridView mGvDangerPhoto;
    private CarPhotoAdapter mPhotoAdapter;
    private PhotoDialog mPhotoDialog;
    private android.support.v7.widget.AppCompatTextView mTvSubmit;
    private List<File> mFilesList;
    private android.support.v7.widget.AppCompatEditText mEtDoChangeStep;
    private android.support.v7.widget.AppCompatEditText mEtDoChangeCapital;
    private String id;
    @Override
    protected DangersAccpetRectificationContract.IDangersAccpetRectificationPresenter createPresenter() {
        return new IDangersAccpetRectificationPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_car_dangers_accpet_rectification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mGvDangerPhoto = findViewById(R.id.gv_danger_photo);
        mTvSubmit = findViewById(R.id.tv_submit);
        mEtDoChangeStep = findViewById(R.id.et_do_change_step);
        mEtDoChangeStep.setFilters(new InputFilter[]{new EmojiFilter(this)});
        mEtDoChangeCapital = findViewById(R.id.et_do_change_capital);
        mEtDoChangeCapital.setFilters(new InputFilter[]{new EmojiFilter(this)});

        mTopBarBack.setOnClickListener(this);
        mTvSubmit.setOnClickListener(this);
        mTopBarTitle.setText("隐患整改");

        mPhotoAdapter = new CarPhotoAdapter(this);
        mPhotoAdapter.addData(R.mipmap.ic_photo_add);
        mGvDangerPhoto.setAdapter(mPhotoAdapter);
        mGvDangerPhoto.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back :
                finish();
                break;
            case R.id.tv_submit:
                String changeStep = Objects.requireNonNull(mEtDoChangeStep.getText()).toString();
                String changeCapital = Objects.requireNonNull(mEtDoChangeCapital.getText()).toString();
                if(TextUtils.isEmpty(changeStep)) {
                    Toast.makeText(DangersAccpetRectificationActivity.this, "整改措施不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(changeCapital)) {
                    Toast.makeText(DangersAccpetRectificationActivity.this, "整改资金不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                mFilesList = new ArrayList<>();
                List<String> entities = mPhotoAdapter.getFilePaths();
                for (String entity : entities) {
                        mFilesList.add(new File(  entity));
                }
                mPresenter.rectificationSubmit(id,changeStep,changeCapital,mFilesList);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Object object = mPhotoAdapter.getItem(position);
        if (object instanceof Integer) {
            if (mPhotoDialog == null) {
                mPhotoDialog = new PhotoDialog(this, DangersAccpetRectificationActivity.this);
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
                            PictureSelector.create(DangersAccpetRectificationActivity.this)
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
                            PictureSelector.create(DangersAccpetRectificationActivity.this)
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
    public void rectificationSubmitSuccessful(String message) {
        Toast.makeText(DangersAccpetRectificationActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
        EventBus.getDefault().post(new EventMessage(EventKeys.DANGERS_SUBMIT_REFUSE,null));
    }
}
