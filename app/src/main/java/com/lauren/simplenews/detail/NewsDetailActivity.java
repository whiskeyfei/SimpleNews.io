package com.lauren.simplenews.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.lauren.simplenews.R;
import com.lauren.simplenews.app.AppApplication;
import com.lauren.simplenews.beans.NewModel;
import com.lauren.simplenews.commons.ApiConstants;
import com.lauren.simplenews.mvp.Injection;
import com.lauren.simplenews.utils.ImageLoaderUtils;

public class NewsDetailActivity extends AppCompatActivity implements DetailContract.View {

    private NewModel mNews;
    private ImageView mImageView;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mImageView = (ImageView)findViewById(R.id.ivImage);
        mWebView = (WebView)findViewById(R.id.webview_content);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mNews.title);

        ImageLoaderUtils.display(AppApplication.get(), mImageView, mNews.imageUrl);

        mNewsDetailPresenter = new NewsDetailPresenter(this, Injection.provideSchedulerProvider());
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
