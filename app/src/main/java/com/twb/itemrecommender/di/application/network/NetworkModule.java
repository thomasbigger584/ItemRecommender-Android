package com.twb.itemrecommender.di.application.network;

import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.twb.itemrecommender.BuildConfig;
import com.twb.itemrecommender.data.retrofit.api.interceptor.AuthorizationInterceptor;
import com.twb.itemrecommender.di.application.ApplicationContextModule;
import com.twb.itemrecommender.di.application.ApplicationScope;
import com.twb.itemrecommender.di.application.network.qualifiers.Authenticated;
import com.twb.itemrecommender.di.application.network.qualifiers.Unauthenticated;

import java.io.File;
import java.util.Date;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


@Module(includes = {ApplicationContextModule.class})
public class NetworkModule {

    private static final String BASE_URL = "http://192.168.0.101:8080/";
    private static final String PREFIX = "api/";

    private static final String OKHTTP_CACHE_FILE = "okhttp_cache";
    private static final int MAX_CACHE_SIZE = 10 * 1000 * 1000; //10MB Cache

    @Provides
    @Unauthenticated
    @ApplicationScope
    public Retrofit retrofitUnAuthenticated(@Unauthenticated OkHttpClient okHttpClient, Gson gson) {
        return getRetrofit(okHttpClient, gson);
    }

    @Provides
    @Unauthenticated
    @ApplicationScope
    public OkHttpClient okHttpClientUnAuthenticated(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return getOkHttpClientBuilder(loggingInterceptor, cache).build();
    }

    @Provides
    @Authenticated
    @ApplicationScope
    public Retrofit retrofitAuthenticated(@Authenticated OkHttpClient okHttpClient, Gson gson) {
        return getRetrofit(okHttpClient, gson);
    }

    @Provides
    @Authenticated
    @ApplicationScope
    public OkHttpClient okHttpClientAuthenticated(HttpLoggingInterceptor loggingInterceptor,
                                                  AuthorizationInterceptor authorizationInterceptor,
                                                  Cache cache) {
        OkHttpClient.Builder builder = getOkHttpClientBuilder(loggingInterceptor, cache);
        builder.addInterceptor(authorizationInterceptor);
        return builder.build();
    }

    @NonNull
    private OkHttpClient.Builder getOkHttpClientBuilder(HttpLoggingInterceptor loggingInterceptor,
                                                        Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().
                addInterceptor(loggingInterceptor)
                .cache(cache);
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        return builder;
    }

    @NonNull
    private Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create(gson)).
                client(okHttpClient).
                baseUrl(BASE_URL + PREFIX).
                build();
    }

    @Provides
    @ApplicationScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> {
            return new Date((long) (json.getAsJsonPrimitive().getAsDouble() * 1000));
        });
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor =
                new HttpLoggingInterceptor(message -> Timber.i(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    @ApplicationScope
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Provides
    @ApplicationScope
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, MAX_CACHE_SIZE);
    }

    @Provides
    @ApplicationScope
    public File cacheFile(Context context) {
        return new File(context.getCacheDir(), OKHTTP_CACHE_FILE);
    }
}
