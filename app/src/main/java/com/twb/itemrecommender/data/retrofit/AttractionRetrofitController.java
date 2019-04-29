package com.twb.itemrecommender.data.retrofit;

import com.twb.itemrecommender.data.domain.Attraction;
import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.data.retrofit.api.ErrorParser;
import com.twb.itemrecommender.data.retrofit.dao.AttractionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class AttractionRetrofitController {

    private final AttractionService attractionService;

    public AttractionRetrofitController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    public DataWrapper<List<Attraction>> getAll(Map<String, Integer> paginationParams) {
        Call<List<Attraction>> postCall = attractionService.getAll(paginationParams);
        List<Attraction> attractionList = new ArrayList<>();
        DataWrapper<List<Attraction>> listDataWrapper = new DataWrapper<>();
        try {
            Response<List<Attraction>> postResponse = postCall.execute();
            if (postResponse.isSuccessful()) {
                List<Attraction> responseBody = postResponse.body();
                if (responseBody != null) {
                    attractionList.addAll(responseBody);
                    listDataWrapper.setData(attractionList);
                    return listDataWrapper;
                }
            }
            ErrorParser.parse(postResponse);
        } catch (Throwable e) {
            e.printStackTrace();
            listDataWrapper.setError(e);
        }
        return listDataWrapper;
    }
}
