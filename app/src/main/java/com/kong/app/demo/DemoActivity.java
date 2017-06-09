package com.kong.app.demo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kong.R;
import com.library.BaseActivity;

/**
 * Created by CaoPengfei on 17/6/9.
 */

public class DemoActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        mToolbar = (Toolbar) findViewById(R.id.demo_toolbar);
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
