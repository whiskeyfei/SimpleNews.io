package com.kong.home.tab.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kong.lib.fragment.BaseFragment;

import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mTabFragment;

    public MyViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        mTabFragment = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mTabFragment.get(position);
    }

    @Override
    public int getCount() {
        return mTabFragment.size();
    }
}
