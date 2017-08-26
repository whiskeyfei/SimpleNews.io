package com.kong.home.tab.widget;


import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kong.R;
import com.kong.lib.utils.ResourceUtil;

public class TabItemView extends FrameLayout implements IMsgView {

    private final ImageView mIcon;
    private final TextView mTitle;
    private final IMsgView mMsgView;

    private int mDefaultDrawable;
    private int mCheckedDrawable;

    private int mDefaultTextColor = ResourceUtil.getColor(R.color.tab_normal);
    private int mCheckedTextColor = ResourceUtil.getColor(R.color.tab_focus);

    public TabItemView(Context context) {
        this(context, null);
    }

    public TabItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.item_normal, this, true);
        mIcon = (ImageView) findViewById(R.id.item_iv_icon);
        mTitle = (TextView) findViewById(R.id.item_tv_title);
        mMsgView = (MsgView) findViewById(R.id.item_msg_id);
    }

    public void initialize(@DrawableRes int drawableRes, @DrawableRes int checkedDrawableRes, String title) {
        mDefaultDrawable = drawableRes;
        mCheckedDrawable = checkedDrawableRes;
        mTitle.setText(title);
    }

    public void setChecked(boolean checked) {
        if (checked) {
            mIcon.setImageResource(mCheckedDrawable);
            mTitle.setTextColor(mCheckedTextColor);
        } else {
            mIcon.setImageResource(mDefaultDrawable);
            mTitle.setTextColor(mDefaultTextColor);
        }
    }

    public String getTitle() {
        return mTitle.getText().toString();
    }

    public void setTextDefaultColor(@ColorInt int color) {
        mDefaultTextColor = color;
    }

    public void setTextCheckedColor(@ColorInt int color) {
        mCheckedTextColor = color;
    }

    @Override
    public void setMsgCount(int count) {
        mMsgView.setMsgCount(count);
    }

    @Override
    public void showPoint() {
        mMsgView.showPoint();
    }

    @Override
    public void hidePoint() {
        mMsgView.hidePoint();
    }
}
