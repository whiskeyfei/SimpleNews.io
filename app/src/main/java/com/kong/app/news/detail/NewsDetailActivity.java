package com.kong.app.news.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.kong.R;
import com.kong.app.news.base.ThemeActivity;

public class NewsDetailActivity extends ThemeActivity implements DetailContract.View {

    public static final String URL = "key_url";
    public static final String TITLE = "key_title";

    private String mTitle;
    private String mUrl;

    private WebView mWebView;
    private DetailContract.Presenter mNewsDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initData();
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        initToolBar(toolbar,mTitle);
        mWebView = (WebView)findViewById(R.id.detail_webview_content);

        mNewsDetailPresenter = new NewsDetailPresenter(this);
        mNewsDetailPresenter.init(mWebView);
        mNewsDetailPresenter.loadUrl(mUrl);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null){
            mTitle = intent.getStringExtra(TITLE);
            mUrl = intent.getStringExtra(URL);
        }
    }

    @Override
    protected void onPause() {
        if (mWebView != null) mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        mWebView = null;
    }

    @Override
    public void showLoadErrorMessage(String description) {
        mWebView.setVisibility(View.GONE);
        Snackbar.make(mWebView,R.string.load_fail,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mNewsDetailPresenter = presenter;
    }
}
