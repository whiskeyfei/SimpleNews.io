package com.lauren.simplenews.view;

import com.whiskeyfei.mvp.base.IMVPBaseView;

public interface INewsDetailView extends IMVPBaseView{

    void showLoadErrorMessage(String description);
}
