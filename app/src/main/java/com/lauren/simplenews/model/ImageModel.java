package com.lauren.simplenews.model;

import android.util.Log;

import com.fei.library.utils.ListUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lauren.simplenews.beans.ImageBean;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.utils.OkHttpUtils;
import com.whiskeyfei.mvp.base.StringUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/22
 */
public class ImageModel implements IImageModel {

    private static final String TAG = "ImageModel";

    public Observable<List<ImageBean>> getImageList() {
        return Observable.just(Urls.IMAGES_URL).flatMap(new Func1<String, Observable<List<ImageBean>>>() {
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
        });
    }

}
