package com.gfc.library.widget.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.SizeUtils;
import com.gfc.library.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * @author admin
 * @date 2019/4/10
 * @function 注释
 **/
public class LoaderDialog extends AppCompatDialog {

    private static final LinearLayoutCompat.LayoutParams LAYOUT_PARAMS = new LinearLayoutCompat
            .LayoutParams(SizeUtils.dp2px(150), SizeUtils.dp2px(80));

    private static final LinearLayoutCompat.LayoutParams AV_PARAMS = new LinearLayoutCompat
            .LayoutParams(SizeUtils.dp2px(42), SizeUtils.dp2px(42));

    public LoaderDialog(Context context) {
        this(context, LoaderStyle.BallPulseIndicator);
    }

    private LoaderDialog(Context context, Enum<LoaderStyle> styleEnum) {
        super(context, R.style.styles_dialog_loader);
        LinearLayoutCompat layoutCompat = new LinearLayoutCompat(context);
        layoutCompat.setGravity(Gravity.CENTER);
        layoutCompat.setLayoutParams(LAYOUT_PARAMS);
        layoutCompat.setBackground(ContextCompat.getDrawable(context, R.drawable.lib_shape_loader));

        AVLoadingIndicatorView indicatorView = LoaderCreator.create(styleEnum.name(), context);
        indicatorView.setLayoutParams(AV_PARAMS);
        indicatorView.setIndicatorColor(Color.WHITE);
        layoutCompat.addView(indicatorView);
        
        setContentView(layoutCompat);
        setCanceledOnTouchOutside(false);
        initWindow();
    }

    private void initWindow() {
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.CENTER;
        }
    }
}
