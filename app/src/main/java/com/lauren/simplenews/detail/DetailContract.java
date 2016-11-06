package com.lauren.simplenews.detail;

import android.webkit.WebView;

import com.lauren.simplenews.mvp.BasePresenter;
import com.lauren.simplenews.mvp.BaseView;

/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface DetailContract {

    interface View extends BaseView<Presenter> {
        void showLoadErrorMessage(String description);
    }

    interface Presenter extends BasePresenter {
        void init(WebView webView);

        void loadUrl(String newUrl);
    }
}
