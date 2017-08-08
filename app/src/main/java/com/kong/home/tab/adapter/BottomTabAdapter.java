package com.kong.home.tab.adapter;

import android.content.Context;
import android.view.View;

import com.kong.home.tab.widget.TabItemView;
import com.kong.home.tab.TabItemModel;

import java.util.List;

/**
 * Created by CaoPengfei on 16/4/13.
 */
public class BottomTabAdapter extends BaseTabAdapter<TabItemModel> {

    private static final String TAG = "BottomTabAdapter";

    public BottomTabAdapter(Context context, List<TabItemModel> tabItemModels) {
        super(context, tabItemModels);
    }

    @Override
    public View getView(final int position) {
        final TabItemView itemView = new TabItemView(getContext());
        final TabItemModel info = mLists.get(position);
        itemView.initialize(info.drawable, info.checkedDrawable, info.text);
        return itemView;
    }
}
