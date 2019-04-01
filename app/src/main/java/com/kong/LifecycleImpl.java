package com.kong;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by wuming on 17/7/21.
 */

public class LifecycleImpl implements Application.ActivityLifecycleCallbacks {

    private static final String TAG = "LifecycleImpl";

    private static LifecycleImpl mInstance;

    public static LifecycleImpl init(Application application) {
        if (mInstance == null) {
            mInstance = new LifecycleImpl();
            application.registerActivityLifecycleCallbacks(mInstance);
        }
        return mInstance;
    }

    public static LifecycleImpl getInstance() {
        if (mInstance == null) {
            mInstance = new LifecycleImpl();
        }
        return mInstance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.i(TAG, activity + " onActivityCreated: ");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.i(TAG, activity + " onActivityStarted: ");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.i(TAG, activity + " onActivityResumed: ");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.i(TAG, activity + " onActivityPaused: ");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i(TAG, activity + " onActivityStopped: ");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle savedInstanceState) {
        Log.i(TAG, activity + " onActivitySaveInstanceState: ");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.i(TAG, activity + " onActivityDestroyed: ");
    }
}
