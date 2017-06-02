package com.kong.app.news;

import com.kong.R;
import com.kong.lib.framework.utils.ActivityUtils;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = ActivityUtils.checkNotNull(view, "view cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                mView.switchNews();
                break;
            case R.id.navigation_item_images:
                mView.switchImages();
                break;
            case R.id.navigation_item_about:
                mView.switchAbout();
                break;
            default:
                mView.switchNews();
                break;
        }
    }

    @Override
    public void start() {

    }
}
