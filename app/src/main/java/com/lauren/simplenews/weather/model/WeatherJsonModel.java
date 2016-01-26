package com.lauren.simplenews.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by whiskeyfei on 16-1-9.
 */
public class WeatherJsonModel {

    @SerializedName("desc")
    public String weatherDesc;

    @SerializedName("status")
    public int weatherStatus;

    @SerializedName("data")
    public DataInfo data;

    @Override
    public String toString() {
        return "WeatherJsonModel{" +
                "weatherDesc='" + weatherDesc + '\'' +
                ", weatherStatus=" + weatherStatus +
                ", data=" + data +
                '}';
    }
}
