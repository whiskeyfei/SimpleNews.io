package com.lauren.simplenews.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by whiskeyfei on 16-1-27.
 */
public class NewResultModel implements Serializable {

    @SerializedName("code")
    public int resultCode;

    @SerializedName("msg")
    public String resultMsg;

    @SerializedName("newslist")
    public List<NewModel> newModellist;

}
