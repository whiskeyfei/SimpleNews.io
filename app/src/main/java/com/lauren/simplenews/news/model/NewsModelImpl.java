package com.lauren.simplenews.news.model;

import com.fei.library.utils.ListUtils;
import com.lauren.simplenews.beans.NewsBean;
import com.lauren.simplenews.beans.NewsDetailBean;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.news.NewsJsonUtils;
import com.lauren.simplenews.news.widget.NewsFragment;
import com.lauren.simplenews.utils.OkHttpUtils;
import com.orhanobut.logger.Logger;
import com.whiskeyfei.mvp.base.StringUtils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class NewsModelImpl implements INewsModel {
    private static final String TAG = "NewsModelImpl";

    public Observable<List<NewsBean>> getNewList(final String url, final int type) {
        return Observable.create(new Observable.OnSubscribe<List<NewsBean>>() {

            @Override
            public void call(final Subscriber<? super List<NewsBean>> subscriber) {
                OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        if (StringUtils.isEmpty(response)){
                            Logger.e("response is null");
                            subscriber.onError(null);
                            return;
                        }
                        Logger.json(response);
                        List<NewsBean> newsBeanList = NewsJsonUtils.readJsonNewsBeans(response, getID(type));
                        if(ListUtils.isEmpty(newsBeanList)){
                            Logger.e("newsBeanList is null");
                            subscriber.onError(null);
                            return;
                        }
//                        Logger.d(TAG + "getNewList() -> newsBeanList:" + newsBeanList);
                        subscriber.onNext(newsBeanList);
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

    @Override
    public Observable<NewsDetailBean> getDetailNew(final String docid) {
        return Observable.create(new Observable.OnSubscribe<NewsDetailBean>(){

            @Override
            public void call(final Subscriber<? super NewsDetailBean> subscriber) {
                String url = getDetailUrl(docid);
                OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
                    @Override
                    public void onSuccess(String response) {
                        if(StringUtils.isEmpty(response)){
                            Logger.e("response is null");
                            subscriber.onError(null);
                            return;
                        }

                        NewsDetailBean newsDetailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
                        if(newsDetailBean == null){
                            subscriber.onError(null);
                            Logger.e("newsDetailBean is null");
                            return;
                        }
//                        Logger.d(TAG + "getDetailNew() -> newsDetailBean:" + newsDetailBean);
                        subscriber.onNext(newsDetailBean);
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

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type) {
        String id;
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Urls.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }

    private String getDetailUrl(String docId) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }

}
