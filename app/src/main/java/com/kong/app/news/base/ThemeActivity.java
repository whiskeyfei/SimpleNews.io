package com.kong.app.news.base;

import android.os.Bundle;

import com.kong.R;
import com.kong.app.news.utils.SettingsUtil;
import com.kong.lib.BaseActivity;

/**
 * Created by CaoPengfei on 17/6/17.
 * <p>
 * use change activity theme
 */

public class ThemeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initTheme();
    }

    private void initTheme() {
        int themeIndex = SettingsUtil.getThemeIndex();
        switch (themeIndex) {
            case 0:
                setTheme(R.style.LapisBlueTheme);
                break;
            case 1:
                setTheme(R.style.PaleDogwoodTheme);
                break;
            case 2:
                setTheme(R.style.GreeneryTheme);
                break;
            case 3:
                setTheme(R.style.PrimroseYellowTheme);
                break;
            case 4:
                setTheme(R.style.FlameTheme);
                break;
            case 5:
                setTheme(R.style.IslandParadiseTheme);
                break;
            case 6:
                setTheme(R.style.KaleTheme);
                break;
            case 7:
                setTheme(R.style.PinkYarrowTheme);
                break;
            case 8:
                setTheme(R.style.NiagaraTheme);
                break;
            case 9:
                setTheme(R.style.WhiteTheme);
                break;
        }
    }
}
