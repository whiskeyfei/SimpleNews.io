package com.kong.app.news;

import android.content.Context;
import android.content.Intent;

import com.kong.app.news.beans.NewModel;
import com.kong.app.news.ui.AboutActivity;
import com.kong.app.news.ui.SettingActivity;
import com.kong.home.HomeActivity;
import com.kong.lib.utils.ActivityUtils;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.componentservice.toast.detail.IDetailService;

/**
 * Created by CaoPengfei on 17/5/26.
 * <p>
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
    public void startCommon(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        ActivityUtils.startActivity(context, intent);
    }

    @Override
    public void startAbout(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        ActivityUtils.startActivity(context, intent);
    }

//    @Override
//    public void startDemo(Context context) {
//        Intent intent = new Intent(context, DemoActivity.class);
//        ActivityUtils.startActivity(context,intent);
//    }

    @Override
    public void startSetting(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        ActivityUtils.startActivity(context, intent);
    }

//    @Override
//    public void startNote(Context context) {
//        Intent intent = new Intent(context, NoteActivity.class);
//        ActivityUtils.startActivity(context,intent);
//    }

//    @Override
//    public void startNote(Context context, Intent intent) {
//        ActivityUtils.startActivity(context,intent);
//    }

    @Override
    public void startMain(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        ActivityUtils.startActivity(context, intent);
    }

    @Override
    public void startBrowser(Context context, String url) {
        IDetailService service = (IDetailService) Router.getInstance().getService(IDetailService.class.getSimpleName());
        if (service != null) {
            service.startBrowser(context, url);
        }
    }

    @Override
    public void startBrowser(Context context, String url, String title) {
        IDetailService service = (IDetailService) Router.getInstance().getService(IDetailService.class.getSimpleName());
        if (service != null) {
            service.startBrowser(context, title, url);//TODO
//            service.startDetailActivity(context, title, url);
        }
    }

    @Override
    public void startDetailActivity(Context context, NewModel news) {
        IDetailService service = (IDetailService) Router.getInstance().getService(IDetailService.class.getSimpleName());
        if (service != null) {
            service.startDetailActivity(context, news.title, news.newUrl);
        }
    }
}
