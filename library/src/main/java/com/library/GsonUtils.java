package com.library;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

public class GsonUtils {

    private static Gson mGson = new Gson();

    public static <T> T deserialize(String json, Type type) throws JsonSyntaxException {
        return mGson.fromJson(json, type);
    }

}
