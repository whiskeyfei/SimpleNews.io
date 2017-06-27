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
import com.kong.app.news.beans.NewModel;
import com.library.AppRun;
import com.library.utils.ImageLoaderUtils;
import com.library.utils.StringUtils;

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
//        if (getPosition(holder) == getAdapter().getItemCount() -1){
//            holder.line.setVisibility(View.GONE);
//        }
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
        private View line;
        private TextView name;
        private TextView title;
        private TextView info;
        private ImageView icon;
        private Feed.PostsBean.ItemsBean mItemModel;

        public ViewHolder(View v) {
            super(v);
            line = v.findViewById(R.id.item_bolg_line);
            icon = (ImageView) v.findViewById(R.id.item_blog_icon);
            title = (TextView) v.findViewById(R.id.item_blog_title_id);
            name = (TextView) v.findViewById(R.id.item_blog_name_id);
            info = (TextView) v.findViewById(R.id.item_blog_info_id);
            v.setOnClickListener(this);
        }

        public void setData(Feed.PostsBean.ItemsBean itemModel) {
            mItemModel = itemModel;
            if (itemModel == null){
                return;
            }
            if (StringUtils.isEmpty(itemModel.getAuthor())){
                itemModel.setAuthor("Tom");
            }
            name.setText(itemModel.getAuthor() + "  " + itemModel.getDate_published().substring(0,10));
            title.setText(itemModel.getTitle());
            if (StringUtils.isEmpty(itemModel.getKeywords())){
                itemModel.setKeywords("Android");
            }
            info.setText("Keywords: " + itemModel.getKeywords());
            if (!StringUtils.isEmpty(itemModel.getCover())){
                icon.setVisibility(View.VISIBLE);
                ImageLoaderUtils.display(AppRun.get().getApplicationContext(), icon, itemModel.getCover(),R.drawable.ic_image_loading, R.drawable.ic_image_loadfail);
            }
        }

        @Override
        public void onClick(View v) {
            if (mItemModel == null){
                return;
            }
            NewModel model = new NewModel();
            model.newUrl = mItemModel.getUrl();
            model.title = mItemModel.getTitle();
            NewsEntry.get().startDetailActivity(v.getContext(),model);
//            NewsEntry.get().startBrowser(v.getContext(),mItemModel.getUrl());
        }

    }
}
