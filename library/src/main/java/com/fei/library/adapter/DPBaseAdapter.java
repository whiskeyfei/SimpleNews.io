package com.fei.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fei.library.inter.DPMultiItemTypeSupport;
import com.fei.library.inter.DPOnItemChildViewByIdClickListener;
import com.fei.library.inter.DPOnItemChildViewByIdLongClickListener;

import java.util.List;


/**
 * Abstraction class of a DPBaseAdapter in which you only need to provide the convert() implementation.<br/>
 *
 * @param <T> The type of the items in the list.
 */
public abstract class DPBaseAdapter<T> extends BaseAdapter {
	
    protected List<T> mDataList;
    protected Context mContext;
    protected int mLayoutId;
    protected DPMultiItemTypeSupport<T> mMultiItemSupport;

    private DPOnItemChildViewByIdClickListener mDPOnItemChildViewByIdClickListener;
    private DPOnItemChildViewByIdLongClickListener mDPOnItemChildViewByIdLongClickListener;
    /**
     * Create DPBaseAdapter of layoutId
     * @param context The context.
     * @param list needed data
     * @param layoutId The layout resource id of each item.
     */
    public DPBaseAdapter(Context context, List<T> list, int layoutId) {
        this.mContext = context;
        this.mDataList = list;
        this.mLayoutId = layoutId;
    }

    /**
     * Create DPBaseAdapter of Support multiItems.
     * @param context The context.
     * @param list  needed data.
     * @param multiItemSupport The multiItemSupport of all item type
     */
    public DPBaseAdapter(Context context, List<T> list,DPMultiItemTypeSupport<T> multiItemSupport){
        this.mContext = context;
        this.mDataList = list;
        this.mMultiItemSupport = multiItemSupport;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public int getViewTypeCount() {
        if (mMultiItemSupport != null)
            return mMultiItemSupport.getViewTypeCount() + 1;
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return (mMultiItemSupport != null) ?
                mMultiItemSupport.getItemViewType(position, mDataList.get(position)) : 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DPViewHolder holder = getDPViewHolder(position, convertView,parent);
        holder.setOnItemChildViewByIdClickListener(mDPOnItemChildViewByIdClickListener);
        holder.setOnItemChildViewByIdLongClickListener(mDPOnItemChildViewByIdLongClickListener);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public void setOnItemChildViewByIdClickListener(DPOnItemChildViewByIdLongClickListener l) {
        this.mDPOnItemChildViewByIdLongClickListener = l;
    }
	
	public void setOnItemChildViewByIdLongClickListener(DPOnItemChildViewByIdClickListener l){
		this.mDPOnItemChildViewByIdClickListener = l;
	}
	
    public void removeItem(int position) {
        this.mDataList.remove(position);
        this.notifyDataSetChanged();
    }
    
    private void addItem(int position, T model) {
        this.mDataList.add(position, model);
        this.notifyDataSetChanged();
    }
    
    public void addFirstItem(T model) {
        this.addItem(0, model);
    }
    
    public void addLastItem(T model){
    	this.addItem(this.mDataList.size(), model);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     * @param holder A fully initialized helper.
     * @param item The item that needs to be displayed.
     */
	public abstract void convert(DPViewHolder holder,T item);

    /**
     *
     * @param position  The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView
     * @param parent The parent that this view will eventually be attached to.
     * @return A DPViewHolder
     */
    public abstract DPViewHolder getDPViewHolder(int position, View convertView,ViewGroup parent);
}