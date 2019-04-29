package com.twb.itemrecommender.data.retrofit.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Parameters {

    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("message")
    @Expose
    private String message;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
