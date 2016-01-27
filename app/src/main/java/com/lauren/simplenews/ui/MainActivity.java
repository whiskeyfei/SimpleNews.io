package com.lauren.simplenews.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fei.library.fragment.BaseFragment;
import com.fei.library.fragment.DPBaseFragment;
import com.fei.library.inter.IBaseFragmentEvent;
import com.lauren.simplenews.R;
import com.lauren.simplenews.fragment.AboutFragment;
import com.lauren.simplenews.fragment.ImageFragment;
import com.lauren.simplenews.fragment.NewsFragment;
import com.lauren.simplenews.fragment.WeatherFragment;
import com.lauren.simplenews.presenter.IMainPresenter;
import com.lauren.simplenews.presenter.MainPresenterImpl;
import com.lauren.simplenews.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView,IBaseFragmentEvent {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private IMainPresenter mMainPresenter;
    private DPBaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

        mMainPresenter = new MainPresenterImpl(this);

        switch2News();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            switch2About();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mMainPresenter.switchNavigation(menuItem.getItemId());
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void switch2News() {
        onSwitchFragment(new NewsFragment(),null);
        mToolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void switch2Images() {
        onSwitchFragment(new ImageFragment(),null);
        mToolbar.setTitle(R.string.navigation_images);
    }

    @Override
    public void switch2Weather() {
        onSwitchFragment(new WeatherFragment(),null);
        mToolbar.setTitle(R.string.navigation_weather);
    }

    @Override
    public void switch2About() {
        onSwitchFragment(new AboutFragment(),null);
        mToolbar.setTitle(R.string.navigation_about);
    }

    private void switchFragment(BaseFragment fragment, Bundle bundle) {
        Log.e(TAG, "switchFragment() -> fragment:" + fragment.toString());
        if (mCurrentFragment == fragment) {
            Log.e(TAG, TAG + "---switchFragment() -> mCurrentFragment eques fragment");
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_content, fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onSwitchFragment(DPBaseFragment fragment, Bundle bundle) {
        switchFragment(fragment, bundle);
    }

    @Override
    public void onAttachActivity(DPBaseFragment fragment) {
        mCurrentFragment = fragment;
        Log.e(TAG, TAG + "---onAttachActivity()-> mCurrentFragment:"+mCurrentFragment.toString());
    }

    @Override
    public void onDetachActivity(DPBaseFragment fragment) {
        if (mCurrentFragment == fragment) {
            mCurrentFragment = null;
        }
    }
}
