package com.lauren.simplenews.weather.api;

/**
 * Created by whiskeyfei on 16-1-9.
 */
public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback);
}
