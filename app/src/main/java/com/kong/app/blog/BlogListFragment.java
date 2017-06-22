package com.kong.app.blog;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kong.R;
import com.kong.app.blog.model.Feed;
import com.kong.lib.share.common.fragment.BaseFragment;
import com.library.utils.ListUtils;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by CaoPengfei on 17/6/21.
 */

public class BlogListFragment extends BaseFragment {

    public static final String FRAGMENT_TYPE_KEY = "bean";

    private RecyclerView mRecyclerView;
    private Feed.PostsBean mPostsBeans;
    private RelativeLayout mLoadingView;
    private LinearLayout mErrorView;

    public static BlogListFragment newInstance(Feed.PostsBean bean) {
        Bundle args = new Bundle();
        BlogListFragment fragment = new BlogListFragment();
        args.putSerializable(FRAGMENT_TYPE_KEY, bean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_list, null);
        mPostsBeans = (Feed.PostsBean) getArguments().getSerializable(FRAGMENT_TYPE_KEY);
        initView(view);
        startLoading();
        setAdapter();
        return view;
    }

    private void setAdapter() {
        mRecyclerView.setVisibility(View.GONE);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(Feed.PostsBean.ItemsBean.class, new BlogItemViewBinder());
        mRecyclerView.setAdapter(adapter);
        if (mPostsBeans != null && !ListUtils.isEmpty(mPostsBeans.getItems())) {
            adapter.setItems(mPostsBeans.getItems());
            adapter.notifyDataSetChanged();
            showResult();
        }else{
            showError();
        }
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.blog_recycle_view_id);
        mLoadingView = (RelativeLayout) view.findViewById(R.id.blog_loading_view_id);
        mErrorView = (LinearLayout) view.findViewById(R.id.blog_error_view_id);
    }

    private void startLoading() {
        mRecyclerView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    private void showError() {
        mRecyclerView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
    }

    private void showResult() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

}
