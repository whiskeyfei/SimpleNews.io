package com.lauren.simplenews.presenter;

import com.lauren.simplenews.beans.NewModel;
import com.lauren.simplenews.commons.ApiConstants;
import com.lauren.simplenews.event.INewsPresenter;
import com.lauren.simplenews.fragment.NewsFragment;
import com.lauren.simplenews.event.INewsModel;
import com.lauren.simplenews.model.NewsModelImpl;
import com.lauren.simplenews.view.INewsView;
import com.orhanobut.logger.Logger;
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
        String url = getUrl(type);
        Logger.d("url###"+url);
        //只有第一页的或者刷新的时候才显示刷新进度条
        if(pageIndex == 0) {
            getMvpBaseView().showProgress();
        }
        load(url,type);
    }

    public void load(String url,int type) {
        mSubscription = mNewsModel.getNewlist(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewModel>>() {
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
                    public void onNext(List<NewModel> list) {
                        getMvpBaseView().addNews(list);
                    }
                });

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

    private String getUrl(int type){
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
}
