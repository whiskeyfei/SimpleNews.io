package com.kong.app.gank;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.kong.lib.utils.GsonUtils;
import com.kong.lib.utils.OkHttpUtils;
import com.kong.lib.utils.StringUtils;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by CaoPengfei on 17/8/2.
 */

class GankApi implements IGankApi {

    private static final String TAG = "GankApi";

    @Override
    public Observable<GankResult> getGankResult(int year, int month, int day) {
        final String url = String.format("http://gank.io/api/day/%d/%d/%d",year,month,day);
        Log.i(TAG, "getGankResult: " + url);
        return Observable.just(url).create(new Observable.OnSubscribe<GankResult>() {
            @Override
            public void call(final Subscriber<? super GankResult> subscriber) {
                OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        if(StringUtils.isEmpty(response)){
                            subscriber.onError(null);
                            return;
                        }
                        GankResult result = GsonUtils.deserialize(response,new TypeToken<GankResult>() {}.getType());
                        if(result == null){
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
