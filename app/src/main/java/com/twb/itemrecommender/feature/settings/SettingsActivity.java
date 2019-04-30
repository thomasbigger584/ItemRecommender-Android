package com.twb.itemrecommender.feature.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.twb.itemrecommender.R;
import com.twb.itemrecommender.feature.navigation.BaseNavigationActivity;

public class SettingsActivity extends BaseNavigationActivity {

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
        Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show();
    }

    public void onTravelingChangeClick(View view) {
        Toast.makeText(this, "Traveling", Toast.LENGTH_SHORT).show();
    }

    public void onActivityChangeClick(View view) {
        Toast.makeText(this, "Activity", Toast.LENGTH_SHORT).show();
    }
}
