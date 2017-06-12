package com.library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.library.AppRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by whiskeyfei on 16-2-28.
 */
public class ResourceUtil {

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    public static int getDimen(int dimen) {
        return (int) getResources().getDimension(dimen);
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static Context getContext() {
        return AppRun.get().getApplicationContext();
    }

    public static String getRawFileStr(int rawId) throws IOException {
        InputStream is = getResources().openRawResource(rawId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        reader.close();
        return sb.toString();
    }
}
