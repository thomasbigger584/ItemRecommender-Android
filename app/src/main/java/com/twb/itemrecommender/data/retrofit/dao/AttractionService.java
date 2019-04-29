package com.twb.itemrecommender.data.retrofit.dao;

import com.twb.itemrecommender.data.domain.Attraction;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface AttractionService {

    @GET("api/ext-attraction")
    Call<List<Attraction>> getAll(@QueryMap(encoded = true) Map<String, Integer> queryParamsMap);
}
