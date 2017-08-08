package com.kong.app.demo.me;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.news.NewsEntry;

import me.drakeet.multitype.ItemViewBinder;

import static com.kong.app.me.MeFragment.TYPE_ABOUT;
import static com.kong.app.me.MeFragment.TYPE_PROBLEM;
import static com.kong.app.me.MeFragment.TYPE_SETTING;


/**
 * Created by CaoPengfei on 17/6/18.
 */

public class SettingImgTvItemViewBinder extends ItemViewBinder<SettingImgTvItem, SettingImgTvItemViewBinder.ViewHolder> {

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_setting_image_tv_h, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull SettingImgTvItem item) {
        holder.setData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public ImageView icon;
        private SettingImgTvItem mData;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.setting_item_h_title);
            icon = (ImageView) itemView.findViewById(R.id.setting_item_h_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (getAdapterPosition()) {
                case TYPE_ABOUT:
                    NewsEntry.get().startAbout(v.getContext());
                    break;
                case TYPE_PROBLEM:

                    break;
                case TYPE_SETTING:
                    NewsEntry.get().startSetting(v.getContext());
                    break;
            }
        }

        public void setData(SettingImgTvItem data) {
            mData = data;
            title.setText(mData.title);
            icon.setImageResource(mData.icon);
        }
    }
}
