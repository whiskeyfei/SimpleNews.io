package com.kong.lib.mvp;

/**
 * 考虑使用 RxJava 时，订阅关系
 */
public interface BaseSubPresenter extends BasePresenter{

	void subscribe();

	void unsubscribe();

}
