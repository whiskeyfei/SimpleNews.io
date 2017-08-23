package com.kong.app.blog.tool;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by CaoPengfei on 17/6/27.
 */

public class OnRVScollListener extends RecyclerView.OnScrollListener {

    public interface OnLoadListener{
        void load();
    }

    private LinearLayoutManager mLinearLayoutManager;
    private OnLoadListener mOnLoadListener;
    private int lastVisibleItem;

    public OnRVScollListener(LinearLayoutManager layoutManager) {
        this.mLinearLayoutManager = layoutManager;
    }

    public OnRVScollListener(LinearLayoutManager layoutManager, OnLoadListener listener) {
        this(layoutManager);
        this.mOnLoadListener = listener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        final int itemCount = mLinearLayoutManager.getItemCount();
        //TODO itemCount 大小需要抽离
        if (newState == RecyclerView.SCROLL_STATE_IDLE && itemCount >= 5 && lastVisibleItem + 1 == itemCount && mOnLoadListener != null) {
            mOnLoadListener.load();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy < 0){
            return;
        }
        lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
    }
}
