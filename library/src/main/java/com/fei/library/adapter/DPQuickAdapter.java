package com.fei.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.fei.library.inter.DPMultiItemTypeSupport;

import java.util.List;

/**
 * Created by whiskeyfei on 15-9-6.
 */
public abstract class DPQuickAdapter<T> extends DPBaseAdapter<T> {

    public DPQuickAdapter(Context context, List<T> list, int layoutId) {
        super(context, list, layoutId);
    }

    public DPQuickAdapter(Context context, List<T> list, DPMultiItemTypeSupport<T> multiItemSupport) {
        super(context, list, multiItemSupport);
    }

    @Override
    public DPViewHolder getDPViewHolder(int position, View convertView, ViewGroup parent) {
        if (mMultiItemSupport != null) {
            return DPViewHolder.get(mContext, convertView, parent, mMultiItemSupport.getLayoutId(position, mDataList.get(position)), position);
        }else{
            return DPViewHolder.get(mContext, convertView, parent, mLayoutId, position);
        }
    }
}
