package com.dalaiye.luhang.ui.car.log;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.adapter.CarPhotoAdapter;
import com.dalaiye.luhang.adapter.CheckOptionAdapter;
import com.dalaiye.luhang.bean.base.BaseListBean;
import com.dalaiye.luhang.bean.log.CheckBean;
import com.dalaiye.luhang.bean.log.LogStartData;
import com.dalaiye.luhang.bean.log.LogStartParams;
import com.dalaiye.luhang.constant.Constant;
import com.dalaiye.luhang.contract.car.LogDriveContract;
import com.dalaiye.luhang.contract.car.impl.DriveStartWritePresenter;
import com.dalaiye.luhang.ui.user.UserSignActivity;
import com.dalaiye.luhang.utils.DateUtils;
import com.dalaiye.luhang.utils.EmojiFilter;
import com.dalaiye.luhang.widget.dialog.CustomTimePicker;
import com.dalaiye.luhang.widget.dialog.ListViewDialog;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author admin
 * @date 2019/4/10
 * @function 行车前写入
 **/
public class DriveStartWriteFragment extends BaseMvpFragment<LogDriveContract.IDriveStartWritePresenter>
        implements LogDriveContract.IDriveStartWriteView, View.OnClickListener, EnabledTextWatcher.EnabledListener
        , TagFlowLayout.OnSelectListener, AdapterView.OnItemClickListener, PhotoDialog.PhotoListener {

    private RelativeLayout mLoadingLayout;
    private AppCompatTextView mTvStartFillDate;
    private AppCompatEditText mEtStartCarPlate;
    private AppCompatEditText mEtStartCarLoadNuclear;
    private AppCompatEditText mEtStartCarLoadActual;
    private AppCompatTextView mTvStartGoodsName;
    private LinearLayoutCompat mGoodsTypeLayout;
    private AppCompatTextView mTvStartGoodsType;
    private AppCompatTextView mTvStartDriverFirst;
    private AppCompatTextView mTvStartDriverSecond;
    private LinearLayoutCompat mEscortLayout;
    private AppCompatTextView mTvStartEscortName;
    private AppCompatEditText mEtStartTemperature;
    private RadioGroup mWeatherRg;
    private RadioButton mRbStartFine;
    private RadioButton mRbStartYin;
    private RadioButton mRbStartRain;
    private RadioButton mRbStartFog;
    private RadioButton mRbStartSnow;
    private RadioButton mRbStartFrozen;
    private AppCompatTextView mTvStartShipmentDate;
    private AppCompatEditText mEtStartShipmentLocation;
    private AppCompatEditText mEtStartDestination;
    private AppCompatEditText mEtStartMileage;
    private AppCompatTextView mTvStartAtDate;
    private TagFlowLayout mStartCheckLayout;
    private AppCompatEditText mEtStartNotMeet;
    private AppCompatTextView mTvStartCarConclusion;
    private AppCompatEditText mEtStartRemarks;
    private ScrollGridView mStartCarGridView;
    private AppCompatImageView mIvStartSign;
    private AppCompatTextView mTvStartSave;

    private ILogDetailsListener mDetailsListener;

    //基础参数
    private LogStartParams.LogStartBasisParams mStartBasisParams;
    //行车前数据
    private LogStartParams.LogStartDriveParams mStartDriveParams;
    //签名照片
    private String mSignImage = null;
    //行车照片
    private CarPhotoAdapter mPhotoAdapter = null;
    //获取dialog
    private ListViewDialog mGoodsDialog = null;
    //驾驶员2dialog
    private ListViewDialog mDriverSecondDialog = null;
    //押运员dialog
    private ListViewDialog mEscortDialog = null;
    //结论dialog
    private ListViewDialog mCarConclusionDialog = null;
    //照片dialog
    private PhotoDialog mPhotoDialog;
    //检查事项的adapter
    private CheckOptionAdapter mOptionAdapter;
    //启运时间
    private Date mStartDate = null;
    //到达日期
    private Date mEndDate = null;


    @Override
    public LogDriveContract.IDriveStartWritePresenter createPresenter() {
        return new DriveStartWritePresenter();
    }

    @Override
    protected Object layoutRes() {
        return R.layout.fragment_car_log_drive_start_write;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        if (getContext() instanceof LogDriveDetailsActivity) {
            mDetailsListener = (ILogDetailsListener) getContext();
        }

        mLoadingLayout = mRootView.findViewById(R.id.loading_layout);
        mTvStartFillDate = mRootView.findViewById(R.id.tv_start_fill_date);
        mEtStartCarPlate = mRootView.findViewById(R.id.et_start_car_plate);
        mEtStartCarLoadNuclear = mRootView.findViewById(R.id.et_start_car_load_nuclear);
        mEtStartCarLoadActual = mRootView.findViewById(R.id.et_start_car_load_actual);
        mTvStartGoodsName = mRootView.findViewById(R.id.tv_start_goods_name);
        mGoodsTypeLayout = mRootView.findViewById(R.id.goods_type_layout);
        mTvStartGoodsType = mRootView.findViewById(R.id.tv_start_goods_type);
        mTvStartDriverFirst = mRootView.findViewById(R.id.tv_start_driver_first);
        mTvStartDriverSecond = mRootView.findViewById(R.id.tv_start_driver_second);
        mEscortLayout = mRootView.findViewById(R.id.escort_layout);
        mTvStartEscortName = mRootView.findViewById(R.id.tv_start_escort_name);
        mEtStartTemperature = mRootView.findViewById(R.id.et_start_temperature);
        mWeatherRg = mRootView.findViewById(R.id.weather_rg);
        mRbStartFine = mRootView.findViewById(R.id.rb_start_fine);
        mRbStartYin = mRootView.findViewById(R.id.rb_start_yin);
        mRbStartRain = mRootView.findViewById(R.id.rb_start_rain);
        mRbStartFog = mRootView.findViewById(R.id.rb_start_fog);
        mRbStartSnow = mRootView.findViewById(R.id.rb_start_snow);
        mRbStartFrozen = mRootView.findViewById(R.id.rb_start_frozen);
        mTvStartShipmentDate = mRootView.findViewById(R.id.tv_start_shipment_date);
        mEtStartShipmentLocation = mRootView.findViewById(R.id.et_start_shipment_location);
        mEtStartDestination = mRootView.findViewById(R.id.et_start_destination);
        mEtStartMileage = mRootView.findViewById(R.id.et_start_mileage);
        mTvStartAtDate = mRootView.findViewById(R.id.tv_start_at_date);
        mStartCheckLayout = mRootView.findViewById(R.id.start_check_layout);
        mEtStartNotMeet = mRootView.findViewById(R.id.et_start_not_meet);
        mTvStartCarConclusion = mRootView.findViewById(R.id.tv_start_car_conclusion);
        mEtStartRemarks = mRootView.findViewById(R.id.et_start_remarks);
        mStartCarGridView = mRootView.findViewById(R.id.start_car_grid_view);
        mIvStartSign = mRootView.findViewById(R.id.iv_start_sign);
        mTvStartSave = mRootView.findViewById(R.id.tv_start_save);

        mEtStartCarPlate.addTextChangedListener(new EnabledTextWatcher(this));
        mEtStartCarPlate.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mEtStartCarLoadNuclear.addTextChangedListener(new EnabledTextWatcher(this));
        mEtStartCarLoadNuclear.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mEtStartCarLoadActual.addTextChangedListener(new EnabledTextWatcher(this));
        mEtStartCarLoadActual.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mEtStartTemperature.addTextChangedListener(new EnabledTextWatcher(this));
        mEtStartTemperature.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mEtStartDestination.addTextChangedListener(new EnabledTextWatcher(this));
        mEtStartDestination.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mEtStartMileage.addTextChangedListener(new EnabledTextWatcher(this));
        mEtStartMileage.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mEtStartNotMeet.addTextChangedListener(new EnabledTextWatcher(this));
        mEtStartNotMeet.setFilters(new InputFilter[]{new EmojiFilter(getContext())});
        mEtStartRemarks.addTextChangedListener(new EnabledTextWatcher(this));
        mEtStartRemarks.setFilters(new InputFilter[]{new EmojiFilter(getContext())});

        mTvStartGoodsName.setOnClickListener(this);
        mTvStartGoodsType.setOnClickListener(this);
        mTvStartDriverSecond.setOnClickListener(this);
        mTvStartEscortName.setOnClickListener(this);
        mTvStartShipmentDate.setOnClickListener(this);
        mTvStartAtDate.setOnClickListener(this);
        mTvStartCarConclusion.setOnClickListener(this);
        mWeatherRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkEnable();
            }
        });
        mIvStartSign.setOnClickListener(this);
        mTvStartSave.setOnClickListener(this);

        mPresenter.getInitData(AccountHelper.getInstance().getUserId());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_goods_name:
            case R.id.tv_start_goods_type:
                if (mGoodsDialog != null && !mGoodsDialog.isShowing()) {
                    mGoodsDialog.show();
                }
                break;
            case R.id.tv_start_driver_second:
                //驾驶员2
                if (TextUtils.isEmpty(mTvStartDriverFirst.getText().toString().trim())) {
                    toast(0, "请先选驾驶员1");
                    return;
                }
                if (mDriverSecondDialog != null && !mDriverSecondDialog.isShowing()) {
                    mDriverSecondDialog.show();
                }
                break;
            case R.id.tv_start_escort_name:
                //押运员 
                if (mEscortDialog != null && !mEscortDialog.isShowing()) {
                    mEscortDialog.show();
                }
                break;
            case R.id.tv_start_shipment_date:
                //启运日期
                new CustomTimePicker((TextView) v, null, mEndDate, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mStartDate = date;
                        mTvStartShipmentDate.setText(DateUtils.getInstance().getDate(mStartDate, DateUtils.DATE_FORMAT));
                    }
                },false).show();
                break;
            case R.id.tv_start_at_date:
                //到达日期
                new CustomTimePicker((TextView) v, mStartDate, null, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mEndDate = date;
                        mTvStartAtDate.setText(DateUtils.getInstance().getDate(mEndDate, DateUtils.DATE_FORMAT));
                    }
                },false).show();
                break;
            case R.id.tv_start_car_conclusion:
                //结论
                if (mCarConclusionDialog != null && !mCarConclusionDialog.isShowing()) {
                    mCarConclusionDialog.show();
                }
                break;
            case R.id.iv_start_sign:
                //签名
                startActivityForResult(new Intent(getContext(), UserSignActivity.class), UserSignActivity.USER_SIGN_CODE);
                break;
            case R.id.tv_start_save:
                //保存
                mPresenter.submitParams(mStartBasisParams, mStartDriveParams, mPhotoAdapter.getFilePaths(), mSignImage);
                break;
            default:
                break;
        }
    }

    @Override
    public void setInitData(LogStartData data) {
        mStartBasisParams = new LogStartParams.LogStartBasisParams(AccountHelper.getInstance().getUserId());
        mStartDriveParams = new LogStartParams.LogStartDriveParams(AccountHelper.getInstance().getUserId());
        //设置今日时间
        mTvStartFillDate.setText(DateUtils.getInstance().getToday(DateUtils.DATE_FORMAT));
        //商品
        mGoodsDialog = new ListViewDialog(getContext(), new ArrayList<BaseListBean>(data.getGoodsNameTypeList())
                , new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                LogStartData.GoodsBean bean = (LogStartData.GoodsBean) listBean;
                mTvStartGoodsName.setText(bean.getGoodsName());

                if (TextUtils.isEmpty(bean.getTypeItem())) {

                    mEscortLayout.setVisibility(View.GONE);
                    mGoodsTypeLayout.setVisibility(View.GONE);
                    mTvStartGoodsType.setText("");
                    //设置货物Id
                    mStartBasisParams.setGoodsName(bean.getId());

                } else {

                    mEscortLayout.setVisibility(View.VISIBLE);
                    mGoodsTypeLayout.setVisibility(View.VISIBLE);
                    mTvStartGoodsType.setText(bean.getTypeItemName());
                    //设置类项id
                    mStartBasisParams.setType(bean.getTypeItem());
                }
                checkEnable();
            }
        });
        //驾驶员姓名1  
        mStartBasisParams.setFirstDriverId(data.getFirstDriver().getFirstDriverId());
        mTvStartDriverFirst.setText(data.getFirstDriver().getFirstDriverName());
        //驾驶员姓名2 
        mDriverSecondDialog = new ListViewDialog(getContext(), new ArrayList<BaseListBean>(data.getDrivers()), new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                LogStartData.DriversBean bean = (LogStartData.DriversBean) listBean;
                if (bean.getDictName().equals(mTvStartDriverFirst.getText().toString().trim())) {
                    toast(0, "不能选择同一个驾驶员");
                } else {
                    mTvStartDriverSecond.setText(bean.getDictName());
                    mStartBasisParams.setSecondDriverId(bean.getId());
                }
                checkEnable();
            }
        });
        //押运员
        mEscortDialog = new ListViewDialog(getContext(), new ArrayList<BaseListBean>(data.getEscorts())
                , new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                LogStartData.EscortBean bean = (LogStartData.EscortBean) listBean;
                mTvStartEscortName.setText(bean.getDictName());
                mStartBasisParams.setEscortId(bean.getId());
                checkEnable();
            }
        });

        //检查事项
        mStartDriveParams.setCarCheckProjectList(data.getCarCheckProject());
        mOptionAdapter = new CheckOptionAdapter(new ArrayList<>(data.getCarCheckProject()));
        mStartCheckLayout.setAdapter(mOptionAdapter);
        mStartCheckLayout.setOnSelectListener(this);
        //行车图片
        mPhotoAdapter = new CarPhotoAdapter(getContext());
        mPhotoAdapter.addData(R.mipmap.ic_photo_add);
        mStartCarGridView.setAdapter(mPhotoAdapter);
        mStartCarGridView.setOnItemClickListener(this);

        //确认结论 
        mCarConclusionDialog = new ListViewDialog(getContext(), new ArrayList<BaseListBean>(data.getSureComment())
                , new ListViewDialog.OnItemClickListener() {
            @Override
            public void onClick(BaseListBean listBean) {
                LogStartData.ConclusionBean bean = (LogStartData.ConclusionBean) listBean;
                mTvStartCarConclusion.setText(bean.getDictName());
                mStartDriveParams.setSureComment(bean.getId());
                checkEnable();
            }
        });

        mLoadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void submitParamsSuccess() {
        mDetailsListener.getActivity().finish();
    }

    /**
     * 判断保存按钮是否可以点击
     */
    @Override
    public void checkEnable() {
        boolean isEnable = true;
        mStartBasisParams.setWriteTime(mTvStartFillDate.getText().toString().trim());//设置时间
        if (TextUtils.isEmpty(mEtStartCarPlate.getText().toString().trim())) {
            //车辆牌号为空
            isEnable = false;
        }
        mStartBasisParams.setCarNumber(mEtStartCarPlate.getText().toString().trim());//设置车牌号
        if (TextUtils.isEmpty(mEtStartCarLoadNuclear.getText().toString().trim())) {
            //核载吨位为空
            isEnable = false;
        }
        mStartBasisParams.setIdealTonnage(mEtStartCarLoadNuclear.getText().toString().trim());//设置核载吨位
        if (TextUtils.isEmpty(mEtStartCarLoadActual.getText().toString().trim())) {
            //实载吨位为空
            isEnable = false;
        }
        mStartBasisParams.setActualTonnage(mEtStartCarLoadActual.getText().toString().trim());//设置实载吨位
        if (TextUtils.isEmpty(mTvStartGoodsName.getText().toString().trim())) {
            //货物名称为空
            isEnable = false;
        }
        if (mGoodsTypeLayout.getVisibility() == View.VISIBLE) {
            if (TextUtils.isEmpty(mTvStartGoodsType.getText().toString().trim())) {
                //类项为空
                isEnable = false;
            }

            if (TextUtils.isEmpty(mTvStartEscortName.getText().toString().trim())) {
                //押运员为空
                isEnable = false;
            }
        }
        if (TextUtils.isEmpty(mTvStartDriverFirst.getText().toString().trim())) {
            //驾驶员姓名1
            isEnable = false;
        }
        if (TextUtils.isEmpty(mTvStartEscortName.getText().toString().trim())) {
            //押运员姓名为空
            isEnable = false;
        }
        mStartBasisParams.setTemperature(mEtStartTemperature.getText().toString().trim());//设置温度
        //设置天气
        if (mRbStartFine.isChecked()) {
            mStartBasisParams.setWeather("0");
        } else if (mRbStartYin.isChecked()) {
            mStartBasisParams.setWeather("1");
        } else if (mRbStartRain.isChecked()) {
            mStartBasisParams.setWeather("2");
        } else if (mRbStartFog.isChecked()) {
            mStartBasisParams.setWeather("3");
        } else if (mRbStartSnow.isChecked()) {
            mStartBasisParams.setWeather("4");
        } else if (mRbStartFrozen.isChecked()) {
            mStartBasisParams.setWeather("5");
        }
        if (TextUtils.isEmpty(mTvStartShipmentDate.getText().toString().trim())) {
            //启运日期为空
            isEnable = false;
        }
        mStartBasisParams.setTransportDate(mTvStartShipmentDate.getText().toString().trim());//设置启运日期
        if (TextUtils.isEmpty(mEtStartShipmentLocation.getText().toString().trim())) {
            //启运地为空
            isEnable = false;
        }
        mStartBasisParams.setTransporAddress(mEtStartShipmentLocation.getText().toString().trim());//设置起运地
        if (TextUtils.isEmpty(mEtStartDestination.getText().toString().trim())) {
            //目的地为空
            isEnable = false;
        }
        mStartBasisParams.setGoalAddress(mEtStartDestination.getText().toString().trim());//设置目的地
        if (TextUtils.isEmpty(mEtStartMileage.getText().toString().trim())) {
            //公里数为空
            isEnable = false;
        }
        mStartBasisParams.setKm(mEtStartMileage.getText().toString().trim());//设置公里数
        if (TextUtils.isEmpty(mTvStartAtDate.getText().toString().trim())) {
            //到达日期为空
            isEnable = false;
        }
        mStartBasisParams.setArriveDate(mTvStartAtDate.getText().toString().trim());//设置到达日期
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
        mStartDriveParams.setNoAccord(mEtStartNotMeet.getText().toString().trim());//设置不符合项
        mStartDriveParams.setRemark(mEtStartRemarks.getText().toString().trim());//设置备注
        if (TextUtils.isEmpty(mSignImage)) {
            //签名照片为空
            isEnable = false;
        }

        mTvStartSave.setEnabled(isEnable);
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
                    .into(mIvStartSign);
            checkEnable();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Object object = mPhotoAdapter.getItem(position);
        if (object instanceof Integer) {
            if (mPhotoDialog == null) {
                mPhotoDialog = new PhotoDialog(getContext(), DriveStartWriteFragment.this);
            }
            mPhotoDialog.show();
        }
    }

    @Override
    public void takePhoto() {
        new RxPermissions(this)
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            PictureSelector.create(DriveStartWriteFragment.this)
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
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            int size = mPhotoAdapter.getCount() - 1;
                            PictureSelector.create(DriveStartWriteFragment.this)
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
