package com.dalaiye.luhang.ui.train;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dalaiye.luhang.R;
import com.gfc.library.base.BaseActivity;
import com.gfc.library.utils.qmui.QMUIStatusBarHelper;

/**
 * @author admin
 * @date 2019/4/11
 * @function 注释
 **/
public class TrainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_layout, TrainFragment.newInstance())
                .commit();
    }

    @Override
    protected void setStatusBarMode() {
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
    }
}
