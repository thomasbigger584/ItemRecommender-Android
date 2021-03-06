package com.twb.itemrecommender.feature.product.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.twb.itemrecommender.R;
import com.twb.itemrecommender.feature.product.ProductListActivity;

/**
 * An activity representing a single Product detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ProductListActivity}.
 */
public class ProductDetailActivity extends AppCompatActivity {

    @DrawableRes
    private static final int FAVOURITE_ON = android.R.drawable.btn_star_big_on;

    @DrawableRes
    private static final int FAVOURITE_OFF = android.R.drawable.btn_star_big_off;

    @DrawableRes
    private int currentFavouriteStatus = FAVOURITE_OFF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(ProductDetailFragment.ARG_ITEM,
                    getIntent().getParcelableExtra(ProductDetailFragment.ARG_ITEM));
            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.product_detail_container, fragment)
                    .commit();

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(view -> {
                switch (currentFavouriteStatus) {
//                      Action to unfavourite the product
//                    case FAVOURITE_ON: {
//                        fragment.onFavouriteClick(true);
//                        fab.setImageResource(FAVOURITE_OFF);
//                        currentFavouriteStatus = FAVOURITE_OFF;
//                        break;
//                    }
//                      Action to favourite the product
                    case FAVOURITE_OFF: {
                        fragment.onFavouriteClick(true);
                        fab.setImageResource(FAVOURITE_ON);
                        currentFavouriteStatus = FAVOURITE_ON;
                        break;
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ProductListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
