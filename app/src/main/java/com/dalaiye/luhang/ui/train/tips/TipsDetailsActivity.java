package com.dalaiye.luhang.ui.train.tips;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.TipsBean;
import com.dalaiye.luhang.contract.train.TipDetailsContract;
import com.dalaiye.luhang.contract.train.impl.TipDetailsPresenter;
import com.dalaiye.luhang.widget.VideoPlayerController;
import com.gfc.library.mvp.BaseMvpActivity;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

/**
 * @author admin
 * @date 2019/4/10
 * @function 小知识详情
 **/
public class TipsDetailsActivity extends BaseMvpActivity<TipDetailsContract.ITipDetailsPresenter>
        implements TipDetailsContract.ITipDetailsView, View.OnClickListener {
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private NiceVideoPlayer mVideoPlayer;
    private VideoPlayerController controller;
    private android.support.v7.widget.AppCompatTextView mTvCourseName;
    private com.gfc.library.widget.web.X5WebView mWebView;

    @Override
    protected TipDetailsContract.ITipDetailsPresenter createPresenter() {
        return new TipDetailsPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_train_tips_details;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        TipsBean.RowsBean rowsBean = (TipsBean.RowsBean) getIntent().getSerializableExtra("rowsBean");
        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTopBarTitle.setText("小知识详情");
        mTopBarBack.setOnClickListener(this);

        mTvCourseName = findViewById(R.id.tv_course_name);
        mTvCourseName.setText(rowsBean.getCourseName());
        mVideoPlayer = findViewById(R.id.video_player);
        mVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK);
        mVideoPlayer.setUp(rowsBean.getVideoUrl(), null);

        controller = new VideoPlayerController(this);
        Glide.with(this).load(rowsBean.getCourseUrl()).into(controller.imageView());
        mVideoPlayer.setController(controller);

        initHardwareAccelerate();
        mWebView = findViewById(R.id.web_view);
        mWebView.loadUrl(rowsBean.getWebUrl());
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
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    protected void onDestroy() {
        //释放资源
        if (mWebView != null) {
            mWebView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
