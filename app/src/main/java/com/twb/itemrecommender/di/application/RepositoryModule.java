package com.twb.itemrecommender.di.application;


import com.twb.itemrecommender.data.AttractionRepository;
import com.twb.itemrecommender.data.retrofit.AttractionRetrofitController;
import com.twb.itemrecommender.di.application.network.RetrofitControllerModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        RetrofitControllerModule.class
})
public class RepositoryModule {

    @Provides
    @ApplicationScope
    public AttractionRepository attractionRepository(AttractionRetrofitController postRetrofitController) {
        return new AttractionRepository(postRetrofitController);
    }
}
