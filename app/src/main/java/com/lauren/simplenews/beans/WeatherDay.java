package com.lauren.simplenews.beans;

/**
 * Created by whiskeyfei on 16-1-15.
 */
public class WeatherDay {
    private String fengxiang;
    private String fengli;
    private String high;
    private String type;
    private String low;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public String getHigh() {
        return high;
    }

    public String getType() {
        return type;
    }

    public String getLow() {
        return low;
    }

    public String getDate() {
        return date;
    }
}
