package com.kong.app.blog;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.kong.R;
import com.kong.app.blog.model.Feed;
import com.kong.app.blog.tool.OnRVScollListener;
import com.kong.app.demo.about.TextItemViewBinder;
import com.kong.app.demo.about.TextViewItem;
import com.kong.app.news.adapter.IRVPagerView;
import com.kong.lib.utils.ListUtils;
import com.kong.lib.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by CaoPengfei on 17/8/21.
 */

public class BlogContentView extends FrameLayout implements OnRVScollListener.OnLoadListener,IRVPagerView {

    public static final String TAG = "BlogContentView";

    private RecyclerView mRecyclerView;
    private Feed.PostsBean mPostsBeans;
    private MultiTypeAdapter mAdapter;
    private List<Object> mObjectList = new ArrayList<Object>();
    private LinearLayoutManager mLayoutManager;

    public BlogContentView(Context context) {
        this(context, null);
    }

    public BlogContentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlogContentView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public BlogContentView setPostsBeans(Feed.PostsBean postsBeans) {
        mPostsBeans = postsBeans;
        setAdapter();
        return this;
    }

    private void init(Context context) {
        LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_blog_list, this, true);
        mRecyclerView = (RecyclerView) findViewById(R.id.blog_recycle_view_id);
    }

    private void setAdapter() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new OnRVScollListener(mLayoutManager,this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(Feed.PostsBean.ItemsBean.class, new BlogItemViewBinder());
        mAdapter.register(TextViewItem.class, new TextItemViewBinder());

        mRecyclerView.setAdapter(mAdapter);

        if (mPostsBeans == null) {
//            showError();
            return;
        }
        List<Feed.PostsBean.ItemsBean> list = mPostsBeans.getItems();
        if (!ListUtils.isEmpty(list)) {
            for (Feed.PostsBean.ItemsBean bean : list) {
                mObjectList.add(bean);
            }
            mAdapter.setItems(mObjectList);
            mAdapter.notifyDataSetChanged();
//            showResult();
        } else {
//            showError();
        }
    }

    @Override
    public void load() {
        if (!isEnd()){
            TextViewItem item = new TextViewItem();
            item.text = ResourceUtil.getString(R.string.blog_end);
            mObjectList.add(item);
            mAdapter.notifyDataSetChanged();
            setEnd(true);
        }
    }

    private boolean isEnd;

    private boolean isEnd(){
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    @Override
    public String getTitle() {
        return mPostsBeans.getCategory();
    }

    @Override
    public View getVeiw() {
        return this;
    }

    @Override
    public void scrollTop() {
        int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
        Log.i(TAG, "scrollTop firstVisibleItemPosition: " + firstVisibleItemPosition);
        if (mRecyclerView != null && firstVisibleItemPosition >0)
            mRecyclerView.smoothScrollToPosition(0);
    }
}
