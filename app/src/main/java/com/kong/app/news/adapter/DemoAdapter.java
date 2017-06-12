package com.kong.app.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.app.news.beans.NoteModel;
import com.library.utils.ListUtils;

import java.util.List;

/**
 * Created by CaoPengfei on 17/6/9.
 */

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<NoteModel> mNoteModels;

    public DemoAdapter(Context context) {
        this.mContext = context;
    }

    public void setDate(List<NoteModel> data) {
        this.mNoteModels = data;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(new TextView(mContext));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            if (ListUtils.isEmpty(mNoteModels)) {
                return;
            }
            TextView textView = (TextView) holder.itemView;
            NoteModel model = mNoteModels.get(position);
            if (model != null) {
                textView.setText("demo:" + model.name);
            }
        }
    }

    @Override
    public int getItemCount() {
        return ListUtils.getCount(mNoteModels);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, this.getAdapterPosition());
            }
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
