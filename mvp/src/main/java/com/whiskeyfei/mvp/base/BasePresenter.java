package com.whiskeyfei.mvp.base;

/**
 * 
 * @author whiskeyfei
 * 
 * @param <T>
 *            mvpBaseView interface
 */
public class BasePresenter<T extends IMVPBaseView> implements IMVPPresenter<T> {
	private T mMvpBaseView;

	@Override
	public void attachView(T mvpBaseView) {
		mMvpBaseView = mvpBaseView;
	}

	@Override
	public void detachView() {
		mMvpBaseView = null;
	}

	public T getMvpBaseView() {
		return mMvpBaseView;
	}

	/**
	 * 是否附加view接口
	 * 
	 * @return true mvpBaseView != null
	 */
	public boolean isViewAttached() {
		return mMvpBaseView != null;
	}

	/**
	 * 检查是否绑定view接口
	 */
	public void checkViewAttached() {
		if (!isViewAttached())
			throw new RuntimeException(
					"Please call BasePresenter.attachView(IMVPBaseView) before"
							+ " requesting data to the Presenter");
	}
}
