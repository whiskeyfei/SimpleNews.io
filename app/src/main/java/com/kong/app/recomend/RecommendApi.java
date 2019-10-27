package com.kong.app.recomend;

import com.google.gson.reflect.TypeToken;
import com.kong.lib.utils.GsonUtils;
import com.kong.lib.utils.OkHttpUtils;
import com.kong.lib.utils.StringUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by wuming on 17/8/2.
 */

public class RecommendApi {

    private static final String TAG = "RecommendApi";

    public Observable<BannerModel> getBannerList() {
        final String url = "https://www.wanandroid.com/banner/json";
        return Observable.just(url).create(new Observable.OnSubscribe<BannerModel>() {
            @Override
            public void call(final Subscriber<? super BannerModel> subscriber) {
                OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        if (StringUtils.isEmpty(response)) {
                            subscriber.onError(null);
                            return;
                        }
                        BannerModel result = GsonUtils.deserialize(response, new TypeToken<BannerModel>() {
                        }.getType());
                        if (result == null) {
                            subscriber.onError(null);
                            return;
                        }
                        subscriber.onNext(result);
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
}
