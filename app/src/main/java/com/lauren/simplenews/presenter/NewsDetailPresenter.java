package com.lauren.simplenews.presenter;

import com.lauren.simplenews.beans.NewsDetailBean;
import com.lauren.simplenews.model.INewsModel;
import com.lauren.simplenews.model.NewsModelImpl;
import com.lauren.simplenews.view.INewsDetailView;
import com.orhanobut.logger.Logger;
import com.whiskeyfei.mvp.base.BasePresenter;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class NewsDetailPresenter extends BasePresenter<INewsDetailView> implements INewsDetailPresenter {

    private INewsModel mNewsModel;
    private Subscription mSubscription;

    public NewsDetailPresenter(INewsDetailView detailView) {
        attachView(detailView);
        mNewsModel = new NewsModelImpl();
    }

    @Override
    public void detachView() {
        super.detachView();
        if(mSubscription != null){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void loadNewsDetail(String docId) {
        checkViewAttached();
        loadNew(docId);
    }

    private void loadNew(String docId) {
        mSubscription = mNewsModel.getDetailNew(docId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        getMvpBaseView().showProgress();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetailBean>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("onCompleted");
                        getMvpBaseView().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("onError e" + e);
                        getMvpBaseView().hideProgress();
                    }

                    @Override
                    public void onNext(NewsDetailBean newsDetailBean) {
                        Logger.d("onNext newsDetailBean:" + newsDetailBean);
                        getMvpBaseView().showNewsDetialContent(newsDetailBean.getBody());
                    }
                });
    }

}
