package com.kong.app.news;

import com.kong.R;
import com.kong.lib.framework.utils.ActivityUtils;

public class MainPresenter implements MainContract.Presenter{

    private final MainContract.View mView;//view接口 用于更新UI
//    private CompositeSubscription mSubscriptions;

    public MainPresenter(MainContract.View view) {
        mView = ActivityUtils.checkNotNull(view, "view cannot be null!");
//        mSubscriptions = new CompositeSubscription();
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
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
    }
}
