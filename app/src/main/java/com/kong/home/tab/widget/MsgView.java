package com.kong.home.tab.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kong.R;

import java.util.Locale;

public class MsgView extends FrameLayout implements IMsgView {

    private View mOvalView;//红点
    private TextView mMessageView;//红点 + 数字

    private int mMsgCount;

    public MsgView(Context context) {
        this(context, null);
    }

    public MsgView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MsgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.round_message_layout, this, true);
    }

    private View getOvalView() {
        if (mOvalView == null) {
            mOvalView = findViewById(R.id.oval_view_id);
        }
        return mOvalView;
    }

    private TextView getMessageView() {
        if (mMessageView == null) {
            mMessageView = (TextView) findViewById(R.id.msg_count_view_id);
            mMessageView.setTypeface(Typeface.DEFAULT_BOLD);
            mMessageView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        }
        return mMessageView;
    }

    public void tintMessageBackground(@ColorInt int color) {
        Drawable drawable = tint(ContextCompat.getDrawable(getContext(), R.drawable.circle_point), color);
        ViewCompat.setBackground(getOvalView(), drawable);
        ViewCompat.setBackground(getMessageView(), drawable);
    }

    public void setMessageNumberColor(@ColorInt int color) {
        getMessageView().setTextColor(color);
    }

    public static Drawable tint(Drawable drawable, int color) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        wrappedDrawable.mutate();
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }

    @Override
    public void setMsgCount(int count) {
        mMsgCount = count;

        if (mMsgCount < 0) {
            getMessageView().setVisibility(View.INVISIBLE);
            return;
        }

        getOvalView().setVisibility(View.INVISIBLE);
        getMessageView().setVisibility(View.VISIBLE);
        setMsgTextSize(getMessageView(), mMsgCount);
        getMessageView().setText(String.format(Locale.ENGLISH, "%d", mMsgCount <= 99 ? mMsgCount : 99));
    }

    @Override
    public void showPoint() {
        getOvalView().setVisibility(mMsgCount > 0 ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void hidePoint() {
        getOvalView().setVisibility(View.INVISIBLE);
    }

    private void setMsgTextSize(TextView textView, int count) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, count < 10 ? 12 : 10);
    }
}
