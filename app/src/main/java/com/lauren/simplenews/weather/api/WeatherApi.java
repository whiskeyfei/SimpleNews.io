package com.lauren.simplenews.weather.api;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.lauren.simplenews.app.AppApplication;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.utils.LogUtils;
import com.lauren.simplenews.utils.OkHttpUtils;
import com.lauren.simplenews.weather.WeatherJsonUtils;
import com.lauren.simplenews.weather.model.WeatherJsonModel;
import com.whiskeyfei.mvp.base.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by whiskeyfei on 16-1-9.
 */
public class WeatherApi implements Api{

    private static final String TAG = "WeatherApi";

    @Override
    public void loadLocation(final LoadLocationListener listener) {
        LocationManager locationManager = (LocationManager) AppApplication.get().getSystemService(Context.LOCATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (AppApplication.get().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && AppApplication.get().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                LogUtils.e(TAG, "location failure.");
                listener.onFailure("location failure.", null);
                return;
            }
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location == null) {
            LogUtils.e(TAG, "location failure.");
            listener.onFailure("location failure.", null);
            return;
        }
        double latitude = location.getLatitude();     //经度
        double longitude = location.getLongitude(); //纬度
        String url = getLocationURL(latitude, longitude);
        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                String city = WeatherJsonUtils.getCity(response);
                if(TextUtils.isEmpty(city)) {
                    LogUtils.e(TAG, "loadData location info failure.");
                    listener.onFailure("loadData location info failure.", null);
                } else {
                    listener.onSuccess(city);
                }
            }

            @Override
            public void onFailure(Exception e) {
                LogUtils.e(TAG, "loadData location info failure.", e);
                listener.onFailure("loadData location info failure.", e);
            }
        };
        OkHttpUtils.get(url, callback);
    }

    private String getLocationURL(double latitude, double longitude) {
        StringBuffer sb = new StringBuffer(Urls.INTERFACE_LOCATION);
        sb.append("?output=json").append("&referer=32D45CBEEC107315C553AD1131915D366EEF79B4");
        sb.append("&location=").append(latitude).append(",").append(longitude);
        LogUtils.d(TAG, sb.toString());
        return sb.toString();
    }

    @Override
    public void loadWeatherData(String cityName,final LoadWeatherListener listener) {
        try {
            String url = Urls.WEATHER + URLEncoder.encode(cityName, "utf-8");
            OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
                @Override
                public void onSuccess(String response) {
                    if (StringUtils.isEmpty(response)){
                        listener.onFailure("response is null", null);
                        return;
                    }
                    Log.e(TAG, "response:"+response);
                    Gson gson = new Gson();
                    WeatherJsonModel model = gson.fromJson(response, WeatherJsonModel.class);
                    Log.e(TAG, "model:"+model.toString());
                    if (model == null || model.data == null || model.data.weatherDayList == null){
                        listener.onFailure("model is null", null);
                        return;
                    }
//                    model.getData().getCity();
                    listener.onSuccess(WeatherJsonUtils.jsonToBeanList(model.data.weatherDayList));
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure("loadData weather data failure.", e);
                }
            };
            OkHttpUtils.get(url, callback);
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(TAG, "url encode error.", e);
        }
    }
}
