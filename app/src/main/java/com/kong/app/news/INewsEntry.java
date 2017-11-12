package com.kong.app.news;

import android.content.Context;

import com.kong.app.news.beans.NewModel;

/**
 * Created by CaoPengfei on 17/6/2.
 */

public interface INewsEntry {
    void startCommon(Context context, Class<?> cls);

    void startAbout(Context context);

//    void startDemo(Context context);

    void startSetting(Context context);

//    void startNote(Context context);

//    void startNote(Context context, Intent intent);

    void startMain(Context context);

    void startBrowser(Context context, String url);

    void startBrowser(Context context, String url, String title);

    void startDetailActivity(Context context, NewModel news);
}
