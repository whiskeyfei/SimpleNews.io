package com.kong.app.news.list;

import android.util.Log;

import com.kong.app.news.beans.NewModel;
import com.kong.app.news.beans.NewResultModel;
import com.kong.lib.share.common.mvp.ISchedulerProvider;
import com.library.utils.ActivityUtils;
import com.library.utils.GsonUtils;
import com.library.utils.ListUtils;
import com.library.utils.OkHttpUtils;
import com.library.utils.StringUtils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.kong.app.news.commons.ApiConstants.MAX_PAGE;

public class NewsPresenter implements NewsContract.Presenter {

    private static final String TAG = "NewsPresenter";

    private final NewsContract.View mView;
    private final ISchedulerProvider mSchedulerProvider;
    private CompositeSubscription mSubscriptions;

    public NewsPresenter(NewsContract.View view, ISchedulerProvider schedulerProvider) {
        mView = ActivityUtils.checkNotNull(view, "view cannot be null!");
        mSchedulerProvider = ActivityUtils.checkNotNull(schedulerProvider, "schedulerProvider cannot be null!");
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    private void startTask(String url) {
        Log.i(TAG, "startTask url" + url);
        mSubscriptions.clear();
        Subscription subscription = getObservable(url).subscribe(getSubscriber());
        mSubscriptions.add(subscription);
    }

    public Observable<List<NewModel>> getObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<List<NewModel>>() {
            @Override
            public void call(final Subscriber<? super List<NewModel>> subscriber) {
                OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {

                    @Override
                    public void onSuccess(String response) {
                        if (StringUtils.isEmpty(response)) {
                            Log.e(TAG, "response is null");
                            subscriber.onError(null);
                            return;
                        }
                        NewResultModel model = GsonUtils.deserialize(response, NewResultModel.class);
                        if (model == null || ListUtils.isEmpty(model.newModellist)) {
                            Log.e(TAG, "model is null");
                            subscriber.onError(null);
                            return;
                        }
//                        Log.e(TAG,"getNewList() -> model:" + model);
                        subscriber.onNext(model.newModellist);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        subscriber.onError(e);
                    }
                };
                OkHttpUtils.get(url, loadNewsCallback);
            }
        }).subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui());
    }

    @Override
    public void loadNews(final String url, final int pageIndex) {
        if (pageIndex >= MAX_PAGE) {
            mView.setEnd(true);
            return;
        }

        String currentUrl = url + "&page=" + pageIndex;
        if (pageIndex == 1) {
            mView.showProgress();
        }
        startTask(currentUrl);
    }

    private Observer<List<NewModel>> getSubscriber() {
        return new Observer<List<NewModel>>() {
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
            public void onNext(List<NewModel> list) {
                mView.addNews(list);
            }
        };
    }

    @Override
    public void start() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
