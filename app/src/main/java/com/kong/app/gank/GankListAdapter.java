package com.kong.app.gank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.news.NewsEntry;
import com.library.utils.ListUtils;

import java.util.List;

/**
 * Created by CaoPengfei on 17/8/2.
 */

public class GankListAdapter extends RecyclerView.Adapter<GankListAdapter.GankViewHolder> {

    private List<Gank> mGankList;

    public GankListAdapter(List<Gank> gankList) {
        mGankList = gankList;
    }

    public void setGankList(List<Gank> gankList) {
        mGankList = gankList;
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false);
        return new GankViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GankViewHolder holder, int position) {
        Gank gank = mGankList.get(position);
        if (position == 0) {
            showCategory(holder);
        } else {
            int before = position - 1;
            Gank gankNext = mGankList.get(before);
            if (gankNext.type.equals(gank.type)) {
                hideCategory(holder);
            } else {
                showCategory(holder);
            }
        }
        holder.mCategory.setText(gank.type);
        holder.mTitle.setText(gank.desc);
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
    public int getItemCount() {
        return ListUtils.getCount(mGankList);
    }

    public class GankViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mCategory;
        public TextView mTitle;

        public GankViewHolder(View itemView) {
            super(itemView);
            mCategory = (TextView) itemView.findViewById(R.id.gank_category);
            mTitle = (TextView) itemView.findViewById(R.id.gank_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Gank gank = mGankList.get(getLayoutPosition());
            if (gank != null) {
                NewsEntry.get().startBrowser(v.getContext(), gank.url, gank.desc);
            }
        }
    }
}
