package com.gfc.library.utils.count;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * @author admin
 * @date 2018/5/25
 * @function 功能
 */

public class TimeCount extends CountDownTimer {
    private TextView mButton;

    public TimeCount(TextView mButton) {
        super(60000, 1000);
        this.mButton = mButton;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mButton.setEnabled(false);
        //修改字体颜色至灰色
        mButton.setText("重新发送(" + millisUntilFinished / 1000 + "s)");
    }

    @Override
    public void onFinish() {
        mButton.setText("发送验证码");
        mButton.setEnabled(true);
    }

}

