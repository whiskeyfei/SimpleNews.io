package com.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.library.AppRun;

public class ToolsUtil {

    public static final int getHeightInPx(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static final int getWidthInPx(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void intentToUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            showToast("暂无支持！");
        }
    }

    public static void intentToEmail(Context context, String email, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("mailto:" + email));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(intent);
        } else {
            showToast("暂无支持！");
        }
    }

    public static void showToast(CharSequence text) {
        makeText(AppRun.get().getApplicationContext(), text, Toast.LENGTH_SHORT);
    }

    public static void showToast(int resId) {
        makeText(AppRun.get().getApplicationContext(), ResourceUtil.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void makeText(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

}
