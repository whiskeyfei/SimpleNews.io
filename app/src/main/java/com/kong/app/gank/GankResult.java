package com.kong.app.gank;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by CaoPengfei on 17/8/2.
 */

class GankResult {

    public boolean error;
    public ResultsBean results;
    public List<String> category;

    public static class ResultsBean {

        @SerializedName("Android")
        public List<Gank> androidList;
        @SerializedName("休息视频")
        public List<Gank> 休息视频List;
        @SerializedName("iOS")
        public List<Gank> iOSList;
        @SerializedName("福利")
        public List<Gank> 妹纸List;
        @SerializedName("拓展资源")
        public List<Gank> 拓展资源List;
        @SerializedName("瞎推荐")
        public List<Gank> 瞎推荐List;
        @SerializedName("App")
        public List<Gank> appList;
        @SerializedName("前端")
        public List<Gank> web;


        @Override
        public String toString() {
            return "ResultsBean{" +
                    "androidList=" + androidList + "\n" +
                    ", 休息视频List=" + 休息视频List + "\n" +
                    ", iOSList=" + iOSList + "\n" +
                    ", 妹纸List=" + 妹纸List + "\n" +
                    ", 拓展资源List=" + 拓展资源List + "\n" +
                    ", 瞎推荐List=" + 瞎推荐List + "\n" +
                    ", appList=" + appList +"\n" +
                    ", web=" + web +"\n" +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GankResult{" +
                "error=" + error +
                ", results=" + results +
                ", category=" + category +
                '}';
    }
}
