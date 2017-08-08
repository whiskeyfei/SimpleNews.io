package com.kong.home.tab.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.kong.home.tab.OnTabItemSelectedListener;
import com.kong.home.tab.adapter.BottomTabAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by CaoPengfei on 16/4/11.
 */
public class BottomTabLayout extends LinearLayout {

    private static final String TAG = "BottomTabLayout";
    private int mSelectedIndex = -1;
    private BottomTabAdapter mBottomTabAdapter;

    private Context mContext;
    private TabViewPager mViewPager;

    private SparseArray<View> mTabViews;
    private List<OnTabItemSelectedListener> mListeners = new ArrayList<>();

    public BottomTabLayout(Context context) {
        this(context, null);
    }

    public BottomTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(BottomTabAdapter adapter) {
        this.mBottomTabAdapter = adapter;
        init(getContext());
    }

    private void init(Context context) {
        mContext = context;
        if (mBottomTabAdapter == null) {
            throw new IllegalArgumentException("mBottomTabAdapter is null");
        }

        int len = mBottomTabAdapter.getCount();
        if (len <= 0) {
            return;
        }

        if (mTabViews == null) {
            mTabViews = new SparseArray<View>();
        }

        mTabViews.clear();
        removeAllViews();

        for (int i = 0; i < len; i++) {
            TabItemView itemView = (TabItemView) mBottomTabAdapter.getView(i);
            itemView.setChecked(false);
            mTabViews.put(i, itemView);
            final int finali = i;
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelectIndex(finali);
                }
            });
            LayoutParams lp = (LayoutParams) itemView.getLayoutParams();
            if (lp == null) {
                lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            }
            lp.gravity = Gravity.CENTER;
            lp.weight = 1;
            addView(itemView, lp);
            mSelectedIndex = 0;
            ((TabItemView) mTabViews.get(0)).setChecked(true);
        }
    }

    private void setSelectIndex(int index) {
        if (index == mSelectedIndex) {
            for (OnTabItemSelectedListener listener : mListeners) {
                listener.onSelectRepeat(mSelectedIndex);
            }
            return;
        }

        int oldSelectedIndex = mSelectedIndex;
        mSelectedIndex = index;

        if (oldSelectedIndex >= 0) {
            ((TabItemView) mTabViews.get(oldSelectedIndex)).setChecked(false);
        }
        ((TabItemView) mTabViews.get(mSelectedIndex)).setChecked(true);

        for (OnTabItemSelectedListener listener : mListeners) {
            listener.onSelected(mSelectedIndex, oldSelectedIndex);
        }
    }

    public void addTabItemSelectedListener(OnTabItemSelectedListener listener) {
        mListeners.add(listener);
    }

    public int getSelectedIndex() {
        return mSelectedIndex;
    }

    public BottomTabAdapter getBottomTabAdapter() {
        return mBottomTabAdapter;
    }

    private ViewPagerPageChangeListener mPageChangeListener;

    private class ViewPagerPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (getSelectedIndex() != position) {
                setSelectIndex(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    public void setViewPager(TabViewPager viewPager) {
        mViewPager = viewPager;
        if (viewPager == null) {
            return;
        }

        mViewPager = viewPager;

        if (mPageChangeListener != null) {
            mViewPager.removeOnPageChangeListener(mPageChangeListener);
        } else {
            mPageChangeListener = new ViewPagerPageChangeListener();
        }

        int index = mViewPager.getCurrentItem();
        if (getSelectedIndex() != index) {
            setSelectIndex(index);
        }
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        addTabItemSelectedListener(mTabItemListener);
    }

    private OnTabItemSelectedListener mTabItemListener = new OnTabItemSelectedListener() {

        @Override
        public void onSelected(int index, int old) {
            if (mViewPager != null) {
                mViewPager.setCurrentItem(index, false);
            }
        }

        @Override
        public void onSelectRepeat(int index) {
            Log.i(TAG, "onSelectRepeat: index" + index);
        }
    };

    private static final String INSTANCE_STATUS = "INSTANCE_STATUS";
    private final String STATUS_SELECTED = "STATUS_SELECTED";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putInt(STATUS_SELECTED, getSelectedIndex());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            int selected = bundle.getInt(STATUS_SELECTED, 0);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));

            if (selected != 0) {
                setSelectIndex(selected);
            }
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
