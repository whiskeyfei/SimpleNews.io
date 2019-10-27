package com.baselib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baselib.R;

/**
 * Created by wuming on 19/6/2.
 * <p>
 * title bar layout
 */
public class TitleBarLayout extends RelativeLayout {

    private ImageView mSearch;
    private TextView mTitle;
    private TextView mSet;

    public TitleBarLayout(Context context) {
        this(context, null);
    }

    public TitleBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.base_title_bar_layout, this, true);
        mSearch = findViewById(R.id.base_titlebar_search_id);
        mTitle = findViewById(R.id.base_toolbar_title_id);
        mSet = findViewById(R.id.base_toolbar_set_id);
    }

    /**
     * 只显示 title
     *
     * @param title
     */
    public void showTitle(String title) {
        mSearch.setVisibility(View.GONE);
        mSet.setVisibility(View.GONE);
        mTitle.setText(title);
    }

    /**
     * 显示设置 title
     *
     * @param title
     * @param listener
     */
    public void showSetView(String title, final View.OnClickListener listener) {
        mSearch.setVisibility(View.GONE);
        mSet.setVisibility(View.VISIBLE);
        mTitle.setText(title);
        mSet.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });
    }

    /**
     * 显示搜索 title
     *
     * @param title
     * @param listener
     */
    public void showSearchView(String title, final View.OnClickListener listener) {
        mTitle.setText(title);
        mSearch.setVisibility(View.VISIBLE);
        mSet.setVisibility(View.GONE);
        mSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });
    }
}
