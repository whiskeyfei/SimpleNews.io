package com.kong.web.app.browser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.baselib.base.ToolBarActivity;
import com.baselib.utlis.StringUtils;
import com.kong.web.R;


/**
 * Created by CaoPengfei on 17/6/14.
 */

public class BrowserActivity extends ToolBarActivity {

    public static final String BWO_KEY = "bwo_key";
    public static final String BWO_TITLE = "bwo_title";
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String mUrl;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null) {
            mUrl = intent.getStringExtra(BWO_KEY);
            mTitle = intent.getStringExtra(BWO_TITLE);
            setTitle(mTitle);
        }

        mWebView = (WebView) findViewById(R.id.browser_webView);
        mProgressBar = (ProgressBar) findViewById(R.id.browser_progress);

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

        });

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                showResult();
            }
        });


        if (!StringUtils.isEmpty(mUrl)) {
            mWebView.loadUrl(mUrl);
        } else {
            showError();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_browser2;
    }

    public void showResult() {
        mWebView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    public void showError() {
        mWebView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }
}
