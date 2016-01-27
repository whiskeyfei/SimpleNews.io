package com.lauren.simplenews.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by whiskeyfei on 16-1-27.
 */
public class NewModel implements Serializable {

    @SerializedName("hottime")
    public String time;

    public String title;
    @SerializedName("description")
    public String digest;

    @SerializedName("picUrl")
    public String imageUrl;

    @SerializedName("url")
    public String newUrl;
}
