package com.kong.app.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.news.beans.NewModel;
import com.kong.app.news.utils.TimeUtils;
import com.kong.lib.adapter.BaseAdapter;
import com.kong.lib.utils.ImageLoaderUtils;

public class NewsAdapter extends BaseAdapter<NewModel> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private boolean mShowFooter = true;

    private OnItemClickListener mOnItemClickListener;

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (!mShowFooter) {
            return TYPE_ITEM;
        }
        return (position + 1 == getItemCount()) ? TYPE_FOOTER : TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, null);
            return new ItemViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_loading_view, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            NewModel news = getItem(position);
            if (news == null) {
                return;
            }
            ((ItemViewHolder) holder).mTitle.setText(news.title);
            ((ItemViewHolder) holder).mDesc.setText(new StringBuilder(TimeUtils.getGapTime(news.time)).append(" ").append(news.digest).toString());
            ImageLoaderUtils.display(mContext, ((ItemViewHolder) holder).mImageView, news.imageUrl, R.drawable.ic_image_loading, R.drawable.ic_image_loadfail);
        }
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof ItemViewHolder) {
            ImageLoaderUtils.setDefaultImage(mContext, ((ItemViewHolder) holder).mImageView, R.drawable.ic_image_loadfail);
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter ? 1 : 0;
        if (getLists() == null) {
            return begin;
        }
        return super.getItemCount() + begin;
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mImageView;
        public TextView mTitle, mDesc;

        public ItemViewHolder(View v) {
            super(v);
            mImageView = (ImageView) v.findViewById(R.id.ivNews);
            mTitle = (TextView) v.findViewById(R.id.tvTitle);
            mDesc = (TextView) v.findViewById(R.id.tvDesc);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getAdapterPosition());
            }
        }
    }

}
