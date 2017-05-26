package com.kong.app.news.list;


import com.kong.app.news.beans.NewModel;
import com.kong.lib.share.common.mvp.BasePresenter;
import com.kong.lib.share.common.mvp.BaseView;

import java.util.List;

/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface NewsContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void addNews(List<NewModel> newsList);

        void hideProgress();

        void showLoadFailMsg();
    }

    interface Presenter extends BasePresenter {
        void loadNews(int type, int page);
    }
}
