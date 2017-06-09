package com.kong.lib.share.app.news;

import android.content.Context;

import com.kong.app.news.beans.NewModel;

/**
 * Created by CaoPengfei on 17/6/2.
 */

public interface INewsEntry {
    void startAbout(Context context);

    void startDemo(Context context);

    void startMain(Context context);

    void startDetailActivity(Context context, NewModel news);
}
