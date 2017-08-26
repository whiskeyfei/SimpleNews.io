package com.kong.app.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.news.beans.ItemModel;
import com.kong.lib.utils.ActivityUtils;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by CaoPengfei on 17/6/18.
 */

public class DemoItemViewBinder extends ItemViewBinder<ItemModel,DemoItemViewBinder.ViewHolder> {

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.demo_content_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemModel item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public TextView mTextView;
        public ItemModel mItemModel;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.demo_item_text);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setData(ItemModel itemModel) {
            mItemModel = itemModel;
            mTextView.setText(mItemModel.name);
        }

        @Override
        public void onClick(View v) {
            if (mItemModel == null){
                return;
            }
            Intent intent = new Intent();
            intent.setClass(v.getContext(),mItemModel.cls);
            ActivityUtils.startActivity(v.getContext(),intent);
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
