package com.baselib.app;

import android.content.Context;

public class AppRunTime {

    private Context mContext = null;

    private static AppRunTime sInstance;

    public static AppRunTime get() {
        if (sInstance == null) {
            sInstance = new AppRunTime();
        }
        return sInstance;
    }

    public void init(Context context) {
        if (mContext != null) {
            throw new IllegalStateException("App just call once init!!");
        }
        mContext = context;
    }

    private void ensureAppContext() {
        if (mContext == null) {
            throw new IllegalStateException("App not call init!!.");
        }
    }

    public Context getApplicationContext() {
        ensureAppContext();
        return mContext;
    }

}
