package com.lauren.simplenews.presenter;

import com.lauren.simplenews.R;
import com.lauren.simplenews.event.IMainPresenter;
import com.whiskeyfei.mvp.base.BasePresenter;
import com.lauren.simplenews.view.IMainView;

public class MainPresenterImpl extends BasePresenter<IMainView> implements IMainPresenter {

    public MainPresenterImpl(IMainView view) {
        attachView(view);
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                getMvpBaseView().switchNews();
                break;
            case R.id.navigation_item_images:
                getMvpBaseView().switchImages();
                break;
            case R.id.navigation_item_about:
                getMvpBaseView().switchAbout();
                break;
            default:
                getMvpBaseView().switchNews();
                break;
        }
    }
}
