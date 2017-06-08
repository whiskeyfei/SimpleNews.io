package com.kong.app.news.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.kong.R;
import com.kong.app.news.beans.NewModel;
import com.kong.app.news.commons.ApiConstants;
import com.library.AppRun;
import com.kong.lib.share.common.activity.BaseActivity;
import com.library.utils.ImageLoaderUtils;

public class NewsDetailActivity extends BaseActivity implements DetailContract.View {

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        mImageView = (ImageView)findViewById(R.id.detail_image);
        mWebView = (WebView)findViewById(R.id.detail_webview_content);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.detail_collapsing_toolbar_id);
        collapsingToolbar.setTitle(mNews.title);

        ImageLoaderUtils.display(AppRun.get().getApplicationContext(), mImageView, mNews.imageUrl,R.drawable.ic_image_loading, R.drawable.ic_image_loadfail);

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
