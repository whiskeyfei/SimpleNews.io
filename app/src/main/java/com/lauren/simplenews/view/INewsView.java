package com.lauren.simplenews.view;

import com.lauren.simplenews.beans.NewModel;
import com.whiskeyfei.mvp.base.IMVPBaseView;

import java.util.List;

public interface INewsView extends IMVPBaseView {

    void showProgress();

    void addNews(List<NewModel> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
