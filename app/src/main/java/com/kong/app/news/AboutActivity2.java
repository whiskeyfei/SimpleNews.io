package com.kong.app.news;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kong.R;
import com.library.BaseActivity;

/**
 * Created by CaoPengfei on 17/5/26.
 *
 * https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
 */

public class AboutActivity2 extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about2);
        mToolbar = (Toolbar) findViewById(R.id.about2_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
