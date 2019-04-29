package com.twb.itemrecommender.di.application.network;


import com.twb.itemrecommender.data.retrofit.PostRetrofitController;
import com.twb.itemrecommender.data.retrofit.dao.PostService;
import com.twb.itemrecommender.di.application.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RetrofitApiModule.class})
public class RetrofitControllerModule {

    @Provides
    @ApplicationScope
    public PostRetrofitController postRetrofitController(PostService postService) {
        return new PostRetrofitController(postService);
    }

}
