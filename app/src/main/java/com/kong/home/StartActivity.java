package com.kong.home;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;

import com.kong.R;
import com.kong.app.news.NewsEntry;


public class StartActivity extends Activity {

    private final Handler mHandler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                NewsEntry.get().startMain(StartActivity.this);
                finish();
            }
        }, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return true;
    }
}
