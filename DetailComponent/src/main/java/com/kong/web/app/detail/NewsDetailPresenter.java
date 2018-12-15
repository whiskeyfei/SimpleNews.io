package com.kong.web.app.detail;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.baselib.app.AppRunTime;
import com.baselib.utlis.ActivityUtils;
import com.baselib.widget.SafeWebView;


public class NewsDetailPresenter implements DetailContract.Presenter {

    private static final String TAG = "NewsDetailPresenter";

    private SafeWebView mWebView;
    private final DetailContract.View mView;

    public NewsDetailPresenter(DetailContract.View view) {
        mView = ActivityUtils.checkNotNull(view, "view cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void loadUrl(String newUrl) {
        if (mWebView != null) {
            mWebView.loadUrl(newUrl);
        }
    }

    @Override
    public void init(SafeWebView webView) {
        mWebView = webView;
        if (mWebView == null) return;
        SafeWebView.disableAccessibility(AppRunTime.get().getApplicationContext());
        initWebSettings(mWebView.getSettings());
        mWebView.setWebViewClient(new mWebViewClient());
    }

    private void initWebSettings(WebSettings settings) {
        if (settings == null) return;
        settings.setJavaScriptEnabled(false);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setTextZoom(100);
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setAllowFileAccess(false);
    }

    @Override
    public void start() {

    }

    private class mWebViewClient extends WebViewClient {


        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "shouldOverrideUrlLoading url: " + url);
            if (TextUtils.isEmpty(url)) {
                return true;
            }
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.i(TAG, "onPageStarted url: " + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.i(TAG, "onPageFinished url: " + url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.i(TAG, "onLoadResource url: " + url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            Log.e(TAG, "onReceivedSslError error: " + error.toString());
            handler.proceed();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            Log.e(TAG, "onReceivedError error: " + error.getErrorCode());
            mView.showLoadErrorMessage(error.getDescription().toString());
        }
    }

}