package com.kong.app.news.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.kong.R;
import com.kong.app.news.base.ThemeActivity;
import com.kong.app.news.beans.NewModel;
import com.kong.app.news.commons.ApiConstants;

public class NewsDetailActivity extends ThemeActivity implements DetailContract.View {

    private NewModel mNews;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        initToolBar(toolbar,mNews.title);
        mWebView = (WebView)findViewById(R.id.detail_webview_content);

        mNewsDetailPresenter = new NewsDetailPresenter(this);
        mNewsDetailPresenter.init(mWebView);
        mNewsDetailPresenter.loadUrl(mNews.newUrl);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null){
            mNews = (NewModel) intent.getSerializableExtra(ApiConstants.NEWS_KEY);
            if (mNews == null){
                mNews = new NewModel();
            }
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
