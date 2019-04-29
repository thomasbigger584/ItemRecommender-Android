package com.twb.itemrecommender.data.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attraction implements Parcelable {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("sygicTravelId")
    @Expose
    private String sygicTravelId;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("marker")
    @Expose
    private String marker;
    @SerializedName("perex")
    @Expose
    private String perex;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;
    @SerializedName("categories")
    @Expose
    private String categories;
    @SerializedName("dsSummary")
    @Expose
    private String dsSummary;
    @SerializedName("dsIcon")
    @Expose
    private String dsIcon;
    @SerializedName("dsApparentTemperatureHigh")
    @Expose
    private Double dsApparentTemperatureHigh;
    @SerializedName("dsApparentTemperatureLow")
    @Expose
    private Double dsApparentTemperatureLow;
    @SerializedName("dsDewPoint")
    @Expose
    private Double dsDewPoint;
    @SerializedName("dsHumidity")
    @Expose
    private Double dsHumidity;
    @SerializedName("dsPressure")
    @Expose
    private Double dsPressure;
    @SerializedName("dsWindSpeed")
    @Expose
    private Double dsWindSpeed;
    @SerializedName("dsWindGust")
    @Expose
    private Double dsWindGust;
    @SerializedName("dsCloudCover")
    @Expose
    private Double dsCloudCover;
    @SerializedName("dsVisibility")
    @Expose
    private Long dsVisibility;
    @SerializedName("adultPrice")
    @Expose
    private Double adultPrice;
    @SerializedName("childPrice")
    @Expose
    private Double childPrice;
    @SerializedName("accessible")
    @Expose
    private Boolean accessible;
    @SerializedName("facilities")
    @Expose
    private Boolean facilities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSygicTravelId() {
        return sygicTravelId;
    }

    public void setSygicTravelId(String sygicTravelId) {
        this.sygicTravelId = sygicTravelId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getPerex() {
        return perex;
    }

    public void setPerex(String perex) {
        this.perex = perex;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getDsSummary() {
        return dsSummary;
    }

    public void setDsSummary(String dsSummary) {
        this.dsSummary = dsSummary;
    }

    public String getDsIcon() {
        return dsIcon;
    }

    public void setDsIcon(String dsIcon) {
        this.dsIcon = dsIcon;
    }

    public Double getDsApparentTemperatureHigh() {
        return dsApparentTemperatureHigh;
    }

    public void setDsApparentTemperatureHigh(Double dsApparentTemperatureHigh) {
        this.dsApparentTemperatureHigh = dsApparentTemperatureHigh;
    }

    public Double getDsApparentTemperatureLow() {
        return dsApparentTemperatureLow;
    }

    public void setDsApparentTemperatureLow(Double dsApparentTemperatureLow) {
        this.dsApparentTemperatureLow = dsApparentTemperatureLow;
    }

    public Double getDsDewPoint() {
        return dsDewPoint;
    }

    public void setDsDewPoint(Double dsDewPoint) {
        this.dsDewPoint = dsDewPoint;
    }

    public Double getDsHumidity() {
        return dsHumidity;
    }

    public void setDsHumidity(Double dsHumidity) {
        this.dsHumidity = dsHumidity;
    }

    public Double getDsPressure() {
        return dsPressure;
    }

    public void setDsPressure(Double dsPressure) {
        this.dsPressure = dsPressure;
    }

    public Double getDsWindSpeed() {
        return dsWindSpeed;
    }

    public void setDsWindSpeed(Double dsWindSpeed) {
        this.dsWindSpeed = dsWindSpeed;
    }

    public Double getDsWindGust() {
        return dsWindGust;
    }

    public void setDsWindGust(Double dsWindGust) {
        this.dsWindGust = dsWindGust;
    }

    public Double getDsCloudCover() {
        return dsCloudCover;
    }

    public void setDsCloudCover(Double dsCloudCover) {
        this.dsCloudCover = dsCloudCover;
    }

    public Long getDsVisibility() {
        return dsVisibility;
    }

    public void setDsVisibility(Long dsVisibility) {
        this.dsVisibility = dsVisibility;
    }

    public Double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Double adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Double childPrice) {
        this.childPrice = childPrice;
    }

    public Boolean getAccessible() {
        return accessible;
    }

    public void setAccessible(Boolean accessible) {
        this.accessible = accessible;
    }

    public Boolean getFacilities() {
        return facilities;
    }

    public void setFacilities(Boolean facilities) {
        this.facilities = facilities;
    }

    public static final Parcelable.Creator<Attraction> CREATOR = new Parcelable.Creator<Attraction>() {
        @Override
        public Attraction createFromParcel(Parcel source) {
            return new Attraction(source);
        }

        @Override
        public Attraction[] newArray(int size) {
            return new Attraction[size];
        }
    };

    public Attraction() {
    }

    protected Attraction(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.sygicTravelId = in.readString();
        this.rating = (Double) in.readValue(Double.class.getClassLoader());
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.name = in.readString();
        this.marker = in.readString();
        this.perex = in.readString();
        this.thumbnailUrl = in.readString();
        this.categories = in.readString();
        this.dsSummary = in.readString();
        this.dsIcon = in.readString();
        this.dsApparentTemperatureHigh = (Double) in.readValue(Double.class.getClassLoader());
        this.dsApparentTemperatureLow = (Double) in.readValue(Double.class.getClassLoader());
        this.dsDewPoint = (Double) in.readValue(Double.class.getClassLoader());
        this.dsHumidity = (Double) in.readValue(Double.class.getClassLoader());
        this.dsPressure = (Double) in.readValue(Double.class.getClassLoader());
        this.dsWindSpeed = (Double) in.readValue(Double.class.getClassLoader());
        this.dsWindGust = (Double) in.readValue(Double.class.getClassLoader());
        this.dsCloudCover = (Double) in.readValue(Double.class.getClassLoader());
        this.dsVisibility = (Long) in.readValue(Long.class.getClassLoader());
        this.adultPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.childPrice = (Double) in.readValue(Double.class.getClassLoader());
        this.accessible = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.facilities = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.sygicTravelId);
        dest.writeValue(this.rating);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
        dest.writeString(this.name);
        dest.writeString(this.marker);
        dest.writeString(this.perex);
        dest.writeString(this.thumbnailUrl);
        dest.writeString(this.categories);
        dest.writeString(this.dsSummary);
        dest.writeString(this.dsIcon);
        dest.writeValue(this.dsApparentTemperatureHigh);
        dest.writeValue(this.dsApparentTemperatureLow);
        dest.writeValue(this.dsDewPoint);
        dest.writeValue(this.dsHumidity);
        dest.writeValue(this.dsPressure);
        dest.writeValue(this.dsWindSpeed);
        dest.writeValue(this.dsWindGust);
        dest.writeValue(this.dsCloudCover);
        dest.writeValue(this.dsVisibility);
        dest.writeValue(this.adultPrice);
        dest.writeValue(this.childPrice);
        dest.writeValue(this.accessible);
        dest.writeValue(this.facilities);
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "id=" + id +
                ", sygicTravelId='" + sygicTravelId + '\'' +
                ", rating=" + rating +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", marker='" + marker + '\'' +
                ", perex='" + perex + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", categories='" + categories + '\'' +
                ", dsSummary='" + dsSummary + '\'' +
                ", dsIcon='" + dsIcon + '\'' +
                ", dsApparentTemperatureHigh=" + dsApparentTemperatureHigh +
                ", dsApparentTemperatureLow=" + dsApparentTemperatureLow +
                ", dsDewPoint=" + dsDewPoint +
                ", dsHumidity=" + dsHumidity +
                ", dsPressure=" + dsPressure +
                ", dsWindSpeed=" + dsWindSpeed +
                ", dsWindGust=" + dsWindGust +
                ", dsCloudCover=" + dsCloudCover +
                ", dsVisibility=" + dsVisibility +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice +
                ", accessible=" + accessible +
                ", facilities=" + facilities +
                '}';
    }
}