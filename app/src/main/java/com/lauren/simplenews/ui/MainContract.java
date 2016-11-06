package com.lauren.simplenews.ui;

import com.lauren.simplenews.mvp.BasePresenter;
import com.lauren.simplenews.mvp.BaseView;

/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void switchNews();
        void switchImages();
        void switchAbout();
    }

    interface Presenter extends BasePresenter {
        void switchNavigation(int id);
    }
}
