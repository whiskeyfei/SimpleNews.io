package com.kong.app.news.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by whiskeyfei on 16-1-27.
 */
public class NewModel implements Serializable {

    @SerializedName("ctime")
    public String time;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String digest;

    @SerializedName("picUrl")
    public String imageUrl;

    @SerializedName("url")
    public String newUrl;

    @Override
    public String toString() {
        return "NewModel{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", newUrl='" + newUrl  +
                '}'+ '\n';
    }
}
