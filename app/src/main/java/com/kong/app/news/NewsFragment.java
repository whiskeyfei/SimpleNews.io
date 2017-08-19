package com.kong.app.news;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kong.R;
import com.kong.app.news.adapter.MyPagerAdapter;
import com.kong.app.news.beans.TabCategory;
import com.kong.app.news.commons.ApiConstants;
import com.kong.app.news.list.NewsListFragment;
import com.kong.lib.fragment.BaseFragment;
import com.library.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    Handler mHandler = new Handler(Looper.myLooper());

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTablayout.setVisibility(View.GONE);
        mViewPager.setOffscreenPageLimit(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setupViewPager(mViewPager);
                        mTablayout.setupWithViewPager(mViewPager);
                        mTablayout.setVisibility(View.VISIBLE);
                    }
                }, 1000);
            }
        }).start();
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        int len = TabCategorys.size();
        for (int i = 0; i < len; i++) {
            TabCategory category = TabCategorys.get(i);
            adapter.addFragment(NewsListFragment.newInstance(category),category.categoryName);
        }
        viewPager.setAdapter(adapter);
    }

    //TODO
    private final static List<TabCategory> TabCategorys = new ArrayList<>();

    static {
        TabCategorys.add(new TabCategory(ApiConstants.HOST_WEIXIN, ResourceUtil.getString(R.string.top), 0));
        TabCategorys.add(new TabCategory(ApiConstants.HOST_YULE, ResourceUtil.getString(R.string.recreation), 1));
        TabCategorys.add(new TabCategory(ApiConstants.HOST_KEJI, ResourceUtil.getString(R.string.science), 2));
        TabCategorys.add(new TabCategory(ApiConstants.HOST_JIANKANG, ResourceUtil.getString(R.string.health), 3));
        TabCategorys.add(new TabCategory(ApiConstants.HOST_SPORTS, ResourceUtil.getString(R.string.sport), 4));
    }

}