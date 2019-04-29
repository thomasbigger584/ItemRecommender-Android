package com.twb.itemrecommender.di.application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationContextModule {

    private final Context context;

    public ApplicationContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    public Context context() {
        return context;
    }
}
