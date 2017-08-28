package com.kong.app.gank;

import rx.Observable;

/**
 * Created by CaoPengfei on 17/8/27.
 */

class GankModel implements GankContract.Model {
    private final IGankApi mIGankApi = new GankApi();

    @Override
    public Observable<GankResult> getGankResult(int year, int month, int day) {
        return mIGankApi.getGankResult(year,month,day);
    }
}
