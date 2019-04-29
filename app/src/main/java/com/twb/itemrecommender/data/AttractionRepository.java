package com.twb.itemrecommender.data;

import com.twb.itemrecommender.data.domain.Attraction;
import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.data.retrofit.AttractionRetrofitController;

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

}
