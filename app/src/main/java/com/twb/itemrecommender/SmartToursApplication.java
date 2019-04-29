package com.twb.itemrecommender;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.twb.itemrecommender.di.application.ApplicationComponent;
import com.twb.itemrecommender.di.application.ApplicationContextModule;
import com.twb.itemrecommender.di.application.DaggerApplicationComponent;

import timber.log.Timber;


public class SmartToursApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context applicationContext;

    private ApplicationComponent applicationComponent;

    public static SmartToursApplication getApplication() {
        return (SmartToursApplication) applicationContext;
    }

    public static ApplicationComponent getApplicationComponent() {
        return getApplication().applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
         * Use stetho for integration with Chrome Developer Tools
         * else
         */
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        /*
         * Initialise Timber for logging
         */
        Timber.plant(new Timber.DebugTree());

        SmartToursApplication.applicationContext = getApplicationContext();

        /*
         * Setup the dagger component at the application level
         */
        applicationComponent = DaggerApplicationComponent.builder().
                applicationContextModule(new ApplicationContextModule(applicationContext)).
                build();
    }
}
