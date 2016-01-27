package com.lauren.simplenews.view;

import com.whiskeyfei.mvp.base.IMVPBaseView;

/**
 * Description : IMainView
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/13
 */
public interface IMainView extends IMVPBaseView {
    void switch2News();
    void switch2Images();
    void switch2Weather();
    void switch2About();
}
