package com.lauren.simplenews.api;

/**
 * Created by whiskeyfei on 16-1-9.
 */

public interface Callback<T> {
    void onResult(T result);
    void onError(Exception e);
}
