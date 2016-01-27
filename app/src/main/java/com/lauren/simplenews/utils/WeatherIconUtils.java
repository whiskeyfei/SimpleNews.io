package com.lauren.simplenews.utils;

import com.lauren.simplenews.R;
import com.whiskeyfei.mvp.base.StringUtils;

import java.util.HashMap;

/**
 * Created by whiskeyfei on 15-12-29.
 */
public class WeatherIconUtils {
    private static final int WEATHER_NO_ICON = -1;
    private static final int INDEXOF_SET = 0;
    private static final String WEATHER_FLAG_ZHUAN = "转";
    private static final String WEATHER_FLAG_DAO = "到";

    static HashMap<String, Integer> mWeatherDayMap = new HashMap<String, Integer>() {{
        put("多云", R.drawable.biz_plugin_weather_duoyun);
        put("中雨", R.drawable.biz_plugin_weather_zhongyu);
        put("雷阵雨", R.drawable.biz_plugin_weather_leizhenyu);
        put("阵雨", R.drawable.biz_plugin_weather_zhenyu);
        put("暴雪", R.drawable.biz_plugin_weather_baoxue);
        put("暴雨", R.drawable.biz_plugin_weather_baoyu);
        put("大暴雨", R.drawable.biz_plugin_weather_dabaoyu);
        put("大雪", R.drawable.biz_plugin_weather_daxue);
        put("大雨", R.drawable.biz_plugin_weather_dayu);
        put("雷阵雨冰雹", R.drawable.biz_plugin_weather_leizhenyubingbao);
        put("晴", R.drawable.biz_plugin_weather_qing);
        put("沙尘暴", R.drawable.biz_plugin_weather_shachenbao);
        put("特大暴雨", R.drawable.biz_plugin_weather_tedabaoyu);
        put("雾", R.drawable.biz_plugin_weather_wu);
        put("小雪", R.drawable.biz_plugin_weather_xiaoxue);
        put("小雨", R.drawable.biz_plugin_weather_xiaoyu);
        put("阴", R.drawable.biz_plugin_weather_yin);
        put("雨夹雪", R.drawable.biz_plugin_weather_yujiaxue);
        put("阵雪", R.drawable.biz_plugin_weather_zhenxue);
        put("中雪", R.drawable.biz_plugin_weather_zhongxue);
    }};


    public static int getWeatherImage(String weather) {
        if (StringUtils.isEmpty(weather)) {
            return WEATHER_NO_ICON;
        }
        Integer weatherIcon = getWeatherIcon(weather);
        if (weatherIcon != null) {
            return weatherIcon;
        }

        int index_z = weather.indexOf(WEATHER_FLAG_ZHUAN);
        if (index_z > INDEXOF_SET) {
            weather = weather.substring(0, index_z);
            weather = getWeatherIndexOf(weather);
        } else {
            weather = getWeatherIndexOf(weather);
        }
        weatherIcon = getWeatherIcon(weather);
        return weatherIcon != null ? weatherIcon : WEATHER_NO_ICON;
    }

    private static Integer getWeatherIcon(String weather) {
        return mWeatherDayMap.get(weather);
    }

    private static String getWeatherIndexOf(String weather) {
        int index = weather.indexOf(WEATHER_FLAG_DAO);
        if (index > INDEXOF_SET) {
            weather = weather.substring(index + 1, weather.length());
        }
        return weather;
    }
}
