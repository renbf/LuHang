package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dalaiye.luhang.R;
import com.gfc.library.utils.watcher.EnabledTextWatcher;

/**
 * @author admin
 * @date 2019/4/29
 * @function 注释
 **/
public class LogHandoverDialog extends AppCompatDialog implements View.OnClickListener, EnabledTextWatcher.EnabledListener {
    private AppCompatEditText mEtRemarks;
    private AppCompatTextView mTvOk;
    private AppCompatTextView mTvNo;
    private IHandoverListener mHandoverListener;

    public LogHandoverDialog(Context context,IHandoverListener listener) {
        this(context, R.style.styles_dialog);
        mHandoverListener = listener;
    }

    private LogHandoverDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_log_handover);
        initView();
        initWindow();
    }

    private void initView() {
        mEtRemarks = findViewById(R.id.et_remarks);
        mTvOk = findViewById(R.id.tv_ok);
        mTvNo = findViewById(R.id.tv_no);
        
        mEtRemarks.addTextChangedListener(new EnabledTextWatcher(this));
        
        mTvOk.setOnClickListener(this);
        mTvNo.setOnClickListener(this);
    }

    private void initWindow() {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.CENTER;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ok:
                if (mHandoverListener != null){
                    mHandoverListener.onHandoverMessage(mEtRemarks.getText().toString().trim());
                }
                cancel();
                break;
            case R.id.tv_no:
                cancel();
                break;
            default:
                break;
        }
    }

    @Override
    public void checkEnable() {
        boolean isEnable = true;
        if (TextUtils.isEmpty(mEtRemarks.getText().toString().trim())){
            isEnable  =false;
        }
        mTvOk.setEnabled(isEnable);
    }

    public interface IHandoverListener{
        void onHandoverMessage(String remarks);
    }
}
