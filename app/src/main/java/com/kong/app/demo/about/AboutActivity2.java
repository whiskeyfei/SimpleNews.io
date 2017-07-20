package com.kong.app.demo.about;

import android.os.Bundle;

import com.kong.R;
import com.kong.app.news.base.ToolBarActivity;
import com.library.utils.ResourceUtil;

/**
 * Created by CaoPengfei on 17/5/26.
 *
 * https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
 */

public class AboutActivity2 extends ToolBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(ResourceUtil.getString(R.string.about));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about2;
    }
}
