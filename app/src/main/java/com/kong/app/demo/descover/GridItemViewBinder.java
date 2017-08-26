package com.kong.app.demo.descover;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.badminton.Players;
import com.kong.lib.AppRun;
import com.kong.lib.utils.ImageLoaderUtils;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by CaoPengfei on 17/6/18.
 */

public class GridItemViewBinder extends ItemViewBinder<Players.PlayerBean, GridItemViewBinder.ViewHolder> {

    private static final String TAG = "GridItemViewBinder";

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_descover, parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Players.PlayerBean item) {
        holder.setData(item);
    }

    @Override
    protected void onViewRecycled(@NonNull ViewHolder holder) {
        holder.icon.setImageResource(R.drawable.ic_image_loading);
    }

    @Override
    protected boolean onFailedToRecycleView(@NonNull ViewHolder holder) {
        Log.i(TAG, "onFailedToRecycleView" + holder.itemView);
        return true;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView icon;
        public TextView title;
        private Players.PlayerBean mData;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.descover_icon);
            title = (TextView) itemView.findViewById(R.id.descover_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }

        public void setData(Players.PlayerBean data) {
            mData = data;
            title.setText(data.getName());
            ImageLoaderUtils.display(AppRun.get().getApplicationContext(), icon, mData.getImg(),R.drawable.ic_image_loading, R.drawable.ic_image_loadfail);
        }
    }
}
