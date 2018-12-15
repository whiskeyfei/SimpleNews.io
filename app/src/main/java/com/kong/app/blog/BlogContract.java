package com.kong.app.blog;


import com.baselib.mvp.BasePresenter;
import com.baselib.mvp.BaseView;
import com.kong.app.blog.model.Feed;

/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface BlogContract {

    interface View extends BaseView<Presenter> {

        void onSuccess(Feed feed);

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BasePresenter {

    }
}
