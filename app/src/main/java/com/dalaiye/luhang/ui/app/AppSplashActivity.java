package com.dalaiye.luhang.ui.app;

import android.content.Intent;
import android.os.Bundle;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.contract.app.AppSplashContract;
import com.dalaiye.luhang.contract.app.impl.AppSplashPresenter;
import com.dalaiye.luhang.service.GTPushIntentService;
import com.dalaiye.luhang.service.GTPushService;
import com.gfc.library.config.ECLibrary;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.utils.qmui.QMUIStatusBarHelper;
import com.igexin.sdk.PushManager;

/**
 * @author admin
 * @date 2019/4/10
 * @function 启动页面
 **/
public class AppSplashActivity extends BaseMvpActivity<AppSplashContract.IAppSplashPresenter>
        implements AppSplashContract.IAppSplashView {

    @Override
    protected AppSplashContract.IAppSplashPresenter createPresenter() {
        return new AppSplashPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_app_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this);
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(getApplicationContext(), GTPushService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(getApplicationContext(), GTPushIntentService.class);
        ECLibrary.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(AppSplashActivity.this, AppMainActivity.class));
                finish();
            }
        },2000);
    }
}
