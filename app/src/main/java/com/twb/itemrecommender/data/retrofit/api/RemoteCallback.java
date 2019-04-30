package com.twb.itemrecommender.data.retrofit.api;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.twb.itemrecommender.SmartToursApplication;

import java.io.IOException;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class RemoteCallback<T> implements Callback<T> {

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        switch (response.code()) {
            case HttpURLConnection.HTTP_OK:
            case HttpURLConnection.HTTP_CREATED:
            case HttpURLConnection.HTTP_ACCEPTED:
            case HttpURLConnection.HTTP_NOT_AUTHORITATIVE: {
                if (response.body() != null) {
                    onSuccess(response.body());
                }
                break;
            }
            case HttpURLConnection.HTTP_UNAUTHORIZED: {
                Gson gson = SmartToursApplication.getApplicationComponent().getGson();
                try {
                    String errorJsonString = response.errorBody().string();
                    ApiError apiError = gson.fromJson(errorJsonString, ApiError.class);
                    onUnauthorized(apiError);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    onFailed(e);
                }
                break;
            }
            case HttpURLConnection.HTTP_NOT_FOUND: {
                onFailed(new Throwable("Not Found"));
                break;
            }
            default: {
                Gson gson = SmartToursApplication.getApplicationComponent().getGson();
                try {
                    ApiError apiError = gson.fromJson(response.errorBody().string(), ApiError.class);
                    onFailed(new Throwable(apiError.getMessage()));
                } catch (IOException e) {
                    e.printStackTrace();
                    onFailed(e);
                }
            }
        }
    }

    @Override
    public final void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        onFailed(t);
    }

    public abstract void onSuccess(T response);

    public abstract void onUnauthorized(ApiError error);

    public abstract void onFailed(Throwable throwable);
}
