package com.dalaiye.luhang.ui.car.log;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CarPhotoAdapter;
import com.dalaiye.luhang.adapter.CheckOptionAdapter;
import com.dalaiye.luhang.bean.log.CheckBean;
import com.dalaiye.luhang.bean.log.LogEndData;
import com.dalaiye.luhang.bean.log.LogEndParams;
import com.dalaiye.luhang.constant.Constant;
import com.dalaiye.luhang.contract.car.LogDriveContract;
import com.dalaiye.luhang.contract.car.impl.DriveEndWritePresenter;
import com.dalaiye.luhang.ui.user.UserSignActivity;
import com.dalaiye.luhang.utils.DateUtils;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.dalaiye.luhang.widget.dialog.PhotoDialog;
import com.gfc.library.mvp.BaseMvpFragment;
import com.gfc.library.utils.user.AccountHelper;
import com.gfc.library.utils.watcher.EnabledTextWatcher;
import com.gfc.library.widget.grid.ScrollGridView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Date;
import java.util.List;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author admin
 * @date 2019/4/10
 * @function 行车后写入
 **/
public class DriveEndWriteFragment extends BaseMvpFragment<LogDriveContract.IDriveEndWritePresenter>
        implements LogDriveContract.IDriveEndWriteView, TagFlowLayout.OnSelectListener, AdapterView.OnItemClickListener
        , PhotoDialog.PhotoListener, View.OnClickListener, EnabledTextWatcher.EnabledListener {

    private ILogDetailsListener mDetailsListener;

    private View mLoadingLayout;
    private TagFlowLayout mEndCheckLayout;
    private AppCompatEditText mEtEndFaultHandling;
    private AppCompatImageView mIvEndSign;
    private AppCompatEditText mEtEndStartCheckSite;
    private AppCompatEditText mEtEndStartCheckDesc;
    private AppCompatEditText mEtEndMiddleCheckSite;
    private AppCompatEditText mEtEndMiddleCheckDesc;
    private ScrollGridView mEndCarGridView;
    private AppCompatTextView mTvEndSave;
    //上传参数
    private LogEndParams mEndParams;
    //检查事项
    private CheckOptionAdapter mOptionAdapter;
    //行车照片
    private CarPhotoAdapter mPhotoAdapter;
    //拍照
    private PhotoDialog mPhotoDialog;
    //签名照片
    private String mSignImage;

    @Override
    public LogDriveContract.IDriveEndWritePresenter createPresenter() {
        return new DriveEndWritePresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_log_drive_end_write;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        if (getContext() instanceof LogDriveDetailsActivity) {
            mDetailsListener = (ILogDetailsListener) getContext();
        }

        mLoadingLayout = mRootView.findViewById(R.id.loading_layout);
        mEndCheckLayout = mRootView.findViewById(R.id.end_check_layout);
        mEtEndFaultHandling = mRootView.findViewById(R.id.et_end_fault_handling);
        mEtEndFaultHandling.setFilters(new InputFilter[]{new EmojiFilter(getContext())});

        mIvEndSign = mRootView.findViewById(R.id.iv_end_sign);
        mEtEndStartCheckSite = mRootView.findViewById(R.id.et_end_start_check_site);
        mEtEndStartCheckSite.setFilters(new InputFilter[]{new EmojiFilter(getContext())});

        mEtEndStartCheckDesc = mRootView.findViewById(R.id.et_end_start_check_desc);
        mEtEndStartCheckDesc.setFilters(new InputFilter[]{new EmojiFilter(getContext())});

        mEtEndMiddleCheckSite = mRootView.findViewById(R.id.et_end_middle_check_site);
        mEtEndMiddleCheckSite.setFilters(new InputFilter[]{new EmojiFilter(getContext())});

        mEtEndMiddleCheckDesc = mRootView.findViewById(R.id.et_end_middle_check_desc);
        mEtEndMiddleCheckDesc.setFilters(new InputFilter[]{new EmojiFilter(getContext())});

        mEndCarGridView = mRootView.findViewById(R.id.end_car_grid_view);
        mTvEndSave = mRootView.findViewById(R.id.tv_end_save);

        mEtEndFaultHandling.addTextChangedListener(new EnabledTextWatcher(this));
        mEtEndStartCheckSite.addTextChangedListener(new EnabledTextWatcher(this));
        mEtEndStartCheckDesc.addTextChangedListener(new EnabledTextWatcher(this));
        mEtEndMiddleCheckSite.addTextChangedListener(new EnabledTextWatcher(this));
        mEtEndMiddleCheckDesc.addTextChangedListener(new EnabledTextWatcher(this));

        mIvEndSign.setOnClickListener(this);
        mTvEndSave.setOnClickListener(this);

        mPresenter.getInitDate(AccountHelper.getInstance().getUserId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_end_sign:
                //签名
                startActivityForResult(new Intent(getContext(), UserSignActivity.class), UserSignActivity.USER_SIGN_CODE);
                break;
            case R.id.tv_end_save:
                mPresenter.submitParams(mEndParams,mPhotoAdapter.getFilePaths(),mSignImage);
                break;
            default:
                break;
        }
    }

    @Override
    public void setInitData(LogEndData data) {
        mEndParams = new LogEndParams(AccountHelper.getInstance().getUserId());
        mEndParams.setDriverLogId(mDetailsListener.getLogId());
        //检查事项
        mEndParams.setCarCheckProjectList(data.getCarCheckProject());
        mOptionAdapter = new CheckOptionAdapter(data.getCarCheckProject());
        mEndCheckLayout.setAdapter(mOptionAdapter);
        mEndCheckLayout.setOnSelectListener(this);
        //行车图片
        mPhotoAdapter = new CarPhotoAdapter(getContext());
        mPhotoAdapter.addData(R.mipmap.ic_photo_add);
        mEndCarGridView.setAdapter(mPhotoAdapter);
        mEndCarGridView.setOnItemClickListener(this);

        mLoadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void submitParamsSuccess() {
        mDetailsListener.getActivity().finish();
    }


    @Override
    public void onSelected(Set<Integer> selectPosSet) {
        checkEnable();
    }

    @Override
    public void checkEnable() {
        boolean isEnable = true;
        mEndParams.setWriteTime(DateUtils.getInstance().getDate(new Date(), DateUtils.DATE_FORMAT));//填写时间
        //检查事项
        if (mOptionAdapter != null) {
            //检查事项是否有选中 先重置为false
            isEnable = false;
            for (CheckBean bean : mOptionAdapter.getOptionBeans()) {
                if (bean.getStatus() == Constant.CHECK_OPTION_SELECTED) {
                    //如果有选中在置为true
                    isEnable = true;
                }
            }
        }
        if (TextUtils.isEmpty(mSignImage)) {
            isEnable = false;
        }
        mEndParams.setFaultHandling(mEtEndFaultHandling.getText().toString().trim());//故障处理
        mEndParams.setStartFromStation(mEtEndStartCheckSite.getText().toString().trim());//始发站
        mEndParams.setStartFromCheck(mEtEndStartCheckDesc.getText().toString().trim());//始发站例检
        mEndParams.setEnRouteAddress(mEtEndMiddleCheckSite.getText().toString().trim());//途中站
        mEndParams.setEnRouteCheck(mEtEndMiddleCheckDesc.getText().toString().trim());//途中站例检

        mTvEndSave.setEnabled(isEnable);
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
            checkEnable();
        } else if (requestCode == UserSignActivity.USER_SIGN_CODE && resultCode == UserSignActivity.USER_SIGN_CODE) {
            mSignImage = data.getStringExtra(UserSignActivity.USER_SIGN_PATH);
            Glide.with(this)
                    .load(mSignImage)
                    .into(mIvEndSign);
            checkEnable();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Object object = mPhotoAdapter.getItem(position);
        if (object instanceof Integer) {
            if (mPhotoDialog == null) {
                mPhotoDialog = new PhotoDialog(getContext(), DriveEndWriteFragment.this);
            }
            mPhotoDialog.show();
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
                            PictureSelector.create(DriveEndWriteFragment.this)
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
                            PictureSelector.create(DriveEndWriteFragment.this)
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
