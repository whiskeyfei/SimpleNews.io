package com.kong.home.tab.adapter;

import android.content.Context;

import com.kong.lib.utils.ListUtils;

import java.util.List;

/**
 * Created by CaoPengfei on 16/4/13.
 */
public abstract class BaseTabAdapter<T> extends TabAdpater<T> {

    private Context mContext;
    protected List<T> mLists;

    public BaseTabAdapter(Context context) {
        mContext = context;
    }

    public BaseTabAdapter(Context context, List<T> objects) {
        this(context);
        mLists = objects;
    }

    public void setData(List<T> list) {
        mLists = list;
    }

    @Override
    public T getItem(int position) {
        return (mLists != null) ? mLists.get(position) : null;
    }

    public int getCount() {
        return ListUtils.getCount(mLists);
    }

    public Context getContext() {
        return mContext;
    }
}
