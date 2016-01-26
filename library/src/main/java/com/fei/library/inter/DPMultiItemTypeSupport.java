package com.fei.library.inter;

/**
 * Created by whiskeyfei on 15-9-6.
 * <p/>
 * 接口DPMultiItemTypeSupport,使用多种 Item 布局，进行设置即可
 */
public interface DPMultiItemTypeSupport<T> {

    /**
     * @param position item position
     * @param t  model
     * @return  layout id
     */
    int getLayoutId(int position, T t);

    /**
     * @return  type count int
     */
    int getViewTypeCount();

    /**
     * @param postion
     * @param t model
     * @return view type int
     */
    int getItemViewType(int postion, T t);
}
