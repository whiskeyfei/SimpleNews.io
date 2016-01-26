package com.fei.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fei.library.inter.DPOnItemClickListener;
import com.fei.library.inter.DPOnItemLongClickListener;

/**
 * RecyclerView viewHolder
 *
 * Created by whiskeyfei on 15-7-15.
 */
public class DPRecyclerViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener,View.OnLongClickListener{
    private RecyclerView mRecyclerView;
    private Context mContext;

    private DPOnItemLongClickListener mOnItemLongClickLitener;
    private DPOnItemClickListener mOnItemClickLitener;
    private DPViewHolder mDPViewHolder;

    public DPRecyclerViewHolder(View v, RecyclerView recyclerView,DPOnItemClickListener onItemChildClickListener,DPOnItemLongClickListener onItemChildLongClickListener){
        super(v);
        mRecyclerView = recyclerView;
        mContext = mRecyclerView.getContext();
        this.mOnItemClickLitener = onItemChildClickListener;
        this.mOnItemLongClickLitener = onItemChildLongClickListener;
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        mDPViewHolder = new DPViewHolder(mRecyclerView,this.itemView,this);
    }

//    public DPRecyclerViewHolder(View v, RecyclerView recyclerView, DPOnItemLongClickListener onItemLongClinckListener, DPOnItemClickListener onItemClickLitener) {
//        this(v,recyclerView);
//        mOnItemLongClickLitener = onItemLongClinckListener;
//        mOnItemClickLitener = onItemClickLitener;
//    }

    public DPViewHolder getDPViewHolder(){
        return mDPViewHolder;
    }


    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == this.itemView.getId() && null != mOnItemLongClickLitener) {
            return mOnItemLongClickLitener.onItemChildLongClick(mRecyclerView, v, getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.itemView.getId() && null != mOnItemClickLitener) {
            mOnItemClickLitener.onItemChildClick(v, getAdapterPosition());
        }
    }

}
