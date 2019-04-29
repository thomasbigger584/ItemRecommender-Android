package com.twb.itemrecommender.di.application;


import com.twb.itemrecommender.data.PostRepository;
import com.twb.itemrecommender.data.retrofit.PostRetrofitController;
import com.twb.itemrecommender.di.application.network.RetrofitControllerModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        RetrofitControllerModule.class
})
public class RepositoryModule {

    @Provides
    @ApplicationScope
    public PostRepository postRepository(PostRetrofitController postRetrofitController) {
        return new PostRepository(postRetrofitController);
    }
}
