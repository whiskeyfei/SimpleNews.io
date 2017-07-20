package com.kong.app.demo.note;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.news.base.ToolBarActivity;
import com.library.utils.ResourceUtil;

import java.io.IOException;

/**
 * Created by CaoPengfei on 17/6/9.
 */

public class NoteActivity extends ToolBarActivity {

    private TextView mTextView;
    private final Handler mHandler = new Handler(Looper.myLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.note);
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
    protected int getLayoutId() {
        return R.layout.activity_note;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
