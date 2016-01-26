package com.lauren.simplenews.news.presenter;

import com.lauren.simplenews.beans.NewsBean;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.news.model.INewsModel;
import com.lauren.simplenews.news.model.NewsModelImpl;
import com.lauren.simplenews.news.view.INewsView;
import com.lauren.simplenews.news.widget.NewsFragment;
import com.whiskeyfei.mvp.base.BasePresenter;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsPresenter extends BasePresenter<INewsView> implements INewsPresenter {

    private static final String TAG = "NewsPresenter";

    private INewsModel mNewsModel;
    private Subscription mSubscription;

    public NewsPresenter(INewsView newsView) {
        attachView(newsView);
        this.mNewsModel = new NewsModelImpl();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void loadNews(final int type, final int pageIndex) {
        checkViewAttached();
        String url = getUrl(type, pageIndex);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 0) {
            getMvpBaseView().showProgress();
        }
        load(url,type);
    }

    public void load(String url,int type) {
        mSubscription = mNewsModel.getNewList(url, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsBean>>() {
                    @Override
                    public void onCompleted() {
                        getMvpBaseView().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpBaseView().hideProgress();
                        getMvpBaseView().showLoadFailMsg();
                    }

                    @Override
                    public void onNext(List<NewsBean> list) {
                        getMvpBaseView().addNews(list);
                    }
                });

    }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @param pageIndex
     * @return
     */
    private String getUrl(int type, int pageIndex) {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(pageIndex).append(Urls.END_URL);
        return sb.toString();
    }
}
