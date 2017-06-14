package com.kong.app.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kong.BuildConfig;
import com.kong.R;
import com.library.AppRun;
import com.library.BaseActivity;
import com.library.utils.ToolsUtil;

/**
 * Created by CaoPengfei on 17/5/26.
 * <p>
 * https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener {
    public static final String VERSION_TEXT = BuildConfig.VERSION_NAME + "-build-" + BuildConfig.VERSION_CODE;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mToolbar = (Toolbar) findViewById(R.id.about_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
                ToolsUtil.intentToUrl(AppRun.get().getApplicationContext(), "https://github.com/whiskeyfei");
                break;

            case R.id.about_btn_author:
                ToolsUtil.intentToUrl(AppRun.get().getApplicationContext(), "http://www.jianshu.com/u/fa272f63280a");
                break;

            case R.id.about_btn_email:
                break;

            case R.id.about_btn_blog:
                ToolsUtil.intentToUrl(AppRun.get().getApplicationContext(), "http://doraemonyu.me/");
                break;
        }
    }
}
