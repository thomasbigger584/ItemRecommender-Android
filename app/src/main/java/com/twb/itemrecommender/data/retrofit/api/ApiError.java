package com.twb.itemrecommender.data.retrofit.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiError {

    @SerializedName("cause")
    @Expose
    private Object cause;
    @SerializedName("stackTrace")
    @Expose
    private List<StackTrace> stackTrace = new ArrayList<>();
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("instance")
    @Expose
    private Object instance;
    @SerializedName("parameters")
    @Expose
    private Parameters parameters;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("localizedMessage")
    @Expose
    private String localizedMessage;

    public Object getCause() {
        return cause;
    }

    public void setCause(Object cause) {
        this.cause = cause;
    }

    public List<StackTrace> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<StackTrace> stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }
}
