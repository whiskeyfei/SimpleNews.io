package com.kong.web.service;

import android.content.Context;
import android.content.Intent;

import com.baselib.utlis.ActivityUtils;
import com.kong.web.app.browser.BrowserActivity;
import com.kong.web.app.detail.NewsDetailActivity;
import com.luojilab.componentservice.toast.detail.IDetailService;

public class DetailService implements IDetailService {

    @Override
    public void startBrowser(Context context, String url) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(BrowserActivity.BWO_KEY,url);
        ActivityUtils.startActivity(context,intent);
    }

    @Override
    public void startBrowser(Context context, String url, String title) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra(BrowserActivity.BWO_KEY, url);
        intent.putExtra(BrowserActivity.BWO_TITLE, title);
        ActivityUtils.startActivity(context,intent);
    }

    @Override
    public void startDetailActivity(Context context, String title, String newUrl) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.TITLE,title);
        intent.putExtra(NewsDetailActivity.URL,newUrl);
        ActivityUtils.startActivity(context, intent);
    }
}
