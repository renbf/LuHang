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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CarPhotoAdapter;
import com.dalaiye.luhang.adapter.CheckOptionAdapter;
import com.dalaiye.luhang.bean.log.CheckBean;
import com.dalaiye.luhang.bean.log.LogInData;
import com.dalaiye.luhang.bean.log.LogInParams;
import com.dalaiye.luhang.constant.Constant;
import com.dalaiye.luhang.contract.car.LogDriveContract;
import com.dalaiye.luhang.contract.car.impl.DriveInWritePresenter;
import com.dalaiye.luhang.ui.user.UserSignActivity;
import com.dalaiye.luhang.utils.DateUtils;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.dalaiye.luhang.widget.dialog.CustomTimePicker;
import com.dalaiye.luhang.widget.dialog.CustomTimePicker2;
import com.dalaiye.luhang.widget.dialog.LogHandoverDialog;
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
 * @function 行车中写入
 **/
public class DriveInWriteFragment extends BaseMvpFragment<LogDriveContract.IDriveInWritePresenter>
        implements LogDriveContract.IDriveInWriteView, TagFlowLayout.OnSelectListener
        , AdapterView.OnItemClickListener, PhotoDialog.PhotoListener, View.OnClickListener
        , EnabledTextWatcher.EnabledListener, LogHandoverDialog.IHandoverListener {

    private RelativeLayout mLoadingLayout;
    private AppCompatTextView mTvInFillDate;
    private TagFlowLayout mInCheckLayout;
    private AppCompatEditText mEtInChangeLocation;
    private AppCompatTextView mTvInCarStopDate;
    private AppCompatEditText mEtInRestLocation;
    private AppCompatTextView mTvInCarStartDate;
    private AppCompatEditText mEtInFaultHandling;
    private ScrollGridView mInCarGridView;
    private AppCompatImageView mIvInSign;
    private AppCompatTextView mTvInSave;
    private AppCompatTextView mTvInHandover;

    private ILogDetailsListener mDetailsListener;
    //上传参数
    private LogInParams mInParams;
    //行车照片
    private CarPhotoAdapter mPhotoAdapter;
    //检查项
    private CheckOptionAdapter mOptionAdapter;
    //签名图片
    private String mSignImage;
    //选择照片
    private PhotoDialog mPhotoDialog;
    //停车时间
    private Date mStopDate;
    //发车时间
    private Date mStartDate;
    //交接dialog
    private LogHandoverDialog mHandoverDialog;

    @Override
    public LogDriveContract.IDriveInWritePresenter createPresenter() {
        return new DriveInWritePresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_log_drive_in_write;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        if (getContext() instanceof LogDriveDetailsActivity) {
            mDetailsListener = (ILogDetailsListener) getContext();
        }

        mLoadingLayout = mRootView.findViewById(R.id.loading_layout);
        mTvInFillDate = mRootView.findViewById(R.id.tv_in_fill_date);
        mInCheckLayout = mRootView.findViewById(R.id.in_check_layout);
        mEtInChangeLocation = mRootView.findViewById(R.id.et_in_change_location);
        mEtInChangeLocation.setFilters(new InputFilter[]{new EmojiFilter(getContext())});

        mTvInCarStopDate = mRootView.findViewById(R.id.tv_in_car_stop_date);
        mEtInRestLocation = mRootView.findViewById(R.id.et_in_rest_location);
        mEtInRestLocation.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mTvInCarStartDate = mRootView.findViewById(R.id.tv_in_car_start_date);
        mEtInFaultHandling = mRootView.findViewById(R.id.et_in_fault_handling);
        mEtInFaultHandling.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mInCarGridView = mRootView.findViewById(R.id.in_car_grid_view);
        mIvInSign = mRootView.findViewById(R.id.iv_in_sign);
        mTvInSave = mRootView.findViewById(R.id.tv_in_save);
        mTvInHandover = mRootView.findViewById(R.id.tv_in_handover);

        mEtInChangeLocation.addTextChangedListener(new EnabledTextWatcher(this));
        mEtInRestLocation.addTextChangedListener(new EnabledTextWatcher(this));
        mEtInFaultHandling.addTextChangedListener(new EnabledTextWatcher(this));

        mTvInCarStopDate.setOnClickListener(this);
        mTvInCarStartDate.setOnClickListener(this);
        mIvInSign.setOnClickListener(this);
        mTvInSave.setOnClickListener(this);
        mTvInHandover.setOnClickListener(this);

        mPresenter.getInitData(AccountHelper.getInstance().getUserId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_in_car_stop_date:
                new CustomTimePicker2(v, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        if (mStartDate != null) {
                            if (mStartDate.getTime() > date.getTime()) {
                                mStopDate = date;
                                mTvInCarStopDate.setText(DateUtils.getInstance().getDate(mStopDate, DateUtils.TIME_FORMAT));
                                checkEnable();
                            } else {
                                toast(0, "停车时间不能超过发车时间！");
                            }
                        } else {
                            mStopDate = date;
                            mTvInCarStopDate.setText(DateUtils.getInstance().getDate(mStopDate, DateUtils.TIME_FORMAT));
                            checkEnable();
                        }
                    }
                }, CustomTimePicker2.TIME_LOG_DRIVE_IN).show();
                break;
            case R.id.tv_in_car_start_date:

                new CustomTimePicker2(v, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        if (mStopDate != null){
                            if (mStopDate.getTime()<date.getTime()){
                                mStartDate = date;
                                mTvInCarStartDate.setText(DateUtils.getInstance().getDate(mStartDate, DateUtils.TIME_FORMAT));
                                checkEnable();
                            }else {
                                toast(0, "发车时间不能小于停车时间！");
                            }
                        }else {
                            mStartDate = date;
                            mTvInCarStartDate.setText(DateUtils.getInstance().getDate(mStartDate, DateUtils.TIME_FORMAT));
                            checkEnable();
                        } 
                    }
                }, CustomTimePicker2.TIME_LOG_DRIVE_IN).show();
                break;
            case R.id.iv_in_sign:
                //签名
                startActivityForResult(new Intent(getContext(), UserSignActivity.class), UserSignActivity.USER_SIGN_CODE);
                break;
            case R.id.tv_in_save:
                mPresenter.submitParams(mInParams, mPhotoAdapter.getFilePaths(), mSignImage);
                break;
            case R.id.tv_in_handover:
                if (mHandoverDialog == null) {
                    mHandoverDialog = new LogHandoverDialog(getContext(), this);
                }
                mHandoverDialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void setInitData(LogInData data) {
        mInParams = new LogInParams(AccountHelper.getInstance().getUserId());
        mInParams.setDriverLogId(mDetailsListener.getLogId());
        //填写日期
        mTvInFillDate.setText(DateUtils.getInstance().getToday(DateUtils.DATE_FORMAT));
        //检查事项
        mInParams.setCarCheckProjectList(data.getCarCheckProject());//检查事项
        mOptionAdapter = new CheckOptionAdapter(data.getCarCheckProject());
        mInCheckLayout.setAdapter(mOptionAdapter);
        mInCheckLayout.setOnSelectListener(this);
        //行车图片
        mPhotoAdapter = new CarPhotoAdapter(getContext());
        mPhotoAdapter.addData(R.mipmap.ic_photo_add);
        mInCarGridView.setAdapter(mPhotoAdapter);
        mInCarGridView.setOnItemClickListener(this);

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
                    .into(mIvInSign);
            checkEnable();
        }
    }

    @Override
    public void checkEnable() {
        boolean isEnable = true;
        mInParams.setWriteTime(mTvInFillDate.getText().toString().trim());//填写日期
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
        mInParams.setChangeAddress(mEtInRestLocation.getText().toString().trim());//换驾休息地点
        mInParams.setStopCarTime(mTvInCarStopDate.getText().toString().trim());//停车时间
        mInParams.setRestAddress(mEtInRestLocation.getText().toString().trim());  //休息地点
        mInParams.setStartCarTime(mTvInCarStartDate.getText().toString().trim());//发车时间
        mInParams.setTroubleShooting(mEtInFaultHandling.getText().toString().trim());//故障处理

        if (TextUtils.isEmpty(mSignImage)) {
            //签名照片为空
            isEnable = false;
        }
        mTvInSave.setEnabled(isEnable);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Object object = mPhotoAdapter.getItem(position);
        if (object instanceof Integer) {
            if (mPhotoDialog == null) {
                mPhotoDialog = new PhotoDialog(getContext(), DriveInWriteFragment.this);
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
                            PictureSelector.create(DriveInWriteFragment.this)
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
                            PictureSelector.create(DriveInWriteFragment.this)
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
    public void onHandoverMessage(String remarks) {
        mPresenter.handover(mDetailsListener.getLogId(), remarks);
    }
}
