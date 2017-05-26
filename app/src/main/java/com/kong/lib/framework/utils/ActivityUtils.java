package com.kong.lib.framework.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.kong.R;
import com.kong.app.news.beans.NewModel;
import com.kong.app.news.commons.ApiConstants;
import com.kong.app.news.detail.NewsDetailActivity;
import com.library.utils.ResourceUtil;

/**
 * Created by whiskeyfei on 16-2-28.
 */
public class ActivityUtils {
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(T reference, String message) {
        if (reference == null) {
            throw new NullPointerException(message);
        }
        return reference;
    }

    public static void startActivity(Context context, Intent intent) {
        if (context instanceof Activity) {
            startActivity((Activity) context, intent, -1);
        } else {
            context.startActivity(intent);
        }
    }

    public static void startActivity(Activity a, Intent intent, int requestCode) {
        if (requestCode >= 0) {
            a.startActivityForResult(intent, requestCode);
        } else {
            a.startActivity(intent);
        }
    }

    public static void startDetailActivity(Context context, NewModel news, View view) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(ApiConstants.NEWS_KEY, news);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, ResourceUtil.getString(R.string.transition_news_img));
        startActivity(context, intent, options.toBundle());
    }

    public static void startActivity(Context context, Intent intent, Bundle options) {
        ActivityCompat.startActivity((Activity) context, intent, options);
    }
}
