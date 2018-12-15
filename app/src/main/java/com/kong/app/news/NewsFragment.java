package com.kong.app.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.baselib.utlis.ResourceUtil;
import com.kong.R;
import com.kong.app.news.adapter.IRVPagerView;
import com.kong.app.news.adapter.RVPagerAdapter;
import com.kong.app.news.beans.TabCategory;
import com.kong.app.news.commons.ApiConstants;
import com.kong.app.news.list.NewsContentView;
import com.kong.home.tab.event.SelectRepeatEvent;


import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends ListBaseFragment {

    private static final String TAG = "NewsFragment";

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateView() {
        showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setupViewPager(mViewPager);
                        mTablayout.setupWithViewPager(mViewPager);
                        showSuccess();
                    }
                }, 1200);
            }
        }).start();
    }

    @Override
    public int getCurrentType() {
        return SelectRepeatEvent.HOMEINDEX;
    }

    private void setupViewPager(ViewPager viewPager) {
        mIRVPagerViews.clear();
        final RVPagerAdapter adapter = new RVPagerAdapter();

        for (TabCategory category : TabCategorys) {
            IRVPagerView view = new NewsContentView(getActivity()).setTabCategory(category);
            mIRVPagerViews.add(view);
        }
        adapter.setIRVPagerViews(mIRVPagerViews);
        viewPager.setAdapter(adapter);
    }

    private final static List<TabCategory> TabCategorys = new ArrayList<>();

    static {
        TabCategorys.add(new TabCategory(ApiConstants.HOST_WEIXIN, ResourceUtil.getString(R.string.top), 0));
        TabCategorys.add(new TabCategory(ApiConstants.HOST_YULE, ResourceUtil.getString(R.string.recreation), 1));
        TabCategorys.add(new TabCategory(ApiConstants.HOST_KEJI, ResourceUtil.getString(R.string.science), 2));
        TabCategorys.add(new TabCategory(ApiConstants.HOST_JIANKANG, ResourceUtil.getString(R.string.health), 3));
        TabCategorys.add(new TabCategory(ApiConstants.HOST_SPORTS, ResourceUtil.getString(R.string.sport), 4));
    }

}