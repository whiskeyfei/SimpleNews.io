//package com.lauren.simplenews.model;
//
//import android.util.Log;
//
//import com.fei.library.utils.StringUtils;
//import com.lauren.simplenews.beans.WeatherBean;
//import com.lauren.simplenews.beans.WeatherJsonModel;
//import com.lauren.simplenews.beans.WeatherModel;
//import com.lauren.simplenews.commons.Urls;
//import com.lauren.simplenews.utils.GsonJsonUtils;
//import com.lauren.simplenews.utils.OkHttpUtils;
//import com.lauren.simplenews.utils.ToolsUtil;
//import com.lauren.simplenews.utils.WeatherJsonUtils;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.List;
//
//import rx.Observable;
//import rx.Subscriber;
//import rx.functions.Func1;
//
//public class WeatherModelImpl implements WeatherModel {
//
//    private static final String TAG = "WeatherModelImpl";
//
//    public Observable<List<WeatherBean>> getWeatherList(String cityName) {
//        return Observable.just(cityName).filter(new Func1<String, Boolean>() {
//            @Override
//            public Boolean call(String cityName) {
//                return !StringUtils.isEmpty(cityName);
//            }
//        }).map(new Func1<String, String>() {
//
//            @Override
//            public String call(String cityName) {
//                try {
//                    return Urls.WEATHER + URLEncoder.encode(cityName, "utf-8");
//                } catch (UnsupportedEncodingException e) {
//                    return "";
//                }
//            }
//        }).filter(new Func1<String, Boolean>() {
//            @Override
//            public Boolean call(String s) {
//                return !StringUtils.isEmpty(s);
//            }
//        }).filter(new Func1<String, Boolean>() {
//            @Override
//            public Boolean call(String s) {
//                return ToolsUtil.isNetworkAvailable();
//            }
//        }).flatMap(new Func1<String, Observable<List<WeatherBean>>>() {
//            @Override
//            public Observable<List<WeatherBean>> call(final String url) {
//                return Observable.create(new Observable.OnSubscribe<List<WeatherBean>>() {
//                    @Override
//                    public void call(final Subscriber<? super List<WeatherBean>> subscriber) {
//                        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
//                            @Override
//                            public void onSuccess(String response) {
//                                WeatherJsonModel model = GsonJsonUtils.deserialize(response, WeatherJsonModel.class);
//                                Log.e(TAG, "model:"+model.toString());
//                                if (model == null || model.data == null || model.data.weatherDayList == null){
//                                    subscriber.onError(null);
//                                    return;
//                                }
//                                subscriber.onNext(WeatherJsonUtils.jsonToBeanList(model.data.weatherDayList));
//                            }
//
//                            @Override
//                            public void onFailure(Exception e) {
//                                subscriber.onError(e);
//                            }
//                        };
//                        OkHttpUtils.get(url, callback);
//                    }
//                });
//            }
//        });
//    }
//}
