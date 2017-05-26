package com.lauren.simplenews.news;

import android.util.Log;

import com.google.gson.Gson;
import com.lauren.simplenews.beans.NewModel;
import com.lauren.simplenews.beans.NewResultModel;
import com.lauren.simplenews.commons.ApiConstants;
import com.lauren.simplenews.mvp.ISchedulerProvider;
import com.lauren.simplenews.utils.ActivityUtils;
import com.library.utils.ListUtils;
import com.library.utils.OkHttpUtils;
import com.library.utils.StringUtils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class NewsPresenter implements NewsContract.Presenter {

    private static final String TAG = "NewsPresenter";

    private final NewsContract.View mView;//view接口 用于更新UI
    private final ISchedulerProvider mSchedulerProvider;
    private CompositeSubscription mSubscriptions;

    public NewsPresenter(NewsContract.View view, ISchedulerProvider schedulerProvider) {
        mView = ActivityUtils.checkNotNull(view, "view cannot be null!");
        mSchedulerProvider = ActivityUtils.checkNotNull(schedulerProvider, "schedulerProvider cannot be null!");
        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    private void startTask(String url) {
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
                            Log.e(TAG,"response is null");
                            subscriber.onError(null);
                            return;
                        }
                        NewResultModel model = new Gson().fromJson(response, NewResultModel.class);
                        if (model == null || ListUtils.isEmpty(model.newModellist)) {
                            Log.e(TAG,"model is null");
                            subscriber.onError(null);
                            return;
                        }
                        Log.e(TAG,"getNewList() -> model:" + model);
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


    public void loadNews(final int type, final int pageIndex) {
        String url = getUrl(type);
        Log.e(TAG,"url###" + url);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if (pageIndex == 0) {
            mView.showProgress();
        }
        load(url, type);
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


    public void load(String url, int type) {
        startTask(url);
    }

//    private String getUrl(int type, int pageIndex) {
//        StringBuffer sb = new StringBuffer();
//        switch (type) {
//            case NewsFragment.NEWS_TYPE_TOP:
//                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
//                break;
//            case NewsFragment.NEWS_TYPE_RECREATION:
//                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
//                break;
//            case NewsFragment.NEWS_TYPE_SCIENCE:
//                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
//                break;
//            case NewsFragment.NEWS_TYPE_HEALTH:
//                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
//                break;
//            default:
//                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
//                break;
//        }
//        sb.append("/").append(pageIndex).append(Urls.END_URL);
//        return sb.toString();
//    }

    private String getUrl(int type) {
        String url = null;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                url = ApiConstants.HOST_WEIXIN;
                break;
            case NewsFragment.NEWS_TYPE_RECREATION:
                url = ApiConstants.HOST_YULE;
                break;
            case NewsFragment.NEWS_TYPE_SCIENCE:
                url = ApiConstants.HOST_KEJI;
                break;
            case NewsFragment.NEWS_TYPE_HEALTH:
                url = ApiConstants.HOST_JIANKANG;
                break;
            default:
                break;
        }
        return url;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
