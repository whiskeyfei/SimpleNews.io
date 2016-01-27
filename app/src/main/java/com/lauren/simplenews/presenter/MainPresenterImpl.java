package com.lauren.simplenews.presenter;

import com.lauren.simplenews.R;
import com.whiskeyfei.mvp.base.BasePresenter;
import com.lauren.simplenews.view.IMainView;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/13
 */
public class MainPresenterImpl extends BasePresenter<IMainView> implements IMainPresenter {

    public MainPresenterImpl(IMainView view) {
        attachView(view);
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                getMvpBaseView().switch2News();
                break;
            case R.id.navigation_item_images:
                getMvpBaseView().switch2Images();
                break;
            case R.id.navigation_item_weather:
                getMvpBaseView().switch2Weather();
                break;
            case R.id.navigation_item_about:
                getMvpBaseView().switch2About();
                break;
            default:
                getMvpBaseView().switch2News();
                break;
        }
    }
}
