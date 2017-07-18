package com.kong.app.news;


import android.view.MenuItem;

/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface MainContract {

    interface View {
        void switchNews();

        void switchImages();

        void switchAbout();

        void switchDemo();

        void switchBlog();

        void switchSetting();

        void switchNavigation(MenuItem item);

    }
}
