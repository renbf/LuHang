package com.gfc.library.config;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * @author 王文波
 * @date 2019/1/10
 * @function 功能
 */
public class ECActivityCallback implements Application.ActivityLifecycleCallbacks {


    private ArrayList<Activity> mActivities;

    public ECActivityCallback() {
        mActivities = ECLibrary.getConfiguration(ConfigKeys.ACTIVITIES);
    }

    @Override
    public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
        mActivities.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivities.remove(activity);
    }
}
