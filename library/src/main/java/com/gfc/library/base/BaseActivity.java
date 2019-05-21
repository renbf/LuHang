package com.gfc.library.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gfc.library.config.ConfigKeys;
import com.gfc.library.config.ECLibrary;
import com.gfc.library.utils.qmui.QMUIStatusBarHelper;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenOrientation();
        setStatusBarMode();
    }

    protected void setStatusBarMode() {
        //设置状态栏图标颜色
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }

    protected void setScreenOrientation() {
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void removeOtherActivity() {
        List<Activity> list = ECLibrary.getConfiguration(ConfigKeys.ACTIVITIES);
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                list.get(i).finish();
            }
        }
    }
}

