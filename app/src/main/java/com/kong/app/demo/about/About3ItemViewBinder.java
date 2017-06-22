package com.kong.app.demo.about;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by CaoPengfei on 17/6/18.
 */

public class About3ItemViewBinder extends ItemViewBinder<ContentModel,About3ItemViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.about_content_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ContentModel item) {
        holder.setModel(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public TextView title;
        public TextView desc;
        public ContentModel mModel;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.item_text);
            desc = (TextView) v.findViewById(R.id.item_desc);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setModel(ContentModel model) {
            mModel = model;
            title.setText(mModel.title);
            desc.setText(mModel.desc);
        }

        @Override
        public void onClick(View v) {
            if (mModel == null){

            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
