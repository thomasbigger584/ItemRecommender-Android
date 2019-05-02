package com.twb.itemrecommender.feature.product.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.twb.itemrecommender.R;
import com.twb.itemrecommender.data.domain.Attraction;
import com.twb.itemrecommender.feature.product.ProductListActivity;
import com.twb.itemrecommender.feature.util.Constants;
import com.twb.itemrecommender.feature.util.LocationUtil;
import com.twb.itemrecommender.feature.util.SharedPrefsUtils;

/**
 * A fragment representing a single Product detail screen.
 * This fragment is either contained in a {@link ProductListActivity}
 * in two-pane mode (on tablets) or a {@link ProductDetailActivity}
 * on handsets.
 */
public class ProductDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM = "attraction";

    /**
     * The dummy content this fragment is presenting.
     */
    private Attraction mItem;

    private Button purchaseButton;

    private ProductDetailViewModel viewModel;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        if (getArguments().containsKey(ARG_ITEM)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            mItem = getArguments().getParcelable(ARG_ITEM);

            Activity activity = this.getActivity();
            assert activity != null;
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
                ImageView toolbarImageView = appBarLayout.findViewById(R.id.toolbar_image_view);
                Glide.with(this).
                        load(mItem.getThumbnailUrl()).
                        apply(RequestOptions.centerCropTransform()).
                        into(toolbarImageView);

                viewModel = ViewModelProviders.
                        of(this).get(ProductDetailViewModel.class);

                viewModel.registerInterestSingleLiveEvent.observe(this, integer -> this.purchaseButton.setVisibility(View.VISIBLE));
                viewModel.takeActionSingleLiveEvent.observe(this, aVoid -> {
                    Toast.makeText(activity, mItem.getName() + " Booked", Toast.LENGTH_SHORT).show();
                    this.purchaseButton.setVisibility(View.GONE);
                    activity.navigateUpTo(new Intent(activity, ProductListActivity.class));
                });
                viewModel.errorLiveEvent.observe(this, error -> {
                    Toast.makeText(activity, error, Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.product_detail)).setText(mItem.getPerex());

            ((TextView) rootView.findViewById(R.id.marker)).setText(mItem.getMarker());
            ((TextView) rootView.findViewById(R.id.categories)).setText(mItem.getCategories());
            ((TextView) rootView.findViewById(R.id.summary)).setText(mItem.getDsSummary());
            ((TextView) rootView.findViewById(R.id.apparentTempHigh)).setText(String.format("Apparent Temp High: %s", mItem.getDsApparentTemperatureHigh()));
            ((TextView) rootView.findViewById(R.id.apparentTempLow)).setText(String.format("Apparent Temp Low: %s", mItem.getDsApparentTemperatureLow()));
            ((TextView) rootView.findViewById(R.id.dewPoint)).setText(String.format("Dew Point: %s", mItem.getDsDewPoint()));
            ((TextView) rootView.findViewById(R.id.humidity)).setText(String.format("Humidity: %s", mItem.getDsHumidity()));
            ((TextView) rootView.findViewById(R.id.pressure)).setText(String.format("Pressure: %s", mItem.getDsPressure()));
            ((TextView) rootView.findViewById(R.id.windspeed)).setText(String.format("Wind Speed: %s", mItem.getDsWindSpeed()));
            ((TextView) rootView.findViewById(R.id.windgust)).setText(String.format("Wind Gust: %s", mItem.getDsWindGust()));
            ((TextView) rootView.findViewById(R.id.cloudcover)).setText(String.format("Cloud Cover: %s", mItem.getDsCloudCover()));

            this.purchaseButton = rootView.findViewById(R.id.purchaseButton);
            this.purchaseButton.setOnClickListener(view -> viewModel.takeAction());
        }
        return rootView;
    }

    public void onFavouriteClick(boolean favourite) {
        if (favourite) {
            Activity activity = this.getActivity();
            String thisTraveling = SharedPrefsUtils.getStringPreference(activity, Constants.PREF_TRAVELING_KEY);
            if (thisTraveling == null) {
                Toast.makeText(activity, "Traveling is not set", Toast.LENGTH_SHORT).show();
                return;
            }
            String thisActivity = SharedPrefsUtils.getStringPreference(activity, Constants.PREF_ACTIVITY_KEY);
            if (thisActivity == null) {
                Toast.makeText(activity, "Activity is not set", Toast.LENGTH_SHORT).show();
                return;
            }
            LocationUtil.Location location = LocationUtil.getSavedLocation(activity);
            this.viewModel.registerInterest(mItem.getId(), thisTraveling, thisActivity, mItem.getDistance(), location);
        } else {

        }
    }
}
