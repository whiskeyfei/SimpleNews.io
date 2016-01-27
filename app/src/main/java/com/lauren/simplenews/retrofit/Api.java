package com.lauren.simplenews.retrofit;

import com.lauren.simplenews.beans.NewsBean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by whiskeyfei on 16-1-27.
 */
public interface Api {
    @GET("{type}/{id}/{index}-{pagesize}.html")
    Call<NewsBean> getData(@Path("type") String type, @Path("id") int id, @Path("index") int index, @Path("pagesize") int pagesize);
}
