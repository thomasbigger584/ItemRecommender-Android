package com.twb.itemrecommender.di.application.network;

import com.twb.itemrecommender.data.retrofit.dao.PostService;
import com.twb.itemrecommender.di.application.ApplicationScope;
import com.twb.itemrecommender.di.application.network.qualifiers.Authenticated;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class RetrofitApiModule {

    @Provides
    @ApplicationScope
    public PostService postService(@Authenticated Retrofit retrofit) {
        return retrofit.create(PostService.class);
    }
}
