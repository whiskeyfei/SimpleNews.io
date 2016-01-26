package com.whiskeyfei.mvp.base;

public interface IMVPPresenter<V extends IMVPBaseView> {
	/**
	 * 附加view回调接口
	 * 
	 * @param mvpBaseView
	 */
	void attachView(V mvpBaseView);

	/**
	 * 释放view接口
	 */
	void detachView();

}
