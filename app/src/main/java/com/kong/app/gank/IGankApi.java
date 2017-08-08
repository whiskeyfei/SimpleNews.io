package com.kong.app.gank;

import rx.Observable;

/**
 * Created by CaoPengfei on 17/8/2.
 */

public interface IGankApi {
    Observable<GankResult> getGankResult(int year, int month, int day);
}
