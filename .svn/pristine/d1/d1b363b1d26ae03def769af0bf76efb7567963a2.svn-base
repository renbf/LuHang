package com.gfc.library.config;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @author Administrator
 */

public final class ECLibrary {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs() 
                .put(ConfigKeys.APPLICATION, context);
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Application getApplication() {
        return getConfiguration(ConfigKeys.APPLICATION);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

}
