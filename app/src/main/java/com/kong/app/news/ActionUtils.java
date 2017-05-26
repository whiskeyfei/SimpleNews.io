package com.kong.app.news;

import android.content.Context;
import android.content.Intent;

/**
 * Created by CaoPengfei on 17/5/26.
 */

public class ActionUtils {
    public static void startAbout(Context context){
        context.startActivity(new Intent(context,AboutActivity.class));
    }
}
