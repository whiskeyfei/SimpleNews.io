package com.kong.app.search;

import android.content.Context;
import android.content.Intent;

/**
 * Created by wuming on 19/6/7.
 * <p>
 * search 统一入口
 */
public class SearchEntry {

    public static void startSearchActivity(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}
