package com.dalaiye.luhang.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dalaiye.luhang.R;

/**
 * @author admin
 * @date 2019/4/26
 * @function 注释
 **/
public class ExamScoreDialog extends AppCompatDialog {

    private Activity mActivity;
    private AppCompatTextView mTvScore;
    private AppCompatTextView mTvKnow;
    private String mScore;

    public ExamScoreDialog(Activity activity, String score) {
        super(activity, R.style.styles_dialog);
        mActivity = activity;
        mScore = score;
    }

    public ExamScoreDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exam_score);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        initView();
        initWindow();
    }

    private void initView() {
        mTvScore = findViewById(R.id.tv_score);
        mTvKnow = findViewById(R.id.tv_know);

        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.5f);
        ForegroundColorSpan fgColorSpan = new ForegroundColorSpan(Color.RED);
        SpannableString ss = new SpannableString(mScore + "分");
        ss.setSpan(sizeSpan, ss.length() - 1, ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(fgColorSpan, ss.length() - 1, ss.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        mTvScore.setText(ss);
        mTvKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    private void initWindow() {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.CENTER;
        }
    }
}
