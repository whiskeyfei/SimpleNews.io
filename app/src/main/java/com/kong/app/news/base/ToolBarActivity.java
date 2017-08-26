package com.kong.app.news.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kong.R;
import com.kong.lib.BaseActivity;
import com.kong.lib.utils.ResourceUtil;

/**
 * Created by CaoPengfei on 17/7/17.
 */

public abstract class ToolBarActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mToolbar = (Toolbar) findViewById(R.id.base_toolbar);
        initToolBar(mToolbar);
    }

    protected abstract int getLayoutId();

    @Override
    public void setTitle(CharSequence title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (mToolbar != null) {
            mToolbar.setTitle(ResourceUtil.getString(titleId));
        }
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void initToolBar(Toolbar toolbar) {
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    public void initToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        initToolBar(toolbar);
    }

}
