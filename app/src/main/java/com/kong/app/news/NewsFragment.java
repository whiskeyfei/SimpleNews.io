package com.kong.app.news;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.kong.R;
import com.kong.app.news.adapter.RVPagerAdapter;
import com.kong.app.news.beans.TabCategory;
import com.kong.app.news.commons.ApiConstants;
import com.kong.app.news.list.NewsContentView;
import com.kong.lib.fragment.BaseFragment;
import com.library.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment {

    private static final String TAG = "NewsFragment";

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private ViewStub mViewStub;
    private Handler mHandler = new Handler(Looper.myLooper());
    private View mRoot;

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRoot = null;
        mProgressView = null;
        mErrorView = null;
        mTablayout = null;
        mViewPager = null;
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_news, null);
        mViewStub = (ViewStub) mRoot.findViewById(R.id.sv_state_id);
        mTablayout = (TabLayout) mRoot.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) mRoot.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
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
        return mRoot;
    }

    private void setupViewPager(ViewPager viewPager) {
        final RVPagerAdapter adapter = new RVPagerAdapter();
        final List<View> listViews = new ArrayList<>();
        final List<String> mTitles = new ArrayList<>();
        for (TabCategory category : TabCategorys) {
            NewsContentView view = new NewsContentView(getActivity());
            view.setTabCategory(category);
            listViews.add(view);
            mTitles.add(category.categoryName);
        }
        adapter.setViews(listViews, mTitles);
        viewPager.setAdapter(adapter);
    }

    private void showSuccess() {
        if (mProgressView != null) {
            mProgressView.setVisibility(View.GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        mTablayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }

    private View mProgressView;

    private View getProgressView() {
        if (mProgressView == null) {
            ViewStub stub = (ViewStub) mViewStub.inflate().findViewById(R.id.vs_progress_id);
            mProgressView = stub.inflate();
            mProgressView.setVisibility(View.VISIBLE);
        }
        return mProgressView;
    }

    private View mErrorView;

    private View getErrorView() {
        if (mErrorView == null) {
            ViewStub stub = (ViewStub) mViewStub.inflate().findViewById(R.id.vs_error_id);
            mErrorView = stub.inflate();
        }
        return mErrorView;
    }

    private void showError() {
        if (mProgressView != null) {
            mProgressView.setVisibility(View.GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(View.VISIBLE);
        } else {
            getErrorView().setVisibility(View.VISIBLE);
        }
    }

    private void showLoading() {
        mTablayout.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        if (mProgressView != null) {
            mProgressView.setVisibility(View.VISIBLE);
        } else {
            getProgressView().setVisibility(View.VISIBLE);
        }
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