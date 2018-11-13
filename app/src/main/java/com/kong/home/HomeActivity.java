package com.kong.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.kong.R;
import com.kong.home.tab.HomeFactory;
import com.kong.home.tab.adapter.BottomTabAdapter;
import com.kong.home.tab.adapter.TabViewPagerAdapter;
import com.kong.home.tab.widget.BottomTabLayout;
import com.kong.home.tab.widget.TabViewPager;
import com.kong.lib.BaseActivity;
import com.kong.lib.event.AppExitEvent;
import com.kong.lib.utils.DoubleTool;
import com.kong.lib.utils.SToast;

import org.greenrobot.eventbus.EventBus;


public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    private TabViewPager mViewPager;
    private BottomTabLayout mBottomTabLayout;
    private BottomTabAdapter mBottomTabAdapter;
    private DoubleTool mDoubleTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mViewPager = (TabViewPager) findViewById(R.id.home_viewpager);
        mViewPager.setScrollEnabled(false);
        mBottomTabLayout = (BottomTabLayout) findViewById(R.id.home_tablayout);
        mBottomTabAdapter = new BottomTabAdapter(getApplicationContext(), HomeFactory.mTabItems);
        mViewPager.setAdapter(new TabViewPagerAdapter(getSupportFragmentManager(), HomeFactory.mTabFragment));
        mViewPager.setOffscreenPageLimit(HomeFactory.mTabFragment.size());
        mBottomTabLayout.setAdapter(mBottomTabAdapter);
        mBottomTabLayout.setViewPager(mViewPager);
        mDoubleTool = new DoubleTool();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() != KeyEvent.ACTION_UP) {
            return super.dispatchKeyEvent(event);
        }
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE) {
            if (getDoubleTool().doubleClickKeyEvent()) {
                SToast.makeText(HomeActivity.this, R.string.back_again_exit, Toast.LENGTH_SHORT).show();
                return true;
            } else {
                EventBus.getDefault().post(new AppExitEvent());
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private DoubleTool getDoubleTool() {
        if (mDoubleTool == null) {
            mDoubleTool = new DoubleTool();
        }
        return mDoubleTool;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDoubleTool = null;
    }
}
