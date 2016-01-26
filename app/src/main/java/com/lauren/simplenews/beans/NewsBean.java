package com.lauren.simplenews.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Description : 新闻实体类
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/19
 */
public class NewsBean implements Serializable {

    public String docid;

    public String title;

    @SerializedName("digest")
    public String digest;

    @SerializedName("imgsrc")
    public String imageUrl;

    public String source;

    @SerializedName("ptime")
    public String time;

    @SerializedName("TAG")
    public String tag;
}
