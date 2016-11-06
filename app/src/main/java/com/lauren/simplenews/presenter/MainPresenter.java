package com.lauren.simplenews.presenter;

import com.lauren.simplenews.R;
import com.lauren.simplenews.mvp.BaseSchedulerProvider;
import com.lauren.simplenews.ui.MainContract;
import com.lauren.simplenews.utils.ActivityUtils;

import rx.subscriptions.CompositeSubscription;

public class MainPresenter implements MainContract.Presenter{

    private final MainContract.View mView;//view接口 用于更新UI
    private final BaseSchedulerProvider mSchedulerProvider;
    private CompositeSubscription mSubscriptions;

    public MainPresenter(MainContract.View view, BaseSchedulerProvider schedulerProvider) {
        mView = ActivityUtils.checkNotNull(view, "view cannot be null!");
        mSchedulerProvider = ActivityUtils.checkNotNull(schedulerProvider, "schedulerProvider cannot be null!");
        mSubscriptions = new CompositeSubscription();
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
        mSubscriptions.clear();
    }
}
