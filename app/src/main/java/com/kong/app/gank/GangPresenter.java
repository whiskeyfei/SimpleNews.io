package com.kong.app.gank;

import com.kong.lib.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CaoPengfei on 17/8/27.
 */

class GangPresenter implements GankContract.Presenter{

    private List<Gank> mGankList = new ArrayList<>();
    protected GankModel mGankModel;
    protected GankContract.View mView;
    private int time = 0;
    private int mYear, mMonth, mDay;

    public GangPresenter(GankModel gankModel, GankContract.View view) {
        mGankModel = gankModel;
        mView = view;
        time = 0;
    }

    @Override
    public void loadData(int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
        mGankModel.getGankResult(year, month, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        reLoad();
                    }

                    @Override
                    public void onNext(GankResult gankResult) {
                        getGankList(gankResult);
                        if (ListUtils.isEmpty(mGankList)){
                            reLoad();
                        }else{
                            time = 100;
                            mView.showReslut(mGankList);
                        }
                    }
                });
    }

    public void reLoad(){
        time++;
        mDay--;
        if (time < 2){
            loadData(mYear, mMonth, mDay);
        }else{
            mView.showError();
        }
    }

    private List<Gank> getGankList(GankResult gankResult){
        if (gankResult.results.妹纸List != null){
            mGankList.addAll(gankResult.results.妹纸List);
        }
        if (gankResult.results.androidList != null){
            mGankList.addAll(gankResult.results.androidList);
        }
        if (gankResult.results.appList != null){
            mGankList.addAll(gankResult.results.appList);
        }
        if (gankResult.results.iOSList != null){
            mGankList.addAll(gankResult.results.iOSList);
        }
        if (gankResult.results.瞎推荐List != null){
            mGankList.addAll(gankResult.results.瞎推荐List);
        }
        if (gankResult.results.web != null){
            mGankList.addAll(gankResult.results.web);
        }
        if (gankResult.results.拓展资源List != null){
            mGankList.addAll(gankResult.results.拓展资源List);
        }
        return mGankList;
    }
}
