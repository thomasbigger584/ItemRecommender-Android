package com.twb.itemrecommender.data.retrofit.dao;

import com.twb.itemrecommender.data.domain.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PostService {

    @GET("post/livefeed")
    Call<List<Post>> getLiveFeedPosts(
            @QueryMap(encoded = true) Map<String, Integer> queryParamsMap);

}
