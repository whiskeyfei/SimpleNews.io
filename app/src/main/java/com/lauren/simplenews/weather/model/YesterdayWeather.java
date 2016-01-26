package com.lauren.simplenews.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by whiskeyfei on 16-1-15.
 */
public class YesterdayWeather {
    @SerializedName("fl")
    public String windLevel;

    @SerializedName("fx")
    public String windDirection;

    @SerializedName("high")
    public String max_tem;

    @SerializedName("type")
    public String type;

    @SerializedName("low")
    public String min_tem;

    @SerializedName("date")
    public String date;
}
