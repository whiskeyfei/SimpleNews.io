package com.kong.app.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.kong.R;
import com.kong.app.news.base.ThemeActivity;
import com.library.utils.StringUtils;

/**
 * Created by CaoPengfei on 17/6/14.
 */

public class BrowserActivity extends ThemeActivity {

    public static final String BWO_KEY = "bwo_key";
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.browser_toolbar);
        initToolBar(toolbar, R.string.demo);
        mWebView = (WebView) findViewById(R.id.browser_webView);
        mProgressBar = (ProgressBar) findViewById(R.id.browser_progress);

        mWebView.setWebChromeClient(new WebChromeClient(){

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

        Intent intent = getIntent();
        if (intent != null){
            mUrl = intent.getStringExtra(BWO_KEY);
        }
        if (!StringUtils.isEmpty(mUrl)){
            mWebView.loadUrl(mUrl);
        }else{

        }
    }
}
