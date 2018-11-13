package com.baselib.bottom;

/**
 * Tab 选择监听接口
 */
public interface OnTabSelectListener {

    /**
     * 切换到新 Item
     *
     * @param position
     */
    void onTabSelect(int position);

    /**
     * 重复切当前 Item
     *
     * @param position
     */
    void onTabReselect(int position);
}