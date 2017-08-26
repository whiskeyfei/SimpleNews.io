package com.kong.lib;

import android.content.Context;

/**
 * Created by CaoPengfei on 17/5/24.
 */

public class AppRun {

    private Context mContext = null;

    private static AppRun sInstance;

    public static AppRun get() {
        if (sInstance == null) {
            sInstance = new AppRun();
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
