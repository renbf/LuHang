package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dalaiye.luhang.R;

import java.util.Calendar;
import java.util.Date;

/**
 * @author admin
 * @date 2019/4/16
 * @function 注释
 **/
public class CustomTimePicker {


    private Calendar mStartCalendar;
    private Calendar mEndCalendar;
    
    private boolean isShow;

    private Context mContext;
    private TextView mTextView;
    private TimePickerView mPickerView;
    private OnTimeSelectListener mTimeSelectListener;

    public CustomTimePicker(TextView textView, Date startDate, Date endDate, OnTimeSelectListener selectListener
    ,boolean isShow) {

        mContext = textView.getContext();
        mTextView = textView;
        
        this.isShow = isShow;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);//当前的年
        int month = calendar.get(Calendar.MONTH); //当前的月

        mStartCalendar = Calendar.getInstance();
        if (startDate != null) {
            mStartCalendar.setTime(startDate);
        }

        mEndCalendar = Calendar.getInstance();
        if (endDate == null) {
            int endYear = year + 3;
            switch (1 + 1) {
                case 2:
                    if (endYear % 4 == 0 || endYear % 100 == 0 || endYear % 400 == 0) {
                        mEndCalendar.set(endYear, month, 29);
                    } else {
                        mEndCalendar.set(endYear, month, 28);
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    mEndCalendar.set(endYear, month, 30);
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    mEndCalendar.set(endYear, month, 31);
                    break;
                default:
                    break;
            }


        } else {
            mEndCalendar.setTime(endDate);
        }

        mTimeSelectListener = selectListener;
    }

    public void show() {
        mPickerView = new TimePickerBuilder(mContext
                , mTimeSelectListener)
                .setDividerColor(ContextCompat.getColor(mContext, R.color.color_window))//分割线
                .setTextColorCenter(ContextCompat.getColor(mContext, R.color.color_text_dark))//选中项
                .setBgColor(Color.WHITE)//背景
                .setDate(mStartCalendar)//选中时间
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
                .setType(new boolean[]{true, true, true, isShow, isShow, isShow})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false) 
                .isDialog(true)
                .setOutSideCancelable(false)
                .build();
        mPickerView.show(mTextView);
    }


}
