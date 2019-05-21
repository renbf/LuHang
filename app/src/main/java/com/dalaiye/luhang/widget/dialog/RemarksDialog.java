package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dalaiye.luhang.R;

import java.util.Objects;

/**
 * @author AnYu
 * @date 2019/4/18
 * @function 备注以及拒绝的dialog
 **/
public class RemarksDialog extends AppCompatDialog implements View.OnClickListener {

    private AppCompatEditText mEdit;
    private AppCompatTextView mTvDetermine;
    private AppCompatTextView mTvCancel;
    private String hintText;
    private DialogClickCallBack mDialogClickCallBack;

    public RemarksDialog(Context context, String hint,DialogClickCallBack mDialogClickCallBack) {
        this(context, R.style.styles_dialog);
        this.hintText = hint;
        this.mDialogClickCallBack = mDialogClickCallBack;
    }

    public RemarksDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_remarks);
        initWindow();
        mEdit = findViewById(R.id.edit);
        mTvDetermine = findViewById(R.id.tv_determine);
        mTvCancel = findViewById(R.id.tv_cancel);

        mTvDetermine.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);

        mEdit.setHint(hintText);
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
            case R.id.tv_determine :
                mDialogClickCallBack.onConfirmClick(Objects.requireNonNull(mEdit.getText()).toString());
                break;
            case R.id.tv_cancel:
                mDialogClickCallBack.onCancelClick();
                break;
            default:
                break;
        }
    }
    public interface DialogClickCallBack{
        void onConfirmClick(String refuseContent);

        void onCancelClick();
    }
}
