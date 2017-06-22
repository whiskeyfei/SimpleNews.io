package com.kong.app.news;


import android.view.MenuItem;

import com.kong.lib.share.common.mvp.BasePresenter;
import com.kong.lib.share.common.mvp.BaseView;

/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void switchNews();
        void switchImages();
        void switchAbout();
        void switchDemo();
        void switchBlog();
        void switchSetting();
    }

    interface Presenter extends BasePresenter {
        void switchNavigation(MenuItem item);
    }
}
