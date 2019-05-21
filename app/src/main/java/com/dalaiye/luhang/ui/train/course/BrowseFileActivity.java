package com.dalaiye.luhang.ui.train.course;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.dalaiye.luhang.R;
import com.dalaiye.luhang.bean.CourseBean;
import com.dalaiye.luhang.contract.train.BorwseFileContract;
import com.dalaiye.luhang.contract.train.impl.BorwseFilePresenter;
import com.dalaiye.luhang.utils.Md5Tool;
import com.dalaiye.luhang.widget.SuperFileView;
import com.gfc.library.mvp.BaseMvpActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author AnYu
 * @date 2019/4/19
 * @function 浏览文档的页面
 **/
public class BrowseFileActivity extends BaseMvpActivity<BorwseFileContract.IBorwseFilePresenter>
        implements BorwseFileContract.IBorwseFileView, View.OnClickListener, SuperFileView.OnGetFilePathListener {
    private static final String TAG = "BrowseFileActivity";
    private android.support.v7.widget.AppCompatImageView mTopBarBack;
    private android.support.v7.widget.AppCompatTextView mTopBarTitle;
    private com.dalaiye.luhang.widget.SuperFileView mMSuperFileView;
    private CourseBean.RowsBean rowsBean;
    @Override
    protected BorwseFileContract.IBorwseFilePresenter createPresenter() {
        return new BorwseFilePresenter();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_train_browse_file;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rowsBean = (CourseBean.RowsBean) getIntent().getSerializableExtra("rows");

        mTopBarBack = findViewById(R.id.top_bar_back);
        mTopBarTitle = findViewById(R.id.top_bar_title);
        mMSuperFileView = findViewById(R.id.mSuperFileView);

        mTopBarBack.setOnClickListener(this);
        mTopBarTitle.setText("文件预览");

        mMSuperFileView.setOnGetFilePathListener(this);

        String[] perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        new RxPermissions(this)
                .request(perms)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            mMSuperFileView.show();
                        } else {
                            toast(0, "获取权限失败！");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_bar_back :
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onGetFilePath(SuperFileView mSuperFileView2) {
        //1.网络下载、存储路径、
        File cacheFile = getCacheFile(rowsBean.getFileUrl());
        if (cacheFile.exists()) {
            if (cacheFile.length() <= 0) {
                Log.d(TAG, "删除空文件！！");
                cacheFile.delete();
                return;
            }
        }
        mPresenter.getBorwseFile(rowsBean.getFileUrl());
    }
    /***
     * 绝对路径获取缓存文件
     *
     * @param url
     * @return
     */
    private File getCacheFile(String url) {
        File cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/007/"
                + getFileName(url));
        Log.d(TAG, "缓存文件 = " + cacheFile.toString());
        return cacheFile;
    }

    @Override
    public void setBorwseFile(File file) {
        mMSuperFileView.displayFile(file);
    }

    @Override
    public void setBorwseFileFailure() {
        Log.d(TAG, "文件下载失败");
        File file = getCacheFile(rowsBean.getFileUrl());
        if (!file.exists()) {
            Log.d(TAG, "删除下载失败文件");
            file.delete();
        }
    }
    /***
     * 根据链接获取文件名（带类型的），具有唯一性
     *
     * @param url
     * @return
     */
    private String getFileName(String url) {
        String fileName = Md5Tool.hashKey(url) + "." + getFileType(url);
        return fileName;
    }
    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            Log.d(TAG, "paramString---->null");
            return str;
        }
        Log.d(TAG,"paramString:"+paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            Log.d(TAG,"i <= -1");
            return str;
        }


        str = paramString.substring(i + 1);
        Log.d(TAG,"paramString.substring(i + 1)------>"+str);
        return str;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"FileDisplayActivity-->onDestroy");
        if (mMSuperFileView != null) {
            mMSuperFileView.onStopDisplay();
        }
    }
}
