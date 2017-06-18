package com.kong.app.demo.me;

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

public class AvatarItemViewBinder extends ItemViewBinder<AvatarItem, AvatarItemViewBinder.ViewHolder> {

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_header_me, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AvatarItem item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView icon;
        public TextView name;
        public TextView account;
        public ImageView in;

        private AvatarItem mData;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.avatar_icon);
            in = (ImageView) itemView.findViewById(R.id.avatar_in);
            name = (TextView) itemView.findViewById(R.id.avatar_name);
            account = (TextView) itemView.findViewById(R.id.avatar_account);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ToolsUtil.showToast("AvatarItemView position:" + getAdapterPosition());
        }

        public void setData(AvatarItem data) {
            mData = data;
        }
    }
}
