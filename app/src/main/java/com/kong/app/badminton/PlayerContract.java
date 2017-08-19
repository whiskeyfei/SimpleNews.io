package com.kong.app.badminton;


import com.kong.lib.mvp.BasePresenter;
import com.kong.lib.mvp.BaseView;

/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface PlayerContract {

    interface View extends BaseView<Presenter> {

        void onSuccess(Players players);

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BasePresenter {
        void loadMore(final int pageIndex);
    }
}
