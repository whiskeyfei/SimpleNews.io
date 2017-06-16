package com.kong.app.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.news.beans.NoteModel;
import com.library.utils.ListUtils;

import java.util.List;

/**
 * Created by CaoPengfei on 17/6/9.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ItemViewHolder> {

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
    public DemoAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.demo_content_item, parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (holder == null) {
            return;
        }
        if (ListUtils.isEmpty(mNoteModels)) {
            return;
        }
        NoteModel model = mNoteModels.get(position);
        if (model != null) {
            holder.mTextView.setText(model.name);
        }
    }

    @Override
    public int getItemCount() {
        return ListUtils.getCount(mNoteModels);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;

        public ItemViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.demo_item_text);
            v.setOnClickListener(this);
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
