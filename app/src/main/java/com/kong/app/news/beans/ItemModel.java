package com.kong.app.news.beans;

import java.io.Serializable;

/**
 * Created by CaoPengfei on 17/6/9.
 */

public class ItemModel implements Serializable {

    public static final int TYPE_NOTE = 0;
    public static final int TYPE_OTHERCLASS = 1;
    public int id = -1;
    public int type;
    public String name;
    public Class<?> cls;

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", cls=" + cls +
                '}';
    }
}
