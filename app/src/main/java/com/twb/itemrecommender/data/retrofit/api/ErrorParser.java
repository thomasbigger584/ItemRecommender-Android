package com.twb.itemrecommender.data.retrofit.api;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.twb.itemrecommender.SmartToursApplication;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ErrorParser {

    private ErrorParser() {

    }

    public static <T> void parse(Response<T> postResponse) throws Throwable {
        ResponseBody errorResponseBody = postResponse.errorBody();
        if (errorResponseBody != null) {
            String errorResponseBodyString = errorResponseBody.string();
            if (errorResponseBodyString != null && !errorResponseBodyString.equals("")) {
                try {
                    Gson gson = SmartToursApplication.getApplicationComponent().getGson();
                    ApiError apiError = gson.fromJson(errorResponseBodyString, ApiError.class);
                    throw new Throwable(apiError.getMessage());
                } catch (JsonSyntaxException e) {
                    throw new Throwable(errorResponseBodyString);
                }
            }
        }
        throw new Throwable("An error occurred");
    }
}
