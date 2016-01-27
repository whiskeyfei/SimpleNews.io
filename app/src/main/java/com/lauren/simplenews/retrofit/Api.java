package com.lauren.simplenews.retrofit;

import com.lauren.simplenews.beans.NewResultModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by whiskeyfei on 16-1-27.
 */
public interface Api {
    @GET("{type}/other/?key={key}&num={num}")
    Call<NewResultModel> getNewlist(@Path("type") String type, @Path("key") String key, @Path("num") int num);
}
