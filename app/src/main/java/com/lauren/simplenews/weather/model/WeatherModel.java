package com.lauren.simplenews.weather.model;

import android.content.Context;

import com.lauren.simplenews.weather.api.Api;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/22
 */
public interface WeatherModel {

    void loadWeatherData(String cityName, Api.LoadWeatherListener listener);

    void loadLocation(Context context, Api.LoadLocationListener listener);



}
