package com.baselib.bottom;

import android.support.annotation.DrawableRes;

/**
 * Item 所需接口
 */
public interface CustomTabEntity {

    /**
     * 定义Item title
     *
     * @return
     */
    String getTabTitle();

    /**
     * 定义 Item 选中态
     *
     * @return
     */
    @DrawableRes
    int getTabSelectedIcon();

    /**
     * 定义Item 默认态
     *
     * @return
     */
    @DrawableRes
    int getTabUnselectedIcon();
}