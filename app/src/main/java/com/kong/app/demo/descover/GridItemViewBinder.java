package com.kong.app.demo.descover;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.R;
import com.library.utils.ToolsUtil;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by CaoPengfei on 17/6/18.
 */

public class GridItemViewBinder extends ItemViewBinder<GridItem, GridItemViewBinder.ViewHolder> {

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_descover, parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GridItem item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView icon;
        public TextView title;
        private GridItem mData;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.descover_icon);
            title = (TextView) itemView.findViewById(R.id.descover_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ToolsUtil.showToast("GridView position:"+getAdapterPosition());
        }

        public void setData(GridItem data) {
            mData = data;
            title.setText(data.title);
        }
    }
}
