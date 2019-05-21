package com.dalaiye.luhang.ui.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.View;

import com.dalaiye.luhang.R;
import com.gfc.library.base.BaseActivity;
import com.gfc.library.widget.web.X5WebView;

/**
 * @author admin
 * @date 2019/4/10
 * @function web页面  banner详情
 **/
public class BannerDetailsActivity extends BaseActivity implements View.OnClickListener {
    private AppCompatImageView topBarBack;
    private AppCompatTextView topBarTitle;
    private X5WebView webView;
    private int type;
    private String mWebViewUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_banner_web);
        type = getIntent().getIntExtra("type", 0);
        mWebViewUrl = getIntent().getStringExtra("webUrl");
        initView();
        initHardwareAccelerate();
        webView.loadUrl(mWebViewUrl);
    }

    private void initView() {
        topBarBack = findViewById(R.id.top_bar_back);
        topBarTitle = findViewById(R.id.top_bar_title);
        webView = findViewById(R.id.web_view);

        topBarBack.setOnClickListener(this);
        if (type == 0) {
            topBarTitle.setText("轮播图详情");
        } else {
            topBarTitle.setText("行业内容");
        }
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 11) {
                getWindow()
                        .setFlags(
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                                android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        //释放资源
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
