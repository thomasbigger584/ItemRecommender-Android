package com.twb.itemrecommender.feature.entrypoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.twb.itemrecommender.R;
import com.twb.itemrecommender.feature.product.ProductListActivity;

public class EntrypointActivity extends AppCompatActivity {
    private final String TAG = EntrypointActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrypoint);
        Intent i = new Intent(EntrypointActivity.this, ProductListActivity.class);
        startActivity(i);
    }

}
