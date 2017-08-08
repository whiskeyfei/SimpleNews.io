package com.kong.app.news.beans;

import java.io.Serializable;

/**
 * Created by CaoPengfei on 17/7/27.
 */

public class TabCategory implements Serializable {

    public String url;
    public String categoryName;
    public int type;

    public TabCategory(String url, String categoryName, int type) {
        this.url = url;
        this.categoryName = categoryName;
        this.type = type;
    }
}
