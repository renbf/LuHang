package com.dalaiye.luhang.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dalaiye.luhang.R;

/**
 * @author admin
 * @date 2019/4/16
 * @function 注释
 **/
public class PhotoDialog extends AppCompatDialog implements View.OnClickListener {

    private PhotoListener mListener;

    public PhotoDialog(Context context, PhotoListener listener) {
        this(context, R.style.Up_Down_dialog_Animation);
        mListener = listener;
    }

    private PhotoDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_photo_permission);
        initWindow();
        initView();
    }

    private void initWindow() {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.BOTTOM;
        }
    }

    private void initView() {
        findViewById(R.id.tv_take_photo).setOnClickListener(this);
        findViewById(R.id.tv_album).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_take_photo:
                if (mListener != null) {
                    mListener.takePhoto();
                }
                cancel();
                break;
            case R.id.tv_album:
                if (mListener != null) {
                    mListener.album();
                }
                cancel();
                break;
            case R.id.tv_cancel:
                cancel();
                break;
            default:
                break;
        }
    }

    public interface PhotoListener {
        void takePhoto();

        void album();
    }
}
