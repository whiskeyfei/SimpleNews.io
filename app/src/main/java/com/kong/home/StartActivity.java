package com.kong.home;

import android.os.Bundle;

import com.kong.R;
import com.kong.app.news.NewsEntry;
import com.library.BaseActivity;


public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        NewsEntry.get().startMain(StartActivity.this);
    }
}
