package com.kong.app;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kong.app.recomend.Banner;
import com.kong.app.recomend.BannerModel;
import com.kong.app.recomend.RecommendApi;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuming on 19/6/7.
 */
public class NSViewModel extends ViewModel {

    /**
     * 首页banner数据
     */
    private final MutableLiveData<List<Banner>> mBannerList = new MutableLiveData<>();

    private final RecommendApi mRecommendApi;

    public NSViewModel() {
        mRecommendApi = new RecommendApi();
    }

    public MutableLiveData<List<Banner>> getBannerList() {
        return mBannerList;
    }

    public void start() {
        mRecommendApi.getBannerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerModel bannerModel) {
                        mBannerList.setValue(bannerModel.mBanners);
                    }
                });
    }
}
