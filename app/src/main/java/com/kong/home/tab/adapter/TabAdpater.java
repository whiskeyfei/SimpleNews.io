package com.kong.home.tab.adapter;

import android.view.View;

/**
 * Created by whiskeyfei on 16/4/13.
 */
public abstract class TabAdpater<T> {

    public abstract View getView(int position);

    public abstract int getCount();

    public abstract T getItem(int position);

}
