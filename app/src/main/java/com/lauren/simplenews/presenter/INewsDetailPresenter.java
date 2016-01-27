package com.lauren.simplenews.presenter;

import android.webkit.WebView;

public interface INewsDetailPresenter {

    void init(WebView mWebView);

    void loadUrl(String newUrl);
}
