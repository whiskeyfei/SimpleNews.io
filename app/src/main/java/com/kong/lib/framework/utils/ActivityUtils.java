package com.kong.lib.framework.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by whiskeyfei on 16-2-28.
 */
public class ActivityUtils {

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, String message) {
        if (reference == null) {
            throw new NullPointerException(message);
        }
        return reference;
    }

    public static void startActivity(Context context, Intent intent) {
        if (context instanceof Activity) {
            startActivity((Activity) context, intent, -1);
        } else {
            context.startActivity(intent);
        }
    }

    public static void startActivity(Activity a, Intent intent, int requestCode) {
        if (requestCode >= 0) {
            a.startActivityForResult(intent, requestCode);
        } else {
            a.startActivity(intent);
        }
    }
}