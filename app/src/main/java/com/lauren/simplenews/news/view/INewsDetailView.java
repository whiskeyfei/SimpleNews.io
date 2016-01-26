package com.lauren.simplenews.news.view;

import com.whiskeyfei.mvp.base.IMVPBaseView;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/21
 */
public interface INewsDetailView extends IMVPBaseView{

    void showNewsDetialContent(String newsDetailContent);

    void showProgress();

    void hideProgress();

}
