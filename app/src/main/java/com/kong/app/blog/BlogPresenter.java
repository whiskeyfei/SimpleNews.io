package com.kong.app.blog;

import com.google.gson.reflect.TypeToken;
import com.kong.app.blog.model.Feed;
import com.kong.app.news.commons.ApiConstants;
import com.kong.lib.mvp.ISchedulerProvider;
import com.kong.lib.mvp.Injection;
import com.library.utils.GsonUtils;
import com.library.utils.OkHttpUtils;
import com.library.utils.StringUtils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by CaoPengfei on 17/6/21.
 */

public class BlogPresenter implements BlogContract.Presenter {

    private final BlogContract.View mView;
    private final ISchedulerProvider mSchedulerProvider;
    private final CompositeSubscription mSubscriptions;

    public BlogPresenter(BlogContract.View view) {
        mView = view;
        mSchedulerProvider = Injection.provideSchedulerProvider();
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        startTask();
    }

    private void startTask() {
        mSubscriptions.clear();
        Subscription subscription = getObservable().subscribe(getSubscriber());
        mSubscriptions.add(subscription);
    }

    public Observable<Feed> getObservable() {
        return Observable.just(ApiConstants.BLOGS_URL).flatMap(new Func1<String, Observable<Feed>>() {
            @Override
            public Observable<Feed> call(final String url) {
                return Observable.create(new Observable.OnSubscribe<Feed>() {

                    @Override
                    public void call(final Subscriber<? super Feed> subscriber) {
                        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
                            @Override
                            public void onSuccess(String response) {
                                if(StringUtils.isEmpty(response)){
                                    subscriber.onError(null);
                                    return;
                                }
                                Feed feed = GsonUtils.deserialize(response,new TypeToken<Feed>() {}.getType());
                                if(feed == null){
                                    subscriber.onError(null);
                                    return;
                                }
                                subscriber.onNext(feed);
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onFailure(Exception e) {
                                subscriber.onError(e);
                            }
                        };
                        OkHttpUtils.get(url, loadNewsCallback);
                    }
                });
            }
        }) .subscribeOn(mSchedulerProvider.io())
           .observeOn(mSchedulerProvider.ui());
    }

    private Subscriber<Feed> getSubscriber() {
        return new Subscriber<Feed>() {
            @Override
            public void onStart() {
                super.onStart();
                mView.showProgress();
            }

            @Override
            public void onCompleted() {
                mView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
            }

            @Override
            public void onNext(Feed feed) {
                mView.onSuccess(feed);
            }
        };
    }
}
