package com.kong.app.recomend;

import com.google.gson.annotations.SerializedName;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * Created by wuming on 19/6/7.
 */
public class Banner extends SimpleBannerInfo {

    @SerializedName("desc")
    public String desc;

    @SerializedName("id")
    public int id;

    @SerializedName("imagePath")
    public String imagePath;

    @SerializedName("isVisible")
    public int isVisible;

    @SerializedName("order")
    public int order;

    @SerializedName("title")
    public String title;

    @SerializedName("type")
    public int type;

    @SerializedName("url")
    public String url;

    @Override
    public String toString() {
        return "Banner{" +
                "desc='" + desc + '\'' +
                ", id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", isVisible=" + isVisible +
                ", order=" + order +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}'+"\n";
    }

    @Override
    public Object getXBannerUrl() {
        return imagePath;
    }

    @Override
    public String getXBannerTitle() {
        return title;
    }
}
