package com.kong.app.news.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kong.R;
import com.kong.app.news.base.ThemeActivity;
import com.library.utils.ResourceUtil;

/**
 * Created by CaoPengfei on 17/5/26.
 *
 * https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
 */

public class AboutActivity2 extends ThemeActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);
        mToolbar = (Toolbar) findViewById(R.id.about2_toolbar);
        mToolbar.setTitle(ResourceUtil.getString(R.string.about));
        initToolBar(mToolbar);
    }
}
