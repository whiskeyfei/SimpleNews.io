package com.fei.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fei.library.inter.DPOnItemClickListener;
import com.fei.library.inter.DPOnItemLongClickListener;
import com.fei.library.inter.DPOnItemChildViewByIdClickListener;
import com.fei.library.inter.DPOnItemChildViewByIdLongClickListener;

import java.util.List;

/**
 * Base Adapter use RecyclerView (only)
 * Created by whiskeyfei on 15-7-14.
 */
public abstract  class DPBaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<DPRecyclerViewHolder> {
    private Context mContext;
    private List<T> mList;
    protected LayoutInflater mLayoutInflater;
    private RecyclerView mRecyclerView;
    private int mLayoutId;

    //Item
    private DPOnItemClickListener mOnItemClickLitener;
    private DPOnItemLongClickListener mOnItemLongClickListener;

    //Item childViewByid
    private DPOnItemChildViewByIdClickListener mDPOnItemChildViewByIdClickListener;
    private DPOnItemChildViewByIdLongClickListener mDPOnItemChildViewByIdLongClickListener;

    public void setOnItemClickLitener(DPOnItemClickListener l) {
        this.mOnItemClickLitener = l;
    }

    public void setOnItemClickLongLitener(DPOnItemLongClickListener l ){
        this.mOnItemLongClickListener = l;
    }

    public void setOnItemChildViewByIdLongClickListener(DPOnItemChildViewByIdLongClickListener l) {
        this.mDPOnItemChildViewByIdLongClickListener = l;
    }

    public void setOnItemChildViewByIdClickListenerr( DPOnItemChildViewByIdClickListener l) {
        this.mDPOnItemChildViewByIdClickListener = l;
    }

    public DPBaseRecyclerViewAdapter(RecyclerView recyclerView, List<T> list,int layoutId) {
        mRecyclerView = recyclerView;
        mContext = recyclerView.getContext();
        mList = list;
        mLayoutId = layoutId;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 创建DPRecyclerViewHolder
     * DPRecyclerViewHolder作为缓存的单位了
     * @param viewGroup
     * @param position
     * @return
     */
    @Override
    public DPRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = mLayoutInflater.inflate(mLayoutId,viewGroup,false);
        DPRecyclerViewHolder holder = new DPRecyclerViewHolder(view,mRecyclerView, mOnItemClickLitener, mOnItemLongClickListener);
        holder.getDPViewHolder().setOnItemChildViewByIdClickListener(this.mDPOnItemChildViewByIdClickListener);
        holder.getDPViewHolder().setOnItemChildViewByIdLongClickListener(this.mDPOnItemChildViewByIdLongClickListener);
        setListener(holder.getDPViewHolder());
        return holder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(DPRecyclerViewHolder holder, int position) {
        fillData(holder.getDPViewHolder(),position,getItem(position));
    }

    private T getItem(int position) {
        return mList.get(position);
    }

    protected abstract void fillData(DPViewHolder viewHolder, int position, T model);
    protected abstract void setListener(DPViewHolder viewHolder);
}





