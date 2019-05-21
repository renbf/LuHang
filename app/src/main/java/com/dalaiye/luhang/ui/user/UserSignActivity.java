package com.dalaiye.luhang.ui.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.widget.sign.SignatureView;
import com.gfc.library.base.BaseActivity;

import java.io.File;
import java.io.IOException;

/**
 * @author admin
 * @date 2019/4/17
 * @function 签名的activity
 **/
public class UserSignActivity extends BaseActivity implements View.OnClickListener {

    public static final String USER_SIGN_PATH = "user_sign_path";
    public static final int USER_SIGN_CODE = 3546;

    private SignatureView mSignView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign);
        initView();
    }

    private void initView() {
        mSignView = findViewById(R.id.sign_view);
        findViewById(R.id.tv_save).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:

                final String path = getPath(getApplicationContext()) + System.currentTimeMillis() + ".png";
                try {
                    mSignView.save(path, true, 0);
                    Intent intent = new Intent();
                    intent.putExtra(USER_SIGN_PATH, mSignView.getSavePath());
                    setResult(USER_SIGN_CODE, intent);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private static String getPath(Context context) {
        String cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath() + "/luban_disk_cache/";
        } else {
            cachePath = context.getCacheDir().getPath() + "/luban_disk_cache/";
        }
        File file = new File(cachePath);
        if (file.mkdirs()) {
            return cachePath;
        }
        return cachePath;
    }
}
