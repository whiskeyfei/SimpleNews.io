package com.lauren.simplenews.utils;

/**
 * Created by whiskeyfei on 16-2-25.
 */
public abstract class Singleton<T> {
    private T mInstance;

    protected abstract T create();

    public final T getInstance() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}