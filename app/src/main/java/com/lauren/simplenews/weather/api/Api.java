package com.lauren.simplenews.weather.api;

import com.lauren.simplenews.weather.model.WeatherBean;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-9.
 */
public interface Api {

    //回调结果
    public interface LoadWeatherListener {
        void onSuccess(List<WeatherBean> list);
        void onFailure(String msg, Exception e);
    }

    public interface LoadLocationListener {
        void onSuccess(String cityName);
        void onFailure(String msg, Exception e);
    }

    //定位
    void loadLocation(LoadLocationListener l);

    //获取天气
    void loadWeatherData(String cityName,LoadWeatherListener l);

}
