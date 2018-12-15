package com.kong.web.app.detail;


import com.baselib.mvp.BasePresenter;
import com.baselib.mvp.BaseView;
import com.baselib.widget.SafeWebView;

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
