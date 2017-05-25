package com.lauren.simplenews.detail;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lauren.simplenews.mvp.ISchedulerProvider;
import com.lauren.simplenews.utils.ActivityUtils;

import rx.subscriptions.CompositeSubscription;

public class NewsDetailPresenter implements DetailContract.Presenter{
    private WebView mWebView;


    private final DetailContract.View mView;//view接口 用于更新UI
    private final ISchedulerProvider mSchedulerProvider;
    private CompositeSubscription mSubscriptions;

    public NewsDetailPresenter(DetailContract.View view, ISchedulerProvider schedulerProvider) {
        mView = ActivityUtils.checkNotNull(view, "view cannot be null!");
        mSchedulerProvider = ActivityUtils.checkNotNull(schedulerProvider, "schedulerProvider cannot be null!");
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void loadUrl(String newUrl) {
        if (mWebView != null) {
            mWebView.loadUrl(newUrl);
        }
    }

    @Override
    public void init(WebView webView) {
        mWebView = webView;
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        mWebView.setWebViewClient(new mWebViewClient());
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    private class mWebViewClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (TextUtils.isEmpty(url)){
                return true;
            }
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            mView.showLoadErrorMessage(description);
        }
    }

}
