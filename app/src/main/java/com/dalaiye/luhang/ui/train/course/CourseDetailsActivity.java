package com.dalaiye.luhang.ui.train.course;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.bean.UpLoadProgressBean;
import com.dalaiye.luhang.contract.train.CourseDetailsContract;
import com.dalaiye.luhang.contract.train.impl.CourseDetailsPresenter;
import com.dalaiye.luhang.utils.RandomUtils;
import com.dalaiye.luhang.widget.VideoPlayerController;
import com.dalaiye.luhang.widget.dialog.CaptchaDialog;
import com.gfc.library.event.EventKeys;
import com.gfc.library.event.EventMessage;
import com.gfc.library.mvp.BaseMvpActivity;
import com.gfc.library.widget.web.X5WebView;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author AnYu
 * @date 2019/4/15
 * @function 课程详情
 **/
public class CourseDetailsActivity extends BaseMvpActivity<CourseDetailsContract.ICourseDetailsPresenter>
        implements CourseDetailsContract.ICourseDetailsView, View.OnClickListener,
        CaptchaDialog.CaptchaScrollListener {

    private AppCompatImageView mTopBarBack;
    private AppCompatTextView mTopBarTitle;
    private CaptchaDialog captchaDialog;
    private NiceVideoPlayer mVideoPlayer;
    private VideoPlayerController controller;
    private CourseBean.RowsBean rowsBean;
    private AppCompatTextView mTvCourseName;
    private AppCompatImageView mIvIsCollection;
    private AppCompatTextView mTvIsCollection;
    private X5WebView mWebView;
    private UpLoadProgressBean upLoadProgressBean;
    /**
     * type 为0的时候是从课程安排或者课程收藏传递进来，这时候页面销毁需要上传进度
     * type 为1的时候是从我的课程传递进来，这时候页面销毁不需要上传进度
     */
    private int type;
    /**
     * 是否上传成功过总时长
     */
    private boolean mTag;
    /**
     * 是否上传了视频开始时间
     */
    private boolean isUpdataStartTime = true;
    private List<Integer> mRandomList;

    @Override
    protected CourseDetailsContract.ICourseDetailsPresenter createPresenter() {
        return new CourseDetailsPresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_train_course_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rowsBean = (CourseBean.RowsBean) getIntent().getSerializableExtra("rows");

        type = getIntent().getIntExtra("type", 1);

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mTopBarTitle.setText("课程详情");
        mIvIsCollection = findViewById(R.id.iv_is_collection);
        mTvIsCollection = findViewById(R.id.tv_is_collection);
        mWebView = findViewById(R.id.web_view);
        mTopBarBack.setOnClickListener(this);
        mIvIsCollection.setOnClickListener(this);
        mTvIsCollection.setOnClickListener(this);

        mTvCourseName = findViewById(R.id.tv_course_name);
        mVideoPlayer = findViewById(R.id.video_player);

        mTvCourseName.setText(rowsBean.getCourseName());


        initViewPlayer();

        initCollection();

        mWebView.loadUrl(rowsBean.getCourseUrl());
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initViewPlayer() {
        mVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK);
        mVideoPlayer.setUp(rowsBean.getVideoUrl(), null);
        controller = new VideoPlayerController(this) {
            @Override
            protected void updateProgress() {
                super.updateProgress();
                final long duration = mVideoPlayer.getDuration();
                final int position = (int) mVideoPlayer.getCurrentPosition();
                //是否需要上传进度
                if (TextUtils.isEmpty(rowsBean.getTotalTimes()) && !mTag) {
                    mTag = true;
                    mPresenter.upLoadProgress(rowsBean.getId(), (int) duration);
                }
                //是否需要上传视频开始时的进度
                if(isUpdataStartTime) {
                    isUpdataStartTime = false;
                    mPresenter.upLoadStartProgress(rowsBean.getUserCourseId(),String.valueOf(position),1);
                }
                //生成随机数
                if (mRandomList == null) {
                    generateRandom((int) duration);
                }
                if (mRandomList != null) {
                    if (captchaDialog == null) {
                        captchaDialog = new CaptchaDialog(CourseDetailsActivity.this, CourseDetailsActivity.this);
                    }

                    Log.d("随机数", JSON.toJSONString(mRandomList));

                    for (int i = 0; i < mRandomList.size(); i++) {
                        int random = mRandomList.get(i);
                        boolean isShow = random / 1000 == position / 1000;
                        if (!captchaDialog.isShowing() && isShow) {
                            captchaDialog.show();
                            mVideoPlayer.pause();
                            mRandomList.remove(i);
                            i--;
                            break;
                        }
                    }
                }
            }
        };
        //设置播放器的seekBar是否可以滑动
        controller.getSeek().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        controller.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        Glide.with(this).load(rowsBean.getCourseUrl()).into(controller.imageView());
        controller.setNiceVideoPlayer(mVideoPlayer);
        mVideoPlayer.setController(controller);
    }

    private void generateRandom(int duration) {
        if (duration > 15 * 60 * 1000) {
            //大于15分钟
            mRandomList = RandomUtils.generateRandom(duration, 3);

        } else if (duration > 10 * 60 * 1000) {
            //大于10分钟
            mRandomList = RandomUtils.generateRandom(duration, 2);

        } else if (duration > 5 * 60 * 1000) {
            //大于5分钟
            mRandomList = RandomUtils.generateRandom(duration, 1);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back:
                finish();
                break;
            case R.id.iv_is_collection:
            case R.id.tv_is_collection:
                if ("0".equals(rowsBean.getIsCollect())) {
                    mPresenter.addOrDeleteCollection(rowsBean.getUserCourseId(), "1");
                } else {
                    mPresenter.addOrDeleteCollection(rowsBean.getUserCourseId(), "0");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        upLoadProgressBean = new UpLoadProgressBean();
        upLoadProgressBean.setUserCourseId(rowsBean.getUserCourseId());
        upLoadProgressBean.setProgress(String.valueOf(mVideoPlayer.getCurrentPosition()));
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void checkSuccessful() {
        if (captchaDialog != null) {
            captchaDialog.dismiss();
            controller.restartVideo();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (type == 0) {
            EventBus.getDefault().post(new EventMessage(EventKeys.UPLOAD_VIDEO_PROGRESS, upLoadProgressBean));
        }
    }

    /**
     * 设置收藏
     */
    @Override
    public void setCollection() {
        if ("0".equals(rowsBean.getIsCollect())) {
            mIvIsCollection.setImageResource(R.mipmap.yes_collection);
            mTvIsCollection.setText("已收藏");
            rowsBean.setIsCollect("1");
        } else {
            mIvIsCollection.setImageResource(R.mipmap.no_collection);
            mTvIsCollection.setText("收藏");
            rowsBean.setIsCollect("0");
        }
    }

    /**
     * 初始收藏状态
     */
    private void initCollection() {
        if ("0".equals(rowsBean.getIsCollect())) {
            mIvIsCollection.setImageResource(R.mipmap.no_collection);
            mTvIsCollection.setText("收藏");
        } else {
            mIvIsCollection.setImageResource(R.mipmap.yes_collection);
            mTvIsCollection.setText("已收藏");
        }
    }

    @Override
    public void setUpLoadSuccessful(int duration) {
        rowsBean.setTotalTimes(String.valueOf(duration));
    }

    @Override
    public void setUpLoadFail(int duration) {
        mTag = false;
    }

    @Override
    public void upLoadStartTimeSuccessful() {

    }

    @Override
    public void upLoadStartTimefail() {
        isUpdataStartTime = true;
    }
}
