package com.twb.itemrecommender.feature.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.schibstedspain.leku.LocationPickerActivity;
import com.schibstedspain.leku.LocationPickerActivityKt;
import com.schibstedspain.leku.locale.SearchZoneRect;
import com.twb.itemrecommender.R;
import com.twb.itemrecommender.feature.navigation.BaseNavigationActivity;
import com.twb.itemrecommender.feature.util.Constants;
import com.twb.itemrecommender.feature.util.SharedPrefsUtils;


public class SettingsActivity extends BaseNavigationActivity {

    private static final int MAPS_REQUEST_CODE = 1000;
    private TextView locationCoordTextView;
    private TextView locationAddressTextView;

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
        setLocationUi();
    }

    public void onLocationChangeClick(View view) {
        Double latitude = SharedPrefsUtils.getDoublePreference(this, Constants.PREF_LATITUDE_KEY);
        if (latitude == null) {
            latitude = Constants.DEFAULT_CENTER_LATITUDE;
        }
        Double longitude = SharedPrefsUtils.getDoublePreference(this, Constants.PREF_LONGITUDE_KEY);
        if (longitude == null) {
            longitude = Constants.DEFAULT_CENTER_LONGITUDE;
        }
        Intent intent = new LocationPickerActivity.Builder()
                .withLocation(latitude, longitude)
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

    public void onTravelingChangeClick(View view) {

        Toast.makeText(this, "Traveling", Toast.LENGTH_SHORT).show();
    }

    public void onActivityChangeClick(View view) {
        Toast.makeText(this, "Activity", Toast.LENGTH_SHORT).show();
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
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.d("RESULT****", "CANCELLED");
        }
    }

    private void setLocationUi() {
        Double latitude = SharedPrefsUtils.getDoublePreference(this, Constants.PREF_LATITUDE_KEY);
        if (latitude == null) {
            latitude = Constants.DEFAULT_CENTER_LATITUDE;
        }
        Double longitude = SharedPrefsUtils.getDoublePreference(this, Constants.PREF_LONGITUDE_KEY);
        if (longitude == null) {
            longitude = Constants.DEFAULT_CENTER_LONGITUDE;
        }
        locationCoordTextView.setText(String.format("(%f, %f)", latitude, longitude));
        String address = SharedPrefsUtils.getStringPreference(this, Constants.PREF_ADDRESS_KEY);
        if (address != null) {
            locationAddressTextView.setText(address);
            locationAddressTextView.setVisibility(View.VISIBLE);
        } else {
            locationAddressTextView.setVisibility(View.GONE);
        }
    }
}
