package com.twb.itemrecommender.feature.settings;

import android.os.Bundle;

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

}
