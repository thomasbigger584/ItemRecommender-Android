package com.twb.itemrecommender.data.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttractionPurchase {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("traveling")
    @Expose
    private String traveling;
    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("userDistance")
    @Expose
    private Double userDistance;
    @SerializedName("userLatitude")
    @Expose
    private Double userLatitude;
    @SerializedName("userLongitude")
    @Expose
    private Double userLongitude;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("actionTakenAt")
    @Expose
    private Object actionTakenAt;
    @SerializedName("actionTaken")
    @Expose
    private Boolean actionTaken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraveling() {
        return traveling;
    }

    public void setTraveling(String traveling) {
        this.traveling = traveling;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Double getUserDistance() {
        return userDistance;
    }

    public void setUserDistance(Double userDistance) {
        this.userDistance = userDistance;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getActionTakenAt() {
        return actionTakenAt;
    }

    public void setActionTakenAt(Object actionTakenAt) {
        this.actionTakenAt = actionTakenAt;
    }

    public Boolean getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(Boolean actionTaken) {
        this.actionTaken = actionTaken;
    }
}
