package com.lauren.simplenews.news;

import com.lauren.simplenews.beans.NewModel;
import com.lauren.simplenews.mvp.BasePresenter;
import com.lauren.simplenews.mvp.BaseView;

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
