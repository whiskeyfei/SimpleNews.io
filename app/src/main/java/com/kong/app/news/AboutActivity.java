package com.kong.app.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kong.R;
import com.kong.app.news.base.ThemeActivity;
import com.library.utils.ResourceUtil;

/**
 * Created by CaoPengfei on 17/5/26.
 * <p>
 * https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
 */

public class AboutActivity extends ThemeActivity implements View.OnClickListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mToolbar = (Toolbar) findViewById(R.id.about_toolbar);
        mToolbar.setTitle(ResourceUtil.getString(R.string.about));
        initToolBar(mToolbar);
        findViewById(R.id.about_btn_version).setOnClickListener(this);
        findViewById(R.id.about_btn_open_source).setOnClickListener(this);
        findViewById(R.id.about_btn_github).setOnClickListener(this);
        findViewById(R.id.about_btn_version).setOnClickListener(this);
        findViewById(R.id.about_btn_author).setOnClickListener(this);
        findViewById(R.id.about_btn_blog).setOnClickListener(this);
        findViewById(R.id.about_btn_email).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_btn_version:

                break;

            case R.id.about_btn_open_source:
                break;

            case R.id.about_btn_github:
                NewsEntry.get().startBrowser(AboutActivity.this, "https://github.com/whiskeyfei");
                break;

            case R.id.about_btn_author:
                NewsEntry.get().startBrowser(AboutActivity.this, "http://www.jianshu.com/u/fa272f63280a");
                break;

            case R.id.about_btn_email:
                break;

            case R.id.about_btn_blog:
                NewsEntry.get().startBrowser(AboutActivity.this, "http://doraemonyu.me/");
                break;
        }
    }
}
