package com.kong.app.news.list;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.kong.R;
import com.kong.app.news.NewsEntry;
import com.kong.app.news.adapter.IRVPagerView;
import com.kong.app.news.adapter.NewsAdapter;
import com.kong.app.news.adapter.OnItemClickListener;
import com.kong.app.news.beans.NewModel;
import com.kong.app.news.beans.TabCategory;
import com.kong.lib.mvp.Injection;
import com.kong.lib.utils.SToast;
import com.library.utils.ListUtils;
import com.library.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;


public class NewsContentView extends FrameLayout implements NewsContract.View ,IRVPagerView{

    private static final String TAG = "NewsContentView";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private List<NewModel> mData;
    private NewsContract.Presenter mNewsPresenter;

    private int pageIndex = 1;

    private TabCategory mTabCategory;

    public NewsContentView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public NewsContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NewsContentView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NewsContentView setTabCategory(TabCategory tabCategory) {
        mTabCategory = tabCategory;
        onRefresh();
        return this;
    }

    public void init(Context context) {
        new NewsPresenter(this, Injection.provideSchedulerProvider());
        LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_newslist, this, true);
        initView();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow: ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow: ");
    }

    public void initView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recycle_view_id);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter(getContext().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                pageIndex+=1;
                mNewsPresenter.loadNews(mTabCategory.url, pageIndex);
            }
        }
    };

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {
            NewModel news = mAdapter.getItem(position);
            if (news != null){
                NewsEntry.get().startBrowser(getContext(),news.newUrl,news.title);
            }
        }
    };

    @Override
    public void showProgress() {
    }

    @Override
    public void addNews(List<NewModel> newsList) {
        mAdapter.isShowFooter(true);
        if(mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(newsList);
        if(pageIndex == 1) {
            mAdapter.setNewDate(mData);
        } else {
            if(ListUtils.isEmpty(mData)) {
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg() {
        if(pageIndex == 1) {
            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        SToast.makeText(getContext(), ResourceUtil.getString(R.string.load_fail),Toast.LENGTH_SHORT).show();
    }

    private boolean isEnd = false;

    @Override
    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
        if (this.isEnd){
            mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
            SToast.makeText(getContext(),ResourceUtil.getString(R.string.load_end),Toast.LENGTH_SHORT).show();
        }
    }

    public void onRefresh() {
        pageIndex = 1;
        if(mData != null) {
            mData.clear();
        }
        mNewsPresenter.loadNews(mTabCategory.url, pageIndex);
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mNewsPresenter = presenter;
    }


    @Override
    public String getTitle() {
        return mTabCategory.categoryName;
    }

    @Override
    public View getVeiw() {
        return this;
    }
}
