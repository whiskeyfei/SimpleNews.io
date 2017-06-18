package com.kong.app.demo.about;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;

import me.drakeet.multitype.ItemViewBinder;

public class TextItemViewBinder extends ItemViewBinder<TextViewItem, TextItemViewBinder.TextHolder> {

    @NonNull
    @Override
    protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View inflate = inflater.inflate(R.layout.item_textview, parent, false);
        return new TextHolder(inflate);
    }

    @Override
    protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull TextViewItem textItem) {
        holder.text.setText(textItem.text);
    }

    static class TextHolder extends RecyclerView.ViewHolder {

        private final TextView text;

        TextHolder(@NonNull View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.item_textview_id);
        }
    }
}
