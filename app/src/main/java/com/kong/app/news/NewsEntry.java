package com.kong.app.news;

import android.content.Context;
import android.content.Intent;

import com.kong.app.news.beans.NewModel;
import com.kong.app.news.commons.ApiConstants;
import com.kong.app.news.detail.NewsDetailActivity;
import com.kong.lib.framework.utils.ActivityUtils;
import com.kong.lib.share.app.news.INewsEntry;

/**
 * Created by CaoPengfei on 17/5/26.
 *
 * News 所有跳转入口
 * 为了方便以后拆分，暂时写成接口形式
 */

public class NewsEntry implements INewsEntry {

    private static class NewsEntryHelper {
        private static NewsEntry sIns = new NewsEntry();
    }

    public static INewsEntry get() {
        return NewsEntryHelper.sIns;
    }

    @Override
    public void startAbout(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        ActivityUtils.startActivity(context,intent);
    }

    @Override
    public void startMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        ActivityUtils.startActivity(context,intent);
    }

    @Override
    public void startDetailActivity(Context context, NewModel news) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(ApiConstants.NEWS_KEY, news);
        ActivityUtils.startActivity(context, intent);
    }
}
