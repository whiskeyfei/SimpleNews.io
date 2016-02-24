package com.lauren.simplenews.model;

import com.fei.library.utils.ListUtils;
import com.google.gson.Gson;
import com.lauren.simplenews.beans.NewModel;
import com.lauren.simplenews.beans.NewResultModel;
import com.lauren.simplenews.event.INewsModel;
import com.lauren.simplenews.utils.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.whiskeyfei.mvp.base.StringUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class NewsModelImpl implements INewsModel {
    private static final String TAG = "NewsModelImpl";

    public Observable<List<NewModel>> getNewlist(final String url){
            return  Observable.create(new Observable.OnSubscribe<List<NewModel>>() {
                @Override
                public void call(final Subscriber<? super List<NewModel>> subscriber) {
                    OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {

                        @Override
                        public void onSuccess(String response) {
                            if (StringUtils.isEmpty(response)){
                                Logger.e("response is null");
                                subscriber.onError(null);
                                return;
                            }
                            Logger.json(response);
                            NewResultModel model = new Gson().fromJson(response,NewResultModel.class);
                            if(model == null || ListUtils.isEmpty(model.newModellist)){
                                Logger.e("model is null");
                                subscriber.onError(null);
                                return;
                            }
                        Logger.d(TAG + "getNewList() -> model:" + model);
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
            });
    }

}
