package com.kong.app.blog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kong.R;
import com.kong.app.blog.model.Feed;
import com.kong.app.news.adapter.MyPagerAdapter;
import com.kong.lib.fragment.BaseFragment;

import java.util.List;

public class BlogFragment extends BaseFragment implements BlogContract.View {

    private static final String TAG = "BlogFragment";

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private BlogContract.Presenter mPresenter;

    public static BlogFragment newInstance() {
        Bundle args = new Bundle();
        BlogFragment fragment = new BlogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new BlogPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTablayout.setVisibility(View.GONE);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mPresenter.start();
        return view;
    }

    private void setupViewPager(ViewPager viewPager, List<Feed.PostsBean> posts) {
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        for (Feed.PostsBean postBean : posts) {
            adapter.addFragment(BlogListFragment.newInstance(postBean), postBean.getCategory());
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public void setPresenter(BlogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onSuccess(Feed feed) {
        Log.i(TAG, "onSuccess:" + feed);
        setupViewPager(mViewPager, feed.getPosts());
        mViewPager.setOffscreenPageLimit(mViewPager.getAdapter().getCount());
        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTablayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}