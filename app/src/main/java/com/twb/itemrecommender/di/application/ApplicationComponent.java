package com.twb.itemrecommender.di.application;

import com.google.gson.Gson;

import dagger.Component;

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
//    void inject(EntryPointViewModel entryPointViewModel);

    /*
     * AsyncTasks
     */
//    void inject(LoginAsyncTask loginAsyncTask);

    /*
     * Public facing objects
     */
    Gson getGson();

}
