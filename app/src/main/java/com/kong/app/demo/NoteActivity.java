package com.kong.app.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kong.R;
import com.library.BaseActivity;
import com.library.utils.ResourceUtil;

import java.io.IOException;

/**
 * Created by CaoPengfei on 17/6/9.
 */

public class NoteActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mTextView;
    private final Handler mHandler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mToolbar = (Toolbar) findViewById(R.id.note_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mTextView = (TextView) findViewById(R.id.tv_note);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    mTextView.setText(ResourceUtil.getRawFileStr(R.raw.demo));
                } catch (IOException e) {
                    e.printStackTrace();
                    mTextView.setText("not load content!!");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
