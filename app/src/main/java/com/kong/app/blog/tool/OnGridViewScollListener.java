package com.kong.app.blog.tool;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by CaoPengfei on 17/6/27.
 */

public class OnGridViewScollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = "OnGridViewScollListener";

    public interface OnLoadListener {
        void loadMore();
    }

    int mItemCount;
    int spanCount;
    private GridLayoutManager mGridLayoutManager;
    private OnLoadListener mOnLoadListener;
    private int lastVisibleItem;
    int mLineCount;

    public OnGridViewScollListener(GridLayoutManager layoutManager) {
        this.mGridLayoutManager = layoutManager;
    }

    public OnGridViewScollListener(GridLayoutManager layoutManager, OnLoadListener listener) {
        this(layoutManager);
        this.mOnLoadListener = listener;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        mItemCount = mGridLayoutManager.getItemCount();
        spanCount = mGridLayoutManager.getSpanCount();

        if (mItemCount % spanCount == 0) {
            mLineCount = mItemCount / spanCount + 1;
        } else {
            mLineCount = (mItemCount / spanCount) + 2;
        }
        int visibleItemCount = mGridLayoutManager.getChildCount();
        int pastVisiblesItems = mGridLayoutManager.findFirstVisibleItemPosition();
        if (newState == RecyclerView.SCROLL_STATE_IDLE && mOnLoadListener != null) {
//            int lostItemLine;
//            if (lastVisibleItem % spanCount == 0) {
//                lostItemLine = lastVisibleItem / spanCount + 1;
//            } else {
//                lostItemLine = lastVisibleItem / spanCount + 2;
//            }

            if (visibleItemCount + pastVisiblesItems>= mItemCount) {
                mOnLoadListener.loadMore();
            }
//            Log.i(TAG,"mLineCount:"+ mLineCount +"mLineCount:"+mLineCount);
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        lastVisibleItem = mGridLayoutManager.findLastCompletelyVisibleItemPosition();
    }

}
