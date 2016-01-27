package com.lauren.simplenews.model;

import android.util.Log;

import com.fei.library.utils.StringUtils;
import com.lauren.simplenews.beans.WeatherBean;
import com.lauren.simplenews.beans.WeatherJsonModel;
import com.lauren.simplenews.beans.WeatherModel;
import com.lauren.simplenews.commons.Urls;
import com.lauren.simplenews.utils.GsonJsonUtils;
import com.lauren.simplenews.utils.LogUtils;
import com.lauren.simplenews.utils.OkHttpUtils;
import com.lauren.simplenews.utils.ToolsUtil;
import com.lauren.simplenews.utils.WeatherJsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/22
 */
public class WeatherModelImpl implements WeatherModel {

    private static final String TAG = "WeatherModelImpl";
//    WeatherApi api = new WeatherApi();

//    public AsyncJob<List<WeatherBean>> getWeatherListJob(final String city){
//
//        AsyncJob<List<WeatherBean>> weatherList = new AsyncJob<List<WeatherBean>>() {
//            @Override
//            public void start(final Callback<List<WeatherBean>> callback) {
//                api.loadLocation(new Api.LoadLocationListener() {
//                    @Override
//                    public void onSuccess(String cityName) {
//                        new Load(cityName, callback).invoke();
//                    }
//
//                    @Override
//                    public void onFailure(String msg, Exception e) {
//                        new Load(city, callback).invoke();
//                    }
//                });
//            }
//        };
//        return weatherList;
//    }

    public Observable<List<WeatherBean>> getWeatherList(String cityName) {
        return Observable.just(cityName).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String cityName) {
                return !StringUtils.isEmpty(cityName);
            }
        }).map(new Func1<String, String>() {

            @Override
            public String call(String cityName) {
                try {
                    return Urls.WEATHER + URLEncoder.encode(cityName, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    return "";
                }
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return !StringUtils.isEmpty(s);
            }
        }).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return ToolsUtil.isNetworkAvailable();
            }
        }).flatMap(new Func1<String, Observable<List<WeatherBean>>>() {
            @Override
            public Observable<List<WeatherBean>> call(final String url) {
                return Observable.create(new Observable.OnSubscribe<List<WeatherBean>>() {
                    @Override
                    public void call(final Subscriber<? super List<WeatherBean>> subscriber) {
                        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
                            @Override
                            public void onSuccess(String response) {
                                WeatherJsonModel model = GsonJsonUtils.deserialize(response, WeatherJsonModel.class);
                                Log.e(TAG, "model:"+model.toString());
                                if (model == null || model.data == null || model.data.weatherDayList == null){
                                    subscriber.onError(null);
                                    return;
                                }
                                subscriber.onNext(WeatherJsonUtils.jsonToBeanList(model.data.weatherDayList));
                            }

                            @Override
                            public void onFailure(Exception e) {
                                subscriber.onError(e);
                            }
                        };
                        OkHttpUtils.get(url, callback);
                    }
                });
            }
        });
    }

//    @Override
//    public void loadWeatherData(String cityName, final Api.LoadWeatherListener listener) {
//        try {
//            String url = Urls.WEATHER + URLEncoder.encode(cityName, "utf-8");
//            OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
//                @Override
//                public void onSuccess(String response) {
//                    List<WeatherBean> lists = WeatherJsonUtils.getWeatherInfo(response);
//                    listener.onSuccess(lists);
//                }
//
//                @Override
//                public void onFailure(Exception e) {
//                    listener.onFailure("loadData weather data failure.", e);
//                }
//            };
//            OkHttpUtils.get(url, callback);
//        } catch (UnsupportedEncodingException e) {
//            LogUtils.e(TAG, "url encode error.", e);
//        }
//    }

//    @Override
//    public void loadLocation(Context context, final Api.LoadLocationListener listener) {
//        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                LogUtils.e(TAG, "location failure.");
//                listener.onFailure("location failure.", null);
//                return;
//            }
//        }
//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        if(location == null) {
//            LogUtils.e(TAG, "location failure.");
//            listener.onFailure("location failure.", null);
//            return;
//        }
//        double latitude = location.getLatitude();     //经度
//        double longitude = location.getLongitude(); //纬度
//        String url = getLocationURL(latitude, longitude);
//        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
//            @Override
//            public void onSuccess(String response) {
//                String city = WeatherJsonUtils.getCity(response);
//                if(TextUtils.isEmpty(city)) {
//                    LogUtils.e(TAG, "loadData location info failure.");
//                    listener.onFailure("loadData location info failure.", null);
//                } else {
//                    listener.onSuccess(city);
//                }
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                LogUtils.e(TAG, "loadData location info failure.", e);
//                listener.onFailure("loadData location info failure.", e);
//            }
//        };
//        OkHttpUtils.get(url, callback);
//    }

    private String getLocationURL(double latitude, double longitude) {
        StringBuffer sb = new StringBuffer(Urls.INTERFACE_LOCATION);
        sb.append("?output=json").append("&referer=32D45CBEEC107315C553AD1131915D366EEF79B4");
        sb.append("&location=").append(latitude).append(",").append(longitude);
        LogUtils.d(TAG, sb.toString());
        return sb.toString();
    }

//
//    private class Load {
//        private final Callback<List<WeatherBean>> callback;
//        private String city;
//
//        public Load(String city, Callback<List<WeatherBean>> callback) {
//            this.city = city;
//            this.callback = callback;
//        }
//
//        public void invoke() {
//            api.loadWeatherData(city,new Api.LoadWeatherListener(){
//                @Override
//                public void onSuccess(List<WeatherBean> list) {
//                    callback.onResult(list);
//                }
//
//                @Override
//                public void onFailure(String msg, Exception e) {
//                    callback.onError(e);
//                }
//            });
//        }
//    }
}
