package com.kong.home;

import android.os.Bundle;
import android.view.KeyEvent;

import com.kong.R;
import com.kong.app.news.event.ThemeChangedEvent;
import com.kong.home.tab.HomeFactory;
import com.kong.home.tab.adapter.BottomTabAdapter;
import com.kong.home.tab.adapter.MyViewPagerAdapter;
import com.kong.home.tab.widget.BottomTabLayout;
import com.kong.home.tab.widget.TabViewPager;
import com.library.BaseActivity;
import com.library.event.AppExitEvent;
import com.library.utils.ToolsUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class HomeActivity extends BaseActivity {

    private TabViewPager mViewPager;
    private BottomTabLayout mBottomTabLayout;
    private BottomTabAdapter mBottomTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mViewPager = (TabViewPager) findViewById(R.id.home_viewpager);
        mViewPager.setScrollEnabled(false);
        mBottomTabLayout = (BottomTabLayout) findViewById(R.id.home_tablayout);
        mBottomTabAdapter = new BottomTabAdapter(getApplicationContext(), HomeFactory.mTabItems);
        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), HomeFactory.mTabFragment));
        mBottomTabLayout.setAdapter(mBottomTabAdapter);
        mBottomTabLayout.setViewPager(mViewPager);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThemeChanged(ThemeChangedEvent event) {
        this.recreate();
    }

    private long firstBackPressedTime = 0;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() != KeyEvent.ACTION_UP) {
            return super.dispatchKeyEvent(event);
        }
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE) {
            long secondBackPressedTime = System.currentTimeMillis();
            if (secondBackPressedTime - firstBackPressedTime > 2000) {
                ToolsUtil.showToast(R.string.back_again_exit);
                firstBackPressedTime = secondBackPressedTime;
                return true;
            } else {
                EventBus.getDefault().post(new AppExitEvent());
                return super.dispatchKeyEvent(event);
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
