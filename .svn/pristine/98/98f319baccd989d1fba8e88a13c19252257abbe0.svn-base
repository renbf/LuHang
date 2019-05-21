package com.dalaiye.luhang.ui.car.log;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dalaiye.luhang.R;
import com.gfc.library.base.BaseActivity;
import com.gfc.library.utils.qmui.QMUIStatusBarHelper;

/**
 * @author admin
 * @date 2019/4/10
 * @function 首页日志跳转
 **/
public class LogActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_log);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_layout, LogFragment.newInstance())
                .commit();

    }

    @Override
    protected void setStatusBarMode() {
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }
}
