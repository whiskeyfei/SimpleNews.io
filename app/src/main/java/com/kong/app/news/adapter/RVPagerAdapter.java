package com.kong.app.news.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoPengfei on 17/8/21.
 */

public class RVPagerAdapter extends PagerAdapter {

    private static final String TAG = "RVPagerAdapter";

    private List<IRVPagerView> mIRVPagerViews = new ArrayList<>();

    public void setIRVPagerViews(List<IRVPagerView> IRVPagerViews) {
        mIRVPagerViews.clear();
        mIRVPagerViews.addAll(IRVPagerViews);
    }

    @Override
    public int getCount() {
        return mIRVPagerViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View contentView = mIRVPagerViews.get(position).getVeiw();
        view.addView(contentView);
        return contentView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View contentView = mIRVPagerViews.get(position).getVeiw();
        container.removeView(contentView);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mIRVPagerViews.get(position).getTitle();
    }
}
