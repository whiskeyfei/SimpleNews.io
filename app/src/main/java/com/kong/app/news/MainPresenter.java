package com.kong.app.news;

import android.view.MenuItem;

import com.kong.R;
import com.library.utils.ActivityUtils;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = ActivityUtils.checkNotNull(view, "view cannot be null!");
        mView.setPresenter(this);
    }

    @Override
    public void switchNavigation(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_item_news:
                mView.switchNews();
                item.setChecked(true);
                break;
            case R.id.navigation_item_images:
                mView.switchImages();
                item.setChecked(true);
                break;
            case R.id.navigation_item_about:
                mView.switchAbout();
                break;
            case R.id.navigation_item_demo:
                mView.switchDemo();
                break;
            case R.id.navigation_item_settings:
                mView.switchSetting();
                break;
            default:
                mView.switchNews();
                item.setChecked(true);
                break;
        }
    }

    @Override
    public void start() {

    }
}
