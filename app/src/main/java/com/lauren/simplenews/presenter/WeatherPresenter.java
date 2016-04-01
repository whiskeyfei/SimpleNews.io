//package com.lauren.simplenews.presenter;
//
//import com.lauren.simplenews.beans.WeatherBean;
//import com.lauren.simplenews.model.WeatherModelImpl;
//import com.lauren.simplenews.view.IWeatherView;
//import com.whiskeyfei.mvp.base.BasePresenter;
//
//import java.util.List;
//
//import rx.Observer;
//import rx.Subscription;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Action0;
//import rx.schedulers.Schedulers;
//
//public class WeatherPresenter extends BasePresenter<IWeatherView> implements IWeatherPresenter {
//
//    private WeatherModelImpl mWeatherModel;
//    private Subscription mSubscription;
//
//    public WeatherPresenter(IWeatherView weatherView) {
//        attachView(weatherView);
//        mWeatherModel = new WeatherModelImpl();
//    }
//
//    @Override
//    public void detachView() {
//        super.detachView();
//        if (mSubscription != null)
//            mSubscription.unsubscribe();
//    }
//
//    @Override
//    public void loadWeatherData() {
//        checkViewAttached();
//        mSubscription = mWeatherModel.getWeatherList("北京")
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//                        getMvpBaseView().showProgress();
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<WeatherBean>>() {
//                    @Override
//                    public void onCompleted() {
//                        getMvpBaseView().hideProgress();
//                        getMvpBaseView().showWeatherLayout();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        getMvpBaseView().showErrorToast("error");
//                    }
//
//                    @Override
//                    public void onNext(List<WeatherBean> list) {
//                        if (list != null && list.size() > 0) {
//                            WeatherBean todayWeather = list.remove(0);
//                            getMvpBaseView().showWeatherResult(todayWeather);
//                        }
//                        getMvpBaseView().setWeatherData(list);
//
//                    }
//                });
//    }
//
//}
