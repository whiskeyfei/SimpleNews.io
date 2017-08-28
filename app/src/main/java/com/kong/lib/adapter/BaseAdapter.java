package com.kong.lib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.kong.lib.utils.ListUtils;

import java.util.List;

/**
 * Created by CaoPengfei on 17/8/27.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;

    protected List<T> mLists;

    public BaseAdapter() {

    }

    public BaseAdapter(Context context) {
        mContext = context;
    }

    public BaseAdapter(Context context, List<T> lists) {
        this(context);
        mLists = lists;
    }

    @Override
    public int getItemCount() {
        return ListUtils.getCount(mLists);
    }

    public List<T> getLists() {
        return mLists;
    }

    public void setLists(List<T> lists) {
        mLists = lists;
    }

    public T getItem(int position) {
        return mLists != null ? mLists.get(position) : null;
    }

}
