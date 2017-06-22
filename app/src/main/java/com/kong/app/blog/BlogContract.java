package com.kong.app.blog;


import com.kong.app.blog.model.Feed;
import com.kong.lib.share.common.mvp.BasePresenter;
import com.kong.lib.share.common.mvp.BaseView;

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
