package com.kong.app.blog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.blog.model.Feed;
import com.kong.app.news.NewsEntry;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by CaoPengfei on 17/6/18.
 */

public class BlogItemViewBinder extends ItemViewBinder<Feed.PostsBean.ItemsBean,BlogItemViewBinder.ViewHolder> {

    private static final String TAG = "BlogItemViewBinder";

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_blog, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Feed.PostsBean.ItemsBean item) {
        holder.setData(item);
    }

    @Override
    protected void onViewRecycled(@NonNull ViewHolder holder) {
        Log.i(TAG,"onViewRecycled"+holder.itemView);
    }

    @Override
    protected boolean onFailedToRecycleView(@NonNull ViewHolder holder) {
        Log.i(TAG,"onFailedToRecycleView"+holder.itemView);
        return true;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView info;
        private ImageView icon;
        private Feed.PostsBean.ItemsBean mItemModel;

        public ViewHolder(View v) {
            super(v);
            icon = (ImageView) v.findViewById(R.id.item_blog_icon);
            title = (TextView) v.findViewById(R.id.item_blog_title_id);
            info = (TextView) v.findViewById(R.id.item_blog_info_id);
            v.setOnClickListener(this);
        }

        public void setData(Feed.PostsBean.ItemsBean itemModel) {
            mItemModel = itemModel;
            if (mItemModel == null){
                return;
            }
            title.setText(itemModel.getTitle());
            info.setText(itemModel.getKeywords() + " " +itemModel.getAuthor());
        }

        @Override
        public void onClick(View v) {
            if (mItemModel == null){
                return;
            }
            NewsEntry.get().startBrowser(v.getContext(),mItemModel.getUrl());
        }

    }
}
