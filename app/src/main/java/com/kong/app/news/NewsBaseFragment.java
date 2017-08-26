package com.kong.app.news;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.kong.R;
import com.kong.app.news.adapter.IRVPagerView;
import com.kong.home.tab.event.SelectRepeatEvent;
import com.kong.lib.fragment.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public abstract class NewsBaseFragment extends BaseFragment {

    private static final String TAG = "NewsBaseFragment";

    protected TabLayout mTablayout;
    protected ViewPager mViewPager;
    protected ViewStub mViewStub;
    protected Handler mHandler = new Handler(Looper.myLooper());
    protected View mRoot;
    protected final List<IRVPagerView> mIRVPagerViews = new ArrayList<>();

    public abstract void onCreateView();
    public abstract int getCurrentType();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectRepeat(SelectRepeatEvent event){
        if (!(event.type == getCurrentType())){
            return;
        }
        int selectPos = mTablayout.getSelectedTabPosition();
        Log.i(TAG, "onSelectRepeat: event :" + event);
        Log.i(TAG, "onSelectRepeat: selectPos :" + selectPos);
        IRVPagerView pagerView = mIRVPagerViews.get(selectPos);
        if (pagerView != null) pagerView.scrollTop();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_news, null);
        mViewStub = (ViewStub) mRoot.findViewById(R.id.sv_state_id);
        mTablayout = (TabLayout) mRoot.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) mRoot.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        onCreateView();
        return mRoot;
    }

    public void showSuccess() {
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

    public void showError() {
        if (mProgressView != null) {
            mProgressView.setVisibility(View.GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(View.VISIBLE);
        } else {
            getErrorView().setVisibility(View.VISIBLE);
        }
    }

    public void showLoading() {
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

}