package com.kong.app.me;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;
import com.kong.lib.fragment.BaseFragment;
import com.library.utils.ResourceUtil;

/**
 * Created by CaoPengfei on 17/7/28.
 */

public abstract class ToolBarFragment extends BaseFragment {

    private Toolbar mToolbar;
    private TextView mTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        mToolbar = (Toolbar) view.findViewById(R.id.base_toolbar);
        mTitle = (TextView) view.findViewById(R.id.base_toolbar_title);
        mToolbar.setTitle("");
        return createView(view, container, savedInstanceState);
    }

    public void setTitle(CharSequence title) {
        if (mTitle != null) {
            mTitle.setText(title);
        }
    }

    public void setTitle(int titleId) {
        if (mTitle != null) {
            mTitle.setText(ResourceUtil.getString(titleId));
        }
    }

    public TextView getTitle() {
        return mTitle;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    protected abstract int getLayoutId();

    public abstract View createView(View view, ViewGroup container, Bundle savedInstanceState);
}
