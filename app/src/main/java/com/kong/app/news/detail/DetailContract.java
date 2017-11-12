package com.kong.app.news.detail;

import com.kong.lib.SafeWebView;
import com.kong.lib.mvp.BasePresenter;
import com.kong.lib.mvp.BaseView;


/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface DetailContract {

    interface View extends BaseView<Presenter> {
        void showLoadErrorMessage(String description);
    }

    interface Presenter extends BasePresenter {
        void init(SafeWebView webView);

        void loadUrl(String newUrl);
    }
}
