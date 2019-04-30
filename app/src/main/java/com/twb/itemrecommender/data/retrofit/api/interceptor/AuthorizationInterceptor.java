package com.twb.itemrecommender.data.retrofit.api.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {

    private static final String API_KEY_HEADER = "SmartTours-Api-Key";
    private static final String API_KEY = "fHPCFQzuCDuT3Kah";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader(API_KEY_HEADER, API_KEY);
        return chain.proceed(builder.build());
    }
}
