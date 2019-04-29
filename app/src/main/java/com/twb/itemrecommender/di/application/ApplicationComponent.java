package com.twb.itemrecommender.di.application;

import com.google.gson.Gson;
import com.twb.itemrecommender.di.application.network.qualifiers.Unauthenticated;
import com.twb.itemrecommender.feature.product.ProductListViewModel;

import dagger.Component;
import okhttp3.OkHttpClient;

@Component(modules = {
        RepositoryModule.class
})
@ApplicationScope
public interface ApplicationComponent {

    /*
     * Activities
     */
//    void inject(ProductListActivity entryPointActivity);

    /*
     * ViewModels
     */
    void inject(ProductListViewModel productListViewModel);

    /*
     * AsyncTasks
     */
//    void inject(LoginAsyncTask loginAsyncTask);


    /*
     * Public facing objects
     */
    Gson getGson();

    @Unauthenticated
    OkHttpClient unauthenticatedOkHttpClient();

}
