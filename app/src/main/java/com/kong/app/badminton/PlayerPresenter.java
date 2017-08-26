package com.kong.app.badminton;

import android.util.Log;

import com.kong.app.news.commons.ApiConstants;
import com.kong.lib.mvp.ISchedulerProvider;
import com.kong.lib.mvp.Injection;
import com.kong.lib.utils.GsonUtils;
import com.kong.lib.utils.ListUtils;
import com.kong.lib.utils.OkHttpUtils;
import com.kong.lib.utils.StringUtils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by CaoPengfei on 17/7/3.
 */

public class PlayerPresenter implements PlayerContract.Presenter {

    private static final String TAG = "PlayerPresenter";
    private final PlayerContract.View mView;
    private final ISchedulerProvider mSchedulerProvider;
    private final CompositeSubscription mSubscriptions;

    public PlayerPresenter(PlayerContract.View view) {
        mView = view;
        mSchedulerProvider = Injection.provideSchedulerProvider();
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    private void startTask(String url) {
        mSubscriptions.clear();
        Subscription subscription = getObservable(url).subscribe(getSubscriber());
        mSubscriptions.add(subscription);
    }

    public Observable<Players> getObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<Players>(){

            @Override
            public void call(final Subscriber<? super Players> subscriber) {
                OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {

                    @Override
                    public void onSuccess(String response) {
                        if (StringUtils.isEmpty(response)) {
                            subscriber.onError(null);
                            return;
                        }
                        Players model = GsonUtils.deserialize(response, Players.class);
                        if (model == null || ListUtils.isEmpty(model.getData())) {
                            subscriber.onError(null);
                            return;
                        }
                        subscriber.onNext(model);
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

    private Subscriber<Players> getSubscriber() {
        return new Subscriber<Players>() {
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
            public void onNext(Players players) {
                mView.onSuccess(players);
            }
        };
    }

    @Override
    public void loadMore(int pageIndex) {
//        Log.i(TAG, "loadMore mPageIndex:"+pageIndex);
//        if (pageIndex > 3 ){
//            Log.i(TAG, "loadMore page");
//            return;
//        }
        String url = ApiConstants.BADMINTON_URL + pageIndex;
        Log.i(TAG,"loadMore url:"+url);
        startTask(url);
    }
}
