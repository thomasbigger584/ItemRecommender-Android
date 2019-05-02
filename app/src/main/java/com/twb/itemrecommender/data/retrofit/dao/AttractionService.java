package com.twb.itemrecommender.data.retrofit.dao;

import androidx.annotation.Keep;

import com.twb.itemrecommender.data.domain.Attraction;
import com.twb.itemrecommender.data.domain.AttractionPurchase;
import com.twb.itemrecommender.feature.util.LocationUtil;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;

public interface AttractionService {

    @GET("ext-attraction")
    Call<List<Attraction>> getAll(@QueryMap(encoded = true) Map<String, Integer> paginationParams);

    @GET("ext-attraction/by-location")
    Call<List<Attraction>> getAllByLocation(@QueryMap(encoded = true) Map<String, Double> locationParamsMap, @QueryMap(encoded = true) Map<String, Integer> paginationParams);

    @POST("ext-attraction-purchase/interest")
    Call<AttractionPurchase> takeInterest(@Body RegisterInterestRequest registerInterestRequest);

    @PUT("ext-attraction-purchase/take-action")
    Call<AttractionPurchase> takeAction(@Body TakeActionRequest registerInterestRequest);

    @Keep
    class RegisterInterestRequest {
        Long attractionId;
        String traveling;
        String activity;
        Double userDistance;
        Double userLatitude;
        Double userLongitude;

        public RegisterInterestRequest(Long attractionId, String traveling, String activity, Double userDistance, LocationUtil.Location location) {
            this.attractionId = attractionId;
            this.traveling = traveling;
            this.activity = activity;
            this.userDistance = userDistance;
            this.userLatitude = location.getLatitude();
            this.userLongitude = location.getLongitude();
        }
    }

    @Keep
    class TakeActionRequest {
        Long attractionPurchaseId;

        public TakeActionRequest(Long attractionPurchaseId) {
            this.attractionPurchaseId = attractionPurchaseId;
        }
    }
}
