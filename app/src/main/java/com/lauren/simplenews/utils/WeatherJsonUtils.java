//package com.lauren.simplenews.utils;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.lauren.simplenews.beans.WeatherBean;
//import com.lauren.simplenews.beans.WeatherDay;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class WeatherJsonUtils {
//
//    /**
//     * 从定位的json字串中获取城市
//     *
//     * @param json
//     * @return
//     */
//    public static String getCity(String json) {
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
//        JsonElement status = jsonObj.get("status");
//        if (status != null && "OK".equals(status.getAsString())) {
//            JsonObject result = jsonObj.getAsJsonObject("result");
//            if (result != null) {
//                JsonObject addressComponent = result.getAsJsonObject("addressComponent");
//                if (addressComponent != null) {
//                    JsonElement cityElement = addressComponent.get("city");
//                    if (cityElement != null) {
//                        return cityElement.getAsString().replace("市", "");
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
////    public static List<WeatherBean> getWeatherInfo(String json) {
////        List<WeatherBean> list = new ArrayList<>();
////        if (TextUtils.isEmpty(json)) {
////            return list;
////        }
////        JsonParser parser = new JsonParser();
////        JsonObject jsonObj = parser.parse(json).getAsJsonObject();
////        String status = jsonObj.get("status").getAsString();
////        if ("1000".equals(status)) {
////            JsonArray jsonArray = jsonObj.getAsJsonObject("data").getAsJsonArray("forecast");
////            for (int i = 0; i < jsonArray.size(); i++) {
////                WeatherBean weatherBean = getWeatherBeanFromJson(jsonArray.get(i).getAsJsonObject());
////                list.add(weatherBean);
////            }
////        }
////        return list;
////    }
//
////    private static WeatherBean getWeatherBeanFromJson(JsonObject jsonObject) {
////
////        String temperature = jsonObject.get("high").getAsString() + " " + jsonObject.get("low").getAsString();
////        String weather = jsonObject.get("type").getAsString();
////        String wind = jsonObject.get("fengxiang").getAsString();
////        String date = jsonObject.get("date").getAsString();
////
////        WeatherBean weatherBean = new WeatherBean();
////
////        weatherBean.setDate(date);
////        weatherBean.setTemperature(temperature);
////        weatherBean.setWeather(weather);
////        weatherBean.setWeek(date.substring(date.length() - 3));
////        weatherBean.setWind(wind);
////        weatherBean.setImageRes(WeatherIconUtils.getWeatherImage(weather));
////        return weatherBean;
////    }
//
//
//    public static WeatherBean jsonToBean(WeatherDay model){
//        WeatherBean weatherBean = new WeatherBean();
//        weatherBean.setDate(model.getDate());
//        weatherBean.setTemperature(model.getHigh()+model.getLow());
//        weatherBean.setWeek(model.getDate().substring(model.getDate().length() - 3));
//        weatherBean.setWind(model.getFengxiang());
//        weatherBean.setImageRes(WeatherIconUtils.getWeatherImage(model.getType()));
//        return weatherBean;
//    }
//
//
//    public static List<WeatherBean> jsonToBeanList(List<WeatherDay> list){
//        List<WeatherBean> beanList = new ArrayList<>();
//        int size = list.size();
//        for(int i=0;i < size; i++){
//            WeatherDay model = list.get(i);
//            WeatherBean weatherBean = jsonToBean(model);
//            beanList.add(weatherBean);
//        }
//        return beanList;
//    }
//
//}
