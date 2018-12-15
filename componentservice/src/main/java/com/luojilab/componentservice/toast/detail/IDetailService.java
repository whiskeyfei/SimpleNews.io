package com.luojilab.componentservice.toast.detail;

import android.content.Context;

public interface IDetailService {

    void startBrowser(Context context, String url);

    void startBrowser(Context context, String url, String title);

    void startDetailActivity(Context context, String title, String newUrl);
}
