package com.lauren.simplenews.event;

import android.webkit.WebView;

public interface INewsDetailPresenter {

    void init(WebView webView);

    void loadUrl(String newUrl);
}
