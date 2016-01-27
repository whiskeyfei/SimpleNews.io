package com.lauren.simplenews.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageBean implements Serializable {

    public static final long serialVersionUID = 1L;
    @SerializedName("title")
    public String title;

    @SerializedName("thumburl")
    public String thumbUrl;

    @SerializedName("sourceurl")
    public String imageUrl;

    @SerializedName("height")
    public int height;

    @SerializedName("width")
    public int width;
}
