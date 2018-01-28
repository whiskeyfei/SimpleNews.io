package com.kong.app.news.commons;

/**
 * Created by whiskeyfei on 16-1-27.
 */
public class ApiConstants {

    // 图片
    public static final String IMAGES_URL = "http://api.laifudao.com/open/tupian.json";
    public static final String BLOGS_URL = "http://doraemonyu.me/feed0.json";

    public static final int MAX_PAGE = 10;

    private static final String HOST = "http://api.tianapi.com/";

    //微信精选
    public static final String HOST_WEIXIN = HOST + "wxnew/?key=d3fdafe27d8188e1a867d3f62f08c24d&num=20";

    //科技新闻数据
    public static final String HOST_KEJI = HOST + "startup/?key=d3fdafe27d8188e1a867d3f62f08c24d&num=20";

    //娱乐花边数据
    public static final String HOST_YULE = HOST + "huabian/?key=d3fdafe27d8188e1a867d3f62f08c24d&num=20";

    //健康资讯数据
    public static final String HOST_JIANKANG = HOST + "health/?key=d3fdafe27d8188e1a867d3f62f08c24d&num=20";

    //体育新闻
    public static final String HOST_SPORTS = HOST + "tiyu/?key=d3fdafe27d8188e1a867d3f62f08c24d&num=20";
}
