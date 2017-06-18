package com.kong.app.demo.descover;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;
import com.library.utils.ToolsUtil;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by CaoPengfei on 17/6/18.
 */

public class HeadertemViewBinder extends ItemViewBinder<Header, HeadertemViewBinder.ViewHolder> {

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_header, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Header item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        private Header mData;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_textview_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ToolsUtil.showToast("header v position:" + getAdapterPosition());
        }

        public void setData(Header data) {
            mData = data;
            title.setText(mData.title);
        }
    }
}
