package com.twb.itemrecommender.feature.util;

import android.content.Context;

import java.util.Locale;

public class LocationUtil {

    private LocationUtil() {
    }

    public static Location getSavedLocation(Context context) {
        Double latitude = SharedPrefsUtils.getDoublePreference(context, Constants.PREF_LATITUDE_KEY);
        Double longitude = SharedPrefsUtils.getDoublePreference(context, Constants.PREF_LONGITUDE_KEY);
        String address = SharedPrefsUtils.getStringPreference(context, Constants.PREF_ADDRESS_KEY);
        if (latitude == null || longitude == null) {
            latitude = Constants.DEFAULT_CENTER_LATITUDE;
            longitude = Constants.DEFAULT_CENTER_LONGITUDE;
            address = null;
        }
        return new Location(latitude, longitude, address);
    }

    public static class Location {
        private final double latitude;
        private final double longitude;
        private final String address;

        Location(double latitude, double longitude, String address) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.address = address;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getAddress() {
            return address;
        }

        public String getCoordinates() {
            return String.format(Locale.getDefault(), "(%f, %f)", this.latitude, this.longitude);
        }
    }
}
