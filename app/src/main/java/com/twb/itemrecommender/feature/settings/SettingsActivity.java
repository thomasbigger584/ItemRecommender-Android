package com.twb.itemrecommender.feature.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.schibstedspain.leku.LocationPickerActivity;
import com.schibstedspain.leku.LocationPickerActivityKt;
import com.schibstedspain.leku.locale.SearchZoneRect;
import com.twb.itemrecommender.R;
import com.twb.itemrecommender.feature.navigation.BaseNavigationActivity;

import timber.log.Timber;

import static com.schibstedspain.leku.LocationPickerActivityKt.LATITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.LONGITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.ZIPCODE;

public class SettingsActivity extends BaseNavigationActivity {

    private static final int MAPS_REQUEST_CODE = 1000;

    private static final double SOUTH_WEST_LAT_COORD = 54.084530;
    private static final double SOUTH_WEST_LONG_COORD = -8.012903;
    private static final double NORTH_EAST_LAT_COORD = 55.244539;
    private static final double NORTH_EAST_LONG_COORD = -5.551857;

    private static final double DEFAULT_CENTER_LATITUDE = (SOUTH_WEST_LAT_COORD + NORTH_EAST_LAT_COORD) / 2;
    private static final double DEFAULT_CENTER_LONGITUDE = (SOUTH_WEST_LONG_COORD + NORTH_EAST_LONG_COORD) / 2;

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
    }

    public void onLocationChangeClick(View view) {

        Intent intent = new LocationPickerActivity.Builder()
                .withLocation(DEFAULT_CENTER_LATITUDE, DEFAULT_CENTER_LONGITUDE)
                .withGeolocApiKey(getString(R.string.google_maps_key))
                .withSearchZone("es_ES")
                .withSearchZone(new SearchZoneRect(new LatLng(SOUTH_WEST_LAT_COORD, SOUTH_WEST_LONG_COORD), new LatLng(NORTH_EAST_LAT_COORD, NORTH_EAST_LONG_COORD)))
                .withDefaultLocaleSearchZone()
                .shouldReturnOkOnBackPressed()
                .withStreetHidden()
                .withCityHidden()
                .withZipCodeHidden()
                .withSatelliteViewHidden()
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
                double latitude = data.getDoubleExtra(LATITUDE, 0.0);
                Timber.d("LATITUDE %f", latitude);
                double longitude = data.getDoubleExtra(LONGITUDE, 0.0);
                Timber.d("LONGITUDE %f", longitude);
                String address = data.getStringExtra(LocationPickerActivityKt.LOCATION_ADDRESS);
                Timber.d("ADDRESS %s", address);
                String postalcode = data.getStringExtra(ZIPCODE);
                Timber.d("POSTCODE %s", postalcode);

            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.d("RESULT****", "CANCELLED");
        }
    }
}
