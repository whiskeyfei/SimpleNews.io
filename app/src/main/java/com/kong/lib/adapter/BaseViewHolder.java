package com.kong.lib.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CaoPengfei on 17/8/30.
 */

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
    }

    protected Context getContext() {
        return itemView.getContext();
    }

    public View getItemView() {
        return itemView;
    }

    public void setOnClickListener(@Nullable View.OnClickListener l) {
        itemView.setOnClickListener(l);
    }

    protected <T extends View> T findViewById(@IdRes int id) {
        return (T) itemView.findViewById(id);
    }

    /**
     * 可重写
     *
     * @param m
     */
    public void setData(M m) {

    }

    /**
     * 回收
     */
    public void onViewRecycled() {

    }
}
