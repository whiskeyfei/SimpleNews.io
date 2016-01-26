//package com.lauren.simplenews.weather.api;
//
//import com.lauren.simplenews.weather.model.WeatherBean;
//
//import java.util.List;
//
///**
// * Created by whiskeyfei on 16-1-9.
// */
//public class WeatherHelper {
//    WeatherApi api = new WeatherApi();
//
//    public AsyncJob<List<WeatherBean>> getWeatherList(final String city){
//
//        AsyncJob<List<WeatherBean>> weatherList = new AsyncJob<List<WeatherBean>>() {
//            @Override
//            public void start(final Callback<List<WeatherBean>> callback) {
//                api.loadWeatherData(city,new Api.LoadWeatherListener(){
//                    @Override
//                    public void onSuccess(List<WeatherBean> list) {
//                        callback.onResult(list);
//                    }
//
//                    @Override
//                    public void onFailure(String msg, Exception e) {
//                        callback.onError(e);
//                    }
//                });
//            }
//        };
//        return weatherList;
//    }
//}
