package com.kong.app.news.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by CaoPengfei on 17/8/21.
 */

public class RVPagerAdapter extends PagerAdapter {

    private static final String TAG = "RVPagerAdapter";

    private List<View> mViews;
    private List<String> mTitles;

    public void setViews(List<View> views) {
        mViews = views;
    }

    public void setViews(List<View> views, List<String> titles) {
        mViews = views;
        mTitles = titles;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View contentView = mViews.get(position);
        view.addView(contentView);
        return contentView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        Log.i(TAG, "destroyItem: ");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
