package com.twb.itemrecommender.feature.util;

public class Constants {

    public static final String[] TRAVELING_ANSWERS = new String[]{"KIDS", "ADULTS"};
    public static final String[] ACTIVITY_ANSWERS = new String[]{"GET ACTIVE", "RELAX"};

    public static final double SOUTH_WEST_LAT_COORD = 54.084530;
    public static final double SOUTH_WEST_LONG_COORD = -8.012903;
    public static final double NORTH_EAST_LAT_COORD = 55.244539;
    public static final double NORTH_EAST_LONG_COORD = -5.551857;

    static final double DEFAULT_CENTER_LATITUDE = (SOUTH_WEST_LAT_COORD + NORTH_EAST_LAT_COORD) / 2;
    static final double DEFAULT_CENTER_LONGITUDE = (SOUTH_WEST_LONG_COORD + NORTH_EAST_LONG_COORD) / 2;


    public static final String PREF_LATITUDE_KEY = "latitude";
    public static final String PREF_LONGITUDE_KEY = "longitude";
    public static final String PREF_ADDRESS_KEY = "address";
    public static final String PREF_TRAVELING_KEY = "traveling";
    public static final String PREF_ACTIVITY_KEY = "activity";

}
