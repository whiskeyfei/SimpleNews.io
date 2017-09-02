package com.kong.app.gank;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.news.NewsEntry;
import com.kong.lib.adapter.BaseAdapter;
import com.kong.lib.adapter.BaseViewHolder;

/**
 * Created by CaoPengfei on 17/8/2.
 */

class GankAdapter extends BaseAdapter<Gank> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankViewHolder(parent,R.layout.item_gank);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolder((GankViewHolder)holder,position);
    }

    public void onBindViewHolder(GankViewHolder holder, int position) {
        Gank gank = getItem(position);
        if (position == 0) {
            showCategory(holder);
        } else {
            int before = position - 1;
            Gank gankNext = getItem(before);
            if (gankNext.type.equals(gank.type)) {
                hideCategory(holder);
            } else {
                showCategory(holder);
            }
        }
        holder.setData(gank);

    }

    private void showCategory(GankViewHolder holder) {
        if (!isVisibleOf(holder.mCategory))
            holder.mCategory.setVisibility(View.VISIBLE);
    }

    private void hideCategory(GankViewHolder holder) {
        if (isVisibleOf(holder.mCategory))
            holder.mCategory.setVisibility(View.GONE);
    }

    private boolean isVisibleOf(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if (holder instanceof  GankViewHolder){
            ((GankViewHolder) holder).onViewRecycled();
        }
    }

    public class GankViewHolder extends BaseViewHolder<Gank> implements View.OnClickListener {

        public TextView mCategory;
        public TextView mTitle;


        public GankViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            mCategory = findViewById(R.id.gank_category);
            mTitle = findViewById(R.id.gank_title);
            setOnClickListener(this);
        }

        @Override
        public void setData(Gank gank) {
            mCategory.setText(gank.type);
            mTitle.setText(gank.desc);
        }

        @Override
        public void onViewRecycled() {

        }

        @Override
        public void onClick(View v) {
            Gank gank = getItem(getLayoutPosition());
            if (gank != null) {
                NewsEntry.get().startBrowser(v.getContext(), gank.url, gank.desc);
            }
        }

    }

}
