//package com.lauren.simplenews.utils;
//
///**
// * Description :
// * Author : lauren
// * Email  : lauren.liuling@gmail.com
// * Blog   : http://www.liuling123.com
// * Date   : 15/12/19
// */
//public class NewsJsonUtils {
//
//    private final static String TAG = "NewsJsonUtils";
//
////    public static List<NewsBean> readJsonNewsBeans(String res, String value) {
////        JsonParser parser = new JsonParser();
////        JsonObject jsonObj = parser.parse(res).getAsJsonObject();
////        JsonElement jsonElement = jsonObj.get(value);
////        if (jsonElement == null) {
////            return null;
////        }
////        JsonArray jsonArray = jsonElement.getAsJsonArray();
////        Log.d(TAG, "jsonArray :" + jsonArray);
////        return new Gson().fromJson(jsonArray, new TypeToken<List<NewsBean>>() {}.getType());
////    }
//
////    public static NewsDetailBean readJsonNewsDetailBeans(String res, String docId) {
////        NewsDetailBean newsDetailBean = null;
////        try {
////            JsonParser parser = new JsonParser();
////            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
////            JsonElement jsonElement = jsonObj.get(docId);
////            if(jsonElement == null) {
////                return null;
////            }
////            newsDetailBean = GsonJsonUtils.deserialize(jsonElement.getAsJsonObject(), NewsDetailBean.class);
////        } catch (Exception e) {
////            LogUtils.e(TAG, "readJsonNewsBeans error" , e);
////        }
////        return newsDetailBean;
////    }
//
//}
