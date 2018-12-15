package com.kong.app.news.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.baselib.app.AppRunTime;

/**
 * Created by CaoPengfei on 17/6/17.
 */

public class SettingsUtil {

    public static int getThemeIndex() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AppRunTime.get().getApplicationContext());
        return prefs.getInt("ThemeIndex", 0);
    }

    public static void setThemeIndex(int index) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AppRunTime.get().getApplicationContext());
        prefs.edit().putInt("ThemeIndex", index).apply();
    }
}
