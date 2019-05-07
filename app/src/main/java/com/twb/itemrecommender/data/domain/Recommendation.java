package com.twb.itemrecommender.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recommendation implements Parcelable {
    public static final Parcelable.Creator<Recommendation> CREATOR = new Parcelable.Creator<Recommendation>() {
        @Override
        public Recommendation createFromParcel(Parcel source) {
            return new Recommendation(source);
        }

        @Override
        public Recommendation[] newArray(int size) {
            return new Recommendation[size];
        }
    };
    @SerializedName("index")
    @Expose
    private Long index;
    @SerializedName("itemId")
    @Expose
    private Long itemId;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("actionTaken")
    @Expose
    private Boolean actionTaken;
    @SerializedName("probability")
    @Expose
    private Double probability;
    @SerializedName("margin")
    @Expose
    private Double margin;
    @SerializedName("attraction")
    @Expose
    private Attraction attraction;

    public Recommendation() {
    }

    protected Recommendation(Parcel in) {
        this.index = (Long) in.readValue(Long.class.getClassLoader());
        this.itemId = (Long) in.readValue(Long.class.getClassLoader());
        this.distance = (Double) in.readValue(Double.class.getClassLoader());
        this.actionTaken = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.probability = (Double) in.readValue(Double.class.getClassLoader());
        this.margin = (Double) in.readValue(Double.class.getClassLoader());
        this.attraction = in.readParcelable(Attraction.class.getClassLoader());
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Boolean getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(Boolean actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.index);
        dest.writeValue(this.itemId);
        dest.writeValue(this.distance);
        dest.writeValue(this.actionTaken);
        dest.writeValue(this.probability);
        dest.writeValue(this.margin);
        dest.writeParcelable(this.attraction, flags);
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "index=" + index +
                ", itemId=" + itemId +
                ", distance=" + distance +
                ", actionTaken=" + actionTaken +
                ", probability=" + probability +
                ", margin=" + margin +
                ", attraction=" + attraction +
                '}';
    }
}
