package com.baselib.bottom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baselib.R;

import java.util.ArrayList;

/**
 * 文字切换 TabLayout
 */
public class SingleTextTabLayout extends FrameLayout implements View.OnClickListener {

    private Context mContext;

    /**
     * 数据
     */
    private ArrayList<CustomTabEntity> mTabEntitys = new ArrayList<>();

    /**
     * Tab父控件
     */
    private LinearLayout mTabsContainer;

    /**
     * 当前 Tab 位置，默认0，第一个
     */
    private int mCurrentTab;

    /**
     * 最后一次 Tab 位置
     */
    private int mLastTab;

    /**
     * Tab Item 个数
     */
    private int mTabCount;

    /**
     * 文字选中颜色
     */
    private int mTextSelectColor = Color.parseColor("#ffffff");

    /**
     * 文字未选中颜色
     */
    private int mTextUnselectColor = Color.parseColor("#AAffffff");

    /**
     * 监听Tab切换
     */
    private OnTabSelectListener mOnTabSelectListener;

    public SingleTextTabLayout(Context context) {
        this(context, null, 0);
    }

    public SingleTextTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleTextTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);//重写onDraw方法,需要调用这个方法来清除flag
        setClipChildren(false);
        setClipToPadding(false);

        this.mContext = context;
        mTabsContainer = new LinearLayout(context);
        addView(mTabsContainer);
    }

    public void setOnTabSelectListener(OnTabSelectListener listener) {
        this.mOnTabSelectListener = listener;
    }

    /**
     * 设置 Tab 数据
     *
     * @param tabEntitys
     */
    public void setTabData(ArrayList<CustomTabEntity> tabEntitys) {
        if (tabEntitys == null || tabEntitys.size() == 0) {
            throw new IllegalStateException("TabEntitys can not be NULL or EMPTY !");
        }

        this.mTabEntitys.clear();
        this.mTabEntitys.addAll(tabEntitys);

        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     */
    public void notifyDataSetChanged() {
        mTabsContainer.removeAllViews();//清空父控件view
        this.mTabCount = mTabEntitys.size();
        View tabView;
        for (int i = 0; i < mTabCount; i++) {
            tabView = View.inflate(mContext, R.layout.layout_text_tab, null);
            tabView.setTag(i);
            addTab(i, tabView);
        }
    }

    /**
     * 创建并向父控件 mTabsContainer 添加tab
     */
    private void addTab(final int position, View tabView) {
        TextView title = (TextView) tabView.findViewById(R.id.tv_tab_title);
        title.setText(mTabEntitys.get(position).getTabTitle());
        tabView.setOnClickListener(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
        mTabsContainer.addView(tabView, position, layoutParams);
    }

    /**
     * 循环遍历所有Tab位置，设置状态，可以恢复选择某个Item态
     *
     * @param position
     */
    private void updateTabSelection(int position) {
        for (int i = 0; i < mTabCount; ++i) {
            View tabView = mTabsContainer.getChildAt(i);
            final boolean isSelect = i == position;
            TextView title = (TextView) tabView.findViewById(R.id.tv_tab_title);
            title.setTextColor(isSelect ? mTextSelectColor : mTextUnselectColor);
        }
    }

    /**
     * 设置当前Tab
     *
     * @param currentTab position
     */
    public void setCurrentTab(int currentTab) {
        mLastTab = this.mCurrentTab;
        this.mCurrentTab = currentTab;
        updateTabSelection(currentTab);
        invalidate();
    }

    @Override
    public void onClick(View v) {
        final int position = (Integer) v.getTag();
        if (mCurrentTab != position) {
            setCurrentTab(position);
            if (mOnTabSelectListener != null) {
                mOnTabSelectListener.onTabSelect(position);
            }
        } else {
            if (mOnTabSelectListener != null) {
                mOnTabSelectListener.onTabReselect(position);
            }
        }
    }
}
