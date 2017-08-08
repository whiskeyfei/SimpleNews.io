package com.kong.home.tab;

/**
 * Created by CaoPengfei on 17/7/25.
 */

public class TabItemModel {
    public int drawable;
    public int checkedDrawable;
    public String text;
    public int type;

    public TabItemModel(int drawable, int checkedDrawable, String text) {
        this.drawable = drawable;
        this.checkedDrawable = checkedDrawable;
        this.text = text;
    }
}
