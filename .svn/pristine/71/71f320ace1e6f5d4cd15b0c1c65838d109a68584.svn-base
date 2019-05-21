package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.ui.train.course.CourseDetailsActivity;
import com.dalaiye.luhang.widget.captcha.Captcha;

/**
 * @author AnYu
 * @date 2019/4/16
 * @function 验证码dialog
 **/
public class CaptchaDialog extends AppCompatDialog implements Captcha.CaptchaListener {

    private com.dalaiye.luhang.widget.captcha.Captcha mCaptcha;
    private CaptchaScrollListener mCaptchaScrollListener;

    public CaptchaDialog(Context context,CaptchaScrollListener listener) {
        this(context, R.style.styles_dialog);
        mCaptchaScrollListener = listener;
    }

    public CaptchaDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_capt_cha);
        initWindow();
        setCancelable(false);
        mCaptcha = findViewById(R.id.captcha);
        if (mCaptcha != null) {
            mCaptcha.setBitmap(R.mipmap.icon_code_car);
            mCaptcha.setCaptchaListener(this);
        }

    }
    private void initWindow() {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.CENTER;
        }
    }

    @Override
    public String onAccess(long time) {
        mCaptchaScrollListener.checkSuccessful();
        return "验证成功";
    }

    @Override
    public String onFailed() {
        Toast.makeText(getContext(), "校检失败，请重新尝试", Toast.LENGTH_SHORT).show();
        return "验证失败";
    }
    public interface CaptchaScrollListener{

        void checkSuccessful(); 
    }
}
