package com.kong.app.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kong.R;
import com.library.BaseActivity;

/**
 * Created by CaoPengfei on 17/6/14.
 */

public class BrowserActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        mToolbar = (Toolbar) findViewById(R.id.browser_toolbar);
        initToolBar(mToolbar);
    }
}
