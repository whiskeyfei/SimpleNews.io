package com.kong.home.tab.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by whiskeyfei on 16/4/16.
 */
public class TabViewPager extends ViewPager {

    private boolean isEnabled = true;

    public TabViewPager(Context context) {
        super(context);
    }

    public TabViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isEnabled && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isEnabled && super.onTouchEvent(ev);
    }

    public void setScrollEnabled(boolean flag){
        isEnabled = flag;
    }
}
