package com.dalaiye.luhang.widget.captcha;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.blankj.utilcode.util.SizeUtils;
import com.dalaiye.luhang.R;

/**
 * Created by AYu 2019/4/15.
 */

public class Captcha extends LinearLayout {

    //控件成员
    private PictureVertifyView vertifyView;         //拼图块
    private SeekBar seekbar;                    //滑动条块
    //控件属性
    private int drawableId = -1;          //验证图片资源id
    private int mMode;               //控件验证模式(有滑动条/无滑动条)
    private int blockSize;           //拼图缺块大小

    //处理滑动条逻辑
    private boolean isResponse;
    private boolean isDown;

    private CaptchaListener mListener;

    private BitmapLoaderTask mTask;
    /**
     * 带滑动条验证模式
     */
    public static final int MODE_BAR = 1;
    /**
     * 不带滑动条验证，手触模式
     */
    public static final int MODE_NONBAR = 2;

    @IntDef(value = {MODE_BAR, MODE_NONBAR})
    public @interface Mode {
    }


    public interface CaptchaListener {

        /**
         * Called when captcha access.
         *
         * @param time cost of access time
         * @return text to show,show default when return null
         */
        String onAccess(long time);

        /**
         * Called when captcha failed.
         *
         * @return text to show,show default when return null
         */
        String onFailed();

    }


    public Captcha(@NonNull Context context) {
        super(context);
    }

    public Captcha(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Captcha(@NonNull final Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Captcha);
        mMode = typedArray.getInteger(R.styleable.Captcha_mode, MODE_BAR);
        blockSize = typedArray.getDimensionPixelSize(R.styleable.Captcha_blockSize, SizeUtils.dp2px(getContext(), 50));
        typedArray.recycle();
        init();
    }


    private void init() {
        View parentView = LayoutInflater.from(getContext()).inflate(R.layout.container, this, true);
        vertifyView = parentView.findViewById(R.id.vertifyView);
        seekbar = parentView.findViewById(R.id.seekbar);

        setMode(mMode);
        if(drawableId!=-1){
            vertifyView.setImageResource(drawableId);
        }
        setBlockSize(blockSize);
        vertifyView.callback(new PictureVertifyView.Callback() {
            @Override
            public void onSuccess(long time) {
                mListener.onAccess(time);
                seekbar.setProgress(0);
            }

            @Override
            public void onFailed() {
                reset();
                seekbar.setEnabled(true);
                vertifyView.setTouchEnable(false);
                mListener.onFailed();
                seekbar.setProgress(0);
            }

        });
        //用于处理滑动条渐滑逻辑
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (isDown) {  //手指按下
                    isDown = false;
                    if (progress > 10) { //按下位置不正确
                        isResponse = false;
                    } else {
                        isResponse = true;
                        vertifyView.down(0);
                    }
                }
                if (isResponse) {
                    vertifyView.move(progress);
                } else {
                    seekBar.setProgress(0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isDown = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (isResponse) {
                    vertifyView.loose();
                }
            }
        });

    }

    public void setCaptchaListener(CaptchaListener listener) {
        this.mListener = listener;
    }

    public void setCaptchaStrategy(CaptchaStrategy strategy) {
        if (strategy != null) {
            vertifyView.setCaptchaStrategy(strategy);
        }
    }

    public void setSeekBarStyle(@DrawableRes int progressDrawable, @DrawableRes int thumbDrawable) {
        seekbar.setProgressDrawable(getResources().getDrawable(progressDrawable));
        seekbar.setThumb(getResources().getDrawable(thumbDrawable));
        seekbar.setThumbOffset(0);
    }

    /**
     * 设置滑块图片大小，单位px
     */
    public void setBlockSize(int blockSize) {
        vertifyView.setBlockSize(blockSize);
    }

    /**
     * 设置滑块验证模式
     */
    public void setMode(@Mode int mode) {
        this.mMode = mode;
        vertifyView.setMode(mode);
        if (mMode == MODE_NONBAR) {
//            seekbar.setVisibility(GONE);
            vertifyView.setTouchEnable(true);
        } else {
            seekbar.setVisibility(VISIBLE);
            seekbar.setEnabled(true);
        }
    }

    public int getMode() {
        return this.mMode;
    }

    public void setBitmap(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        setBitmap(bitmap);
    }

    public void setBitmap(Bitmap bitmap) {
        vertifyView.setImageBitmap(bitmap);
        reset();
    }

    public void setBitmap(String url) {
        mTask = new BitmapLoaderTask(new BitmapLoaderTask.Callback() {
            @Override
            public void result(Bitmap bitmap) {
                setBitmap(bitmap);
            }
        });
        mTask.execute(url);
    }

    @Override
    protected void onDetachedFromWindow() {
        if(mTask!=null&&mTask.getStatus().equals(AsyncTask.Status.RUNNING)){
            mTask.cancel(true);
        }
        super.onDetachedFromWindow();
    }

    /**
     * 复位
     */
    public void reset() {
        vertifyView.reset();
        if (mMode == MODE_BAR) {
            seekbar.setEnabled(true);
            seekbar.setProgress(0);
        } else {
            vertifyView.setTouchEnable(true);
        }
    }
}
