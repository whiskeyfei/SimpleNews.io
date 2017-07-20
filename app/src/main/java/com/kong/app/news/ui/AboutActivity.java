package com.kong.app.news.ui;

import android.os.Bundle;
import android.view.View;

import com.kong.R;
import com.kong.app.news.NewsEntry;
import com.kong.app.news.base.ToolBarActivity;
import com.library.utils.ResourceUtil;

/**
 * Created by CaoPengfei on 17/5/26.
 * <p>
 * https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
 */

public class AboutActivity extends ToolBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ResourceUtil.getString(R.string.about));
        findViewById(R.id.about_btn_version).setOnClickListener(this);
        findViewById(R.id.about_btn_open_source).setOnClickListener(this);
        findViewById(R.id.about_btn_github).setOnClickListener(this);
        findViewById(R.id.about_btn_version).setOnClickListener(this);
        findViewById(R.id.about_btn_author).setOnClickListener(this);
        findViewById(R.id.about_btn_blog).setOnClickListener(this);
        findViewById(R.id.about_btn_email).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
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
