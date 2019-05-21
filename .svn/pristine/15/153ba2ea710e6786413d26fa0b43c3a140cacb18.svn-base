package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author admin
 * @date 2019/4/16
 * @function 注释
 **/
public class CustomTimePicker2 {

    private static final String TAG = "CustomTimePicker2";

    public static final int TIME_UPLOAD_DANGERS = 0;
    public static final int TIME_LOG_DRIVE_START = 1;
    public static final int TIME_LOG_DRIVE_IN = 2;
    public static final int TIME_LOG_DRIVE_END = 3;

    private Calendar mStartCalendar;
    private Calendar mEndCalendar;
    private Calendar mShowCalendar;

    private int mState;
    private View mShowView;
    private TimePickerView mPickerView;
    private OnTimeSelectListener mTimeSelectListener;

    public CustomTimePicker2(View showView, OnTimeSelectListener timeSelectListener, int state) {
        mState = state;
        mShowView = showView;
        mTimeSelectListener = timeSelectListener;

        initCalendar(state);


    }

    private void initCalendar(int state) {
        Calendar calendar = Calendar.getInstance();
        mStartCalendar = Calendar.getInstance();//默认显示最早时间
        mStartCalendar.add(Calendar.DAY_OF_MONTH, -30);
        mEndCalendar = Calendar.getInstance();//默认显示最晚时间
        mEndCalendar.add(Calendar.DAY_OF_MONTH, 30);
        mShowCalendar = Calendar.getInstance();//默认显示选中时间

        if (mState == TIME_LOG_DRIVE_IN || mState == TIME_LOG_DRIVE_END) {
            mStartCalendar.add(Calendar.DAY_OF_MONTH, 30);
        }


//        Calendar calendar = Calendar.getInstance();//当前时间
//        switch (state) {
//            case TIME_UPLOAD_DANGERS://上报隐患 当前时间向前
//                mEndCalendar = Calendar.getInstance();//结束时间就是当前时间
//
//                mStartCalendar = Calendar.getInstance();//开始时间
//                mStartCalendar.add(Calendar.DAY_OF_MONTH, -30);//向前30天
//                mCurrentCalendar = calendar;
//                break;
//            case TIME_LOG_DRIVE_START:
//                if (start == null && end == null) {
//                    //开始时间 结束时间 都为null
//                    mStartCalendar = Calendar.getInstance();
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.add(Calendar.DAY_OF_MONTH, 30);
//                } else if (start == null) {
//                    //开始时间为null 结束时间不为null
//                    mStartCalendar = Calendar.getInstance();
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(end);
//                } else if (end == null) {
//                    //开始时间不为null 结束时间为null
//                    mStartCalendar = Calendar.getInstance();
//                    mStartCalendar.setTime(start);
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(start);
//                    mEndCalendar.add(Calendar.DAY_OF_MONTH, 30);
//                } else {
//                    mStartCalendar = Calendar.getInstance();
//                    mStartCalendar.setTime(start);
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(end);
//                }
//
//                if (mStartCalendar.compareTo(calendar) >= 0) {//开始时间在当前时间之后
//                    mCurrentCalendar = mStartCalendar;
//                } else {//开始时间在当前时间之前
//                    mCurrentCalendar = calendar;
//                }
//
//
//                break;
//            case TIME_LOG_DRIVE_IN:
//                if (start == null && end == null) {
//                    Log.d(TAG, "initCalendar: null null");
//                    //开始时间 结束时间 都为null
//                    mStartCalendar = Calendar.getInstance();
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.add(Calendar.DAY_OF_MONTH, 30);
//                } else if (start == null) {
//                    Log.d(TAG, "initCalendar: null not");
//                    //开始时间为null 结束时间不为null
//                    mStartCalendar = Calendar.getInstance();
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(end);
//                } else if (end == null) {
//                    Log.d(TAG, "initCalendar: not null");
//                    //开始时间不为null 结束时间为null
//                    mStartCalendar = Calendar.getInstance();
//                    mStartCalendar.setTime(start);
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(start);
//                    mEndCalendar.add(Calendar.DAY_OF_MONTH, 30);
//                } else {
//                    Log.d(TAG, "initCalendar: not not");
//                    mStartCalendar = Calendar.getInstance();
//                    mStartCalendar.setTime(start);
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(end);
//                }
//
//                if (mStartCalendar.compareTo(calendar) >= 0) {//开始时间在当前时间之后
//                    mCurrentCalendar = mStartCalendar;
//                } else {//开始时间在当前时间之前
//                    mCurrentCalendar = calendar;
//                }
//                break;
//            case TIME_LOG_DRIVE_END:
//                if (start == null && end == null) {
//                    //开始时间 结束时间 都为null
//                    mStartCalendar = Calendar.getInstance();
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.add(Calendar.DAY_OF_MONTH, 30);
//                } else if (start == null) {
//                    //开始时间为null 结束时间不为null
//                    
//                    mStartCalendar = Calendar.getInstance();
//                    mStartCalendar.setTime(end); 
//                    mStartCalendar.add(Calendar.DAY_OF_MONTH, -30);//向前30天
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(end);
//                    
//                    
//                } else if (end == null) {
//                    //开始时间不为null 结束时间为null
//                    mStartCalendar = Calendar.getInstance();
//                    mStartCalendar.setTime(start);
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(start);
//                    mEndCalendar.add(Calendar.DAY_OF_MONTH, 30);
//                } else {
//                    mStartCalendar = Calendar.getInstance();
//                    mStartCalendar.setTime(start);
//                    mEndCalendar = Calendar.getInstance();
//                    mEndCalendar.setTime(end);
//                }
//
//                if (mStartCalendar.compareTo(calendar) >= 0) {//开始时间在当前时间之后
//                    mCurrentCalendar = mStartCalendar;
//                } else {//开始时间在当前时间之前
//                    mCurrentCalendar = calendar;
//                }
//                break;
//            default:
//                break;
//        }
    }

    public void show() {

        Log.d(TAG, "start -->" + DateUtils.getInstance().getDate(mStartCalendar.getTime(), DateUtils.TIME_FORMAT)
                + "\n" + "show -->" + DateUtils.getInstance().getDate(mShowCalendar.getTime(), DateUtils.TIME_FORMAT)
                + "\n" + "end -->" + DateUtils.getInstance().getDate(mEndCalendar.getTime(), DateUtils.TIME_FORMAT)
        );

        mPickerView = new TimePickerBuilder(mShowView.getContext()
                , mTimeSelectListener)
                .setDividerColor(ContextCompat.getColor(mShowView.getContext(), R.color.color_window))//分割线
                .setTextColorCenter(ContextCompat.getColor(mShowView.getContext(), R.color.color_text_dark))//选中项
                .setBgColor(Color.WHITE)//背景
                .setDate(mShowCalendar)//选中时间
                .setRangDate(mStartCalendar, mEndCalendar)//时间区间
                .setLayoutRes(R.layout.dialog_picker_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        v.findViewById(R.id.tv_cancel);
                        v.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPickerView.dismiss();
                            }
                        });
                        v.findViewById(R.id.tv_determine).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPickerView.returnData();
                                mPickerView.dismiss();
                            }
                        });
                    }
                })//布局
                .setContentTextSize(16)
                .setType(new boolean[]{true, true, true, mState == TIME_LOG_DRIVE_IN, mState == TIME_LOG_DRIVE_IN, mState == TIME_LOG_DRIVE_IN})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)
                .setOutSideCancelable(false)
                .build();
        mPickerView.show(mShowView);
    }


}
