package com.twb.itemrecommender.data;

import com.twb.itemrecommender.data.domain.Attraction;
import com.twb.itemrecommender.data.domain.AttractionPurchase;
import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.data.retrofit.AttractionRetrofitController;
import com.twb.itemrecommender.data.retrofit.dao.AttractionService;
import com.twb.itemrecommender.feature.util.LocationUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttractionRepository {

    private final AttractionRetrofitController attractionRetrofitController;

    public AttractionRepository(AttractionRetrofitController attractionRetrofitController) {
        this.attractionRetrofitController = attractionRetrofitController;
    }

    public DataWrapper<List<Attraction>> getAll(int page, int count) {
        Map<String, Integer> paginationParams = new HashMap<>();
        paginationParams.put("page", page);
        paginationParams.put("size", count);
        DataWrapper<List<Attraction>> listDataWrapper =
                attractionRetrofitController.getAll(paginationParams);
        if (listDataWrapper.isPresent()) {
            List<Attraction> attractionList = listDataWrapper.getData();
//            do whatever
        }
        return listDataWrapper;
    }

    public DataWrapper<List<Attraction>> getAllByLocation(LocationUtil.Location location, int page, int count) {
        Map<String, Double> locationParams = new HashMap<>();
        locationParams.put("latitude", location.getLatitude());
        locationParams.put("longitude", location.getLongitude());

        Map<String, Integer> paginationParams = new HashMap<>();
        paginationParams.put("page", page);
        paginationParams.put("size", count);

        DataWrapper<List<Attraction>> listDataWrapper =
                attractionRetrofitController.getAllByLocation(locationParams, paginationParams);
        if (listDataWrapper.isPresent()) {
            List<Attraction> attractionList = listDataWrapper.getData();
            for (Attraction attraction : attractionList) {
                updateAttraction(attraction);
            }
        }
        return listDataWrapper;
    }

    private void updateAttraction(Attraction attraction) {
        BigDecimal distanceBigDecimal = new BigDecimal(attraction.getDistance());
        distanceBigDecimal = distanceBigDecimal.setScale(2, RoundingMode.CEILING);
        attraction.setDistanceBigDecimal(distanceBigDecimal);
    }

    public DataWrapper<AttractionPurchase> takeInterest(Long attractionId, String traveling, String activity, Double userDistance, LocationUtil.Location location) {
        AttractionService.RegisterInterestRequest request =
                new AttractionService.RegisterInterestRequest(attractionId, traveling, activity, userDistance, location);
        DataWrapper<AttractionPurchase> dataWrapper =
                attractionRetrofitController.takeInterest(request);
        if (dataWrapper.isPresent()) {
            AttractionPurchase responseData = dataWrapper.getData();
        }
        return dataWrapper;
    }

    public DataWrapper<AttractionPurchase> takeAction(Long attractionPurchaseId) {
        AttractionService.TakeActionRequest takeActionRequest =
                new AttractionService.TakeActionRequest(attractionPurchaseId);
        DataWrapper<AttractionPurchase> dataWrapper =
                attractionRetrofitController.takeAction(takeActionRequest);
        if (dataWrapper.isPresent()) {
            AttractionPurchase responseData = dataWrapper.getData();
        }
        return dataWrapper;
    }

}
