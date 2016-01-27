package com.lauren.simplenews.view;

import com.lauren.simplenews.beans.NewModel;
import com.whiskeyfei.mvp.base.IMVPBaseView;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/18
 */
public interface INewsView extends IMVPBaseView {

    void showProgress();

    void addNews(List<NewModel> newsList);

    void hideProgress();

    void showLoadFailMsg();
}
