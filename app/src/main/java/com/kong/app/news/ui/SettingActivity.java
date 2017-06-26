package com.kong.app.news.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.kong.R;
import com.kong.app.news.base.ThemeActivity;
import com.kong.app.news.event.ThemeChangedEvent;
import com.kong.app.news.fragment.SettingFragment;
import com.kong.app.news.utils.SettingsUtil;
import com.library.utils.ResourceUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by CaoPengfei on 17/6/17.
 */

public class SettingActivity extends ThemeActivity implements ColorChooserDialog.ColorCallback{

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        mToolbar.setTitle(ResourceUtil.getString(R.string.settings));
        initToolBar(mToolbar);
        replace();
    }

    public static int getThemeColor(Context context, int attrRes) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{attrRes});
        int color = typedArray.getColor(0, 0xffffff);
        typedArray.recycle();
        return color;
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        if (selectedColor == getThemeColor(this, R.attr.colorPrimary))
            return;
        mToolbar.setBackgroundColor(selectedColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(selectedColor);
        }
        if (selectedColor == ResourceUtil.getColor(R.color.lapis_blue)) {
            setTheme(R.style.LapisBlueTheme);
            SettingsUtil.setThemeIndex(0);
        } else if (selectedColor == ResourceUtil.getColor(R.color.pale_dogwood)) {
            setTheme(R.style.PaleDogwoodTheme);
            SettingsUtil.setThemeIndex(1);
        } else if (selectedColor == ResourceUtil.getColor(R.color.greenery)) {
            setTheme(R.style.GreeneryTheme);
            SettingsUtil.setThemeIndex(2);
        } else if (selectedColor == ResourceUtil.getColor(R.color.primrose_yellow)) {
            setTheme(R.style.PrimroseYellowTheme);
            SettingsUtil.setThemeIndex(3);
        } else if (selectedColor == ResourceUtil.getColor(R.color.flame)) {
            setTheme(R.style.FlameTheme);
            SettingsUtil.setThemeIndex(4);
        } else if (selectedColor == ResourceUtil.getColor(R.color.island_paradise)) {
            setTheme(R.style.IslandParadiseTheme);
            SettingsUtil.setThemeIndex(5);
        } else if (selectedColor == ResourceUtil.getColor(R.color.kale)) {
            setTheme(R.style.KaleTheme);
            SettingsUtil.setThemeIndex(6);
        } else if (selectedColor == getResources().getColor(R.color.pink_yarrow)) {
            setTheme(R.style.PinkYarrowTheme);
            SettingsUtil.setThemeIndex(7);
        } else if (selectedColor == ResourceUtil.getColor(R.color.niagara)) {
            setTheme(R.style.NiagaraTheme);
            SettingsUtil.setThemeIndex(8);
        }
        EventBus.getDefault().post(new ThemeChangedEvent(selectedColor));
        replace();
    }

    private void replace(){
        getFragmentManager().beginTransaction().replace(R.id.settings_content, SettingFragment.newInstance()).commit();
    }
}
