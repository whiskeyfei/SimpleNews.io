package com.lauren.simplenews.presenter;

import android.webkit.WebView;

public interface INewsDetailPresenter {

    void init(WebView webView);

    void loadUrl(String newUrl);
}
