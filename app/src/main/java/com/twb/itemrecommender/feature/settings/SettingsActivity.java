package com.twb.itemrecommender.feature.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.maps.model.LatLng;
import com.schibstedspain.leku.LocationPickerActivity;
import com.schibstedspain.leku.LocationPickerActivityKt;
import com.schibstedspain.leku.locale.SearchZoneRect;
import com.twb.itemrecommender.R;
import com.twb.itemrecommender.feature.navigation.BaseNavigationActivity;
import com.twb.itemrecommender.feature.util.Constants;
import com.twb.itemrecommender.feature.util.LocationUtil;
import com.twb.itemrecommender.feature.util.SharedPrefsUtils;


public class SettingsActivity extends BaseNavigationActivity {

    private static final int MAPS_REQUEST_CODE = 1000;
    private TextView locationCoordTextView;
    private TextView locationAddressTextView;
    private TextView travelingWithTextView;
    private TextView activityTextView;

    @Override
    protected int getContentView() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Settings");
        }
        locationCoordTextView = findViewById(R.id.locationCoordTextView);
        locationAddressTextView = findViewById(R.id.locationAddressTextView);
        travelingWithTextView = findViewById(R.id.travelingWithTextView);
        activityTextView = findViewById(R.id.activityTextView);
        setLocationUi();
        setTravelingWithUi();
        setActivityUi();
    }

    public void onLocationChangeClick(View view) {
        LocationUtil.Location location = LocationUtil.getSavedLocation(this);
        Intent intent = new LocationPickerActivity.Builder()
                .withLocation(location.getLatitude(), location.getLongitude())
                .withGeolocApiKey(getString(R.string.google_maps_key))
                .withSearchZone(new SearchZoneRect(
                        new LatLng(Constants.SOUTH_WEST_LAT_COORD, Constants.SOUTH_WEST_LONG_COORD),
                        new LatLng(Constants.NORTH_EAST_LAT_COORD, Constants.NORTH_EAST_LONG_COORD)))
                .withDefaultLocaleSearchZone()
                .shouldReturnOkOnBackPressed()
                .withGooglePlacesEnabled()
                .withGoogleTimeZoneEnabled()
                .withVoiceSearchHidden()
                .withUnnamedRoadHidden()
                .build(this);
        startActivityForResult(intent, MAPS_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == MAPS_REQUEST_CODE) {
                double latitude = data.getDoubleExtra(LocationPickerActivityKt.LATITUDE, 0d);
                double longitude = data.getDoubleExtra(LocationPickerActivityKt.LONGITUDE, 0d);
                String address = data.getStringExtra(LocationPickerActivityKt.LOCATION_ADDRESS);

                SharedPrefsUtils.setDoublePreference(this, Constants.PREF_LATITUDE_KEY, latitude);
                SharedPrefsUtils.setDoublePreference(this, Constants.PREF_LONGITUDE_KEY, longitude);
                SharedPrefsUtils.setStringPreference(this, Constants.PREF_ADDRESS_KEY, address);

                setLocationUi();
            }
        }
    }

    public void onTravelingChangeClick(View view) {
        new AlertDialog.Builder(this).
                setTitle("Traveling with...").
                setSingleChoiceItems(Constants.TRAVELING_ANSWERS, getTravelingCheckedItem(), (dialogInterface, i) -> {
                    String chosenTraveling = Constants.TRAVELING_ANSWERS[i];
                    SharedPrefsUtils.setStringPreference(this, Constants.PREF_TRAVELING_KEY, chosenTraveling);
                    setTravelingWithUi();
                    dialogInterface.dismiss();
                }).show();
    }

    private int getTravelingCheckedItem() {
        String traveling = SharedPrefsUtils.getStringPreference(this, Constants.PREF_TRAVELING_KEY);
        if (traveling != null) {
            for (int index = 0; index < Constants.TRAVELING_ANSWERS.length; index++) {
                String thisTraveling = Constants.TRAVELING_ANSWERS[index];
                if (traveling.equals(thisTraveling)) {
                    return index;
                }
            }
        }
        return 0;
    }

    public void onActivityChangeClick(View view) {
        new AlertDialog.Builder(this).
                setTitle("Activity type...").
                setSingleChoiceItems(Constants.ACTIVITY_ANSWERS, getActivityCheckedItem(), (dialogInterface, i) -> {
                    String chosenActivity = Constants.ACTIVITY_ANSWERS[i];
                    SharedPrefsUtils.setStringPreference(this, Constants.PREF_ACTIVITY_KEY, chosenActivity);
                    setActivityUi();
                    dialogInterface.dismiss();
                }).show();
    }

    private int getActivityCheckedItem() {
        String activity = SharedPrefsUtils.getStringPreference(this, Constants.PREF_ACTIVITY_KEY);
        if (activity != null) {
            for (int index = 0; index < Constants.ACTIVITY_ANSWERS.length; index++) {
                String thisActivity = Constants.ACTIVITY_ANSWERS[index];
                if (activity.equals(thisActivity)) {
                    return index;
                }
            }
        }
        return 0;
    }

    /*
     * Setting UI
     */
    private void setLocationUi() {
        LocationUtil.Location location = LocationUtil.getSavedLocation(this);
        locationCoordTextView.setText(location.getCoordinates());
        String address = location.getAddress();
        if (address != null) {
            locationAddressTextView.setText(address);
            locationAddressTextView.setVisibility(View.VISIBLE);
        } else {
            locationAddressTextView.setVisibility(View.GONE);
        }
    }

    private void setTravelingWithUi() {
        String traveling = SharedPrefsUtils.getStringPreference(this, Constants.PREF_TRAVELING_KEY);
        travelingWithTextView.setText(traveling);
    }

    private void setActivityUi() {
        String traveling = SharedPrefsUtils.getStringPreference(this, Constants.PREF_ACTIVITY_KEY);
        activityTextView.setText(traveling);
    }
}
