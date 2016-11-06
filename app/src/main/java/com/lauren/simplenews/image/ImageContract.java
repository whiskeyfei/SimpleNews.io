package com.lauren.simplenews.image;

import com.lauren.simplenews.mvp.BasePresenter;
import com.lauren.simplenews.mvp.BaseView;

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
