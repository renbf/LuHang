package com.dalaiye.luhang.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import com.dalaiye.luhang.R;
import com.gfc.library.config.ECActivityCallback;
import com.gfc.library.config.ECLibrary;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.logging.Level;

import okhttp3.Interceptor;

/**
 * @author admin
 * @date 2019/4/10
 * @function Application
 **/
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        ECLibrary.init(this)
                .withInterceptor(logInterceptor())
                .configure();
        registerActivityLifecycleCallbacks(new ECActivityCallback());

    }

    /**
     * log日志拦截器
     */
    private Interceptor logInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("LU_HANG");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        return loggingInterceptor;
    }

    static { //配置刷新控件
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.transparent, R.color.color_blue).setHeaderHeight(60);
                return new ClassicsHeader(context).setTextSizeTitle(13).setTextSizeTime(11);
            }
        });
    }
}
