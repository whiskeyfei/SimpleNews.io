package com.kong.app.gank;


import com.baselib.mvp.IModel;

import java.util.List;

import rx.Observable;

/**
 * Created by whiskeyfei on 16/11/6.
 */

interface GankContract {

    interface View {
        void showReslut(List<Gank> gankList);

        void showError();
    }

    interface Model extends IModel {
        Observable<GankResult> getGankResult(int year, int month, int day);
    }

    interface Presenter {
        void loadData(int year, int month, int day);
    }
}
