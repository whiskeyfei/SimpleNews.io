package com.kong.app.news.image;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kong.app.news.commons.ApiConstants;
import com.library.utils.ActivityUtils;
import com.kong.lib.share.common.mvp.ISchedulerProvider;
import com.library.utils.ListUtils;
import com.library.utils.OkHttpUtils;
import com.library.utils.StringUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class ImagePresenter implements ImageContract.Presenter {
    private static final String TAG = "ImagePresenter";

    private final ImageContract.View mView;//view接口 用于更新UI
    private final ISchedulerProvider mSchedulerProvider;
    private CompositeSubscription mSubscriptions;

    public ImagePresenter(ImageContract.View statisticsView,ISchedulerProvider schedulerProvider) {
        mView = ActivityUtils.checkNotNull(statisticsView, "StatisticsView cannot be null!");
        mSchedulerProvider = ActivityUtils.checkNotNull(schedulerProvider,"schedulerProvider cannot be null!");
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    private void startTask() {
        mSubscriptions.clear();
        Subscription subscription = getObservable().subscribe(getSubscriber());
        mSubscriptions.add(subscription);
    }


    public Observable<List<ImageBean>> getObservable() {
        return Observable.just(ApiConstants.IMAGES_URL).flatMap(new Func1<String, Observable<List<ImageBean>>>() {
            @Override
            public Observable<List<ImageBean>> call(final String url) {
                //可以拼接url
                return Observable.create(new Observable.OnSubscribe<List<ImageBean>>() {

                    @Override
                    public void call(final Subscriber<? super List<ImageBean>> subscriber) {
                        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
                            @Override
                            public void onSuccess(String response) {
                                Log.d(TAG, "onSuccess response:" + response);
                                if(StringUtils.isEmpty(response)){
                                    subscriber.onError(null);
                                    return;
                                }
                                Gson gson = new Gson();
                                List<ImageBean> iamgeBeanList = gson.fromJson(response, new TypeToken<List<ImageBean>>() {}.getType());
                                if(ListUtils.isEmpty(iamgeBeanList)){
                                    subscriber.onError(null);
                                    return;
                                }
                                subscriber.onNext(iamgeBeanList);
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

    private Subscriber<List<ImageBean>> getSubscriber() {
        return new Subscriber<List<ImageBean>>() {
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
                mView.showLoadFailMsg();
            }

            @Override
            public void onNext(List<ImageBean> list) {
                mView.onSuccess(list);
            }
        };
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {
        startTask();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
