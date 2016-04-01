package com.lauren.simplenews.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.lauren.simplenews.app.AppApplication;

/**
 * Created by whiskeyfei on 16-2-28.
 */
public class ResourceUtil {

    public static String getString(int resId) {
        return getResource().getString(resId);
    }

    public static int getColor(int resId) {
        return getResource().getColor(resId);
    }

    public static Drawable getDrawable(int resId) {
        return getResource().getDrawable(resId);
    }

    public static int getDimen(int dimen) {
        return (int) getResource().getDimension(dimen);
    }

    public static Resources getResource() {
        return getContext().getResources();
    }

    public static Context getContext() {
        return AppApplication.get();
    }
}
