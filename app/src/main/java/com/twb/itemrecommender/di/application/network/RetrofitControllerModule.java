package com.twb.itemrecommender.di.application.network;


import com.twb.itemrecommender.data.retrofit.AttractionRetrofitController;
import com.twb.itemrecommender.data.retrofit.dao.AttractionService;
import com.twb.itemrecommender.di.application.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RetrofitApiModule.class})
public class RetrofitControllerModule {

    @Provides
    @ApplicationScope
    public AttractionRetrofitController attractionRetrofitController(AttractionService attractionService) {
        return new AttractionRetrofitController(attractionService);
    }
}
