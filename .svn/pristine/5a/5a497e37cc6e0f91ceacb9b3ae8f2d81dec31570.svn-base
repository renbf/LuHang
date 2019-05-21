package com.gfc.library.config;


import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.tencent.smtt.sdk.QbSdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;


/**
 * @author 王文波
 */
public final class Configurator {

    private static final long TIMEOUT = 60;
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
    private static final ArrayList<Activity> ACTIVITIES = new ArrayList<>();

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 开始配置
     */
    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITIES, ACTIVITIES);

        BUILDER
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * baseUrl
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 拦截器
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        BUILDER.addInterceptor(interceptor);
        return this;
    }

    /**
     * 配置完成
     */
    public final void configure() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        //日志
        Application application = (Application) LATTE_CONFIGS.get(ConfigKeys.APPLICATION);
        if (application != null) {
            //腾讯X5 tbs
            QbSdk.initX5Environment(application.getApplicationContext(), null);
            //网络请求
            OkGo.getInstance()
                    .init(application)
                    .setRetryCount(0)
                    .setOkHttpClient(BUILDER.build());
            //工具类
            Utils.init(application);
        }
    }

    /**
     * 是否配置完成
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
}

