package com.kong.app.recomend;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wuming on 19/6/7.
 */
public class BannerModel {

    @SerializedName("data")
    public List<Banner> mBanners;

    @SerializedName("errorCode")
    public int errorCode;

    @SerializedName("errorMsg")
    public String errorMsg;
}
