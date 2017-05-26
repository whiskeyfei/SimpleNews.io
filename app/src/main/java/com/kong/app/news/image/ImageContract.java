package com.kong.app.news.image;


import com.kong.lib.share.common.mvp.BasePresenter;
import com.kong.lib.share.common.mvp.BaseView;

import java.util.List;

public interface ImageContract {
	interface View extends BaseView<Presenter> {
		void onSuccess(List<ImageBean> list);

		void showProgress();

		void hideProgress();

		void showLoadFailMsg();
	}

	interface Presenter extends BasePresenter {
	}
}
