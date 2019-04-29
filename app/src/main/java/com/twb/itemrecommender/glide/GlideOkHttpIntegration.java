package com.twb.itemrecommender.glide;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.twb.itemrecommender.SmartToursApplication;

import java.io.InputStream;

import okhttp3.OkHttpClient;

@GlideModule
public class GlideOkHttpIntegration extends AppGlideModule {

    private final OkHttpClient okHttpClient;

    public GlideOkHttpIntegration() {
        okHttpClient = SmartToursApplication.
                getApplicationComponent().unauthenticatedOkHttpClient();
    }

    @Override
    /*
     * Set an OkHttpClient to the Glide instance
     */
    public void registerComponents(Context context, Glide glide, Registry registry) {
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(okHttpClient);
        registry.replace(GlideUrl.class, InputStream.class, factory);
    }
}
