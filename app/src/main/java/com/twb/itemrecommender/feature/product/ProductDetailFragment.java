package com.twb.itemrecommender.feature.product;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.twb.itemrecommender.R;
import com.twb.itemrecommender.data.domain.Attraction;

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

        }
        return rootView;
    }

    public void onFavouriteClick(boolean show) {

        if (show) {
            this.purchaseButton.setVisibility(View.VISIBLE);
        } else {
            this.purchaseButton.setVisibility(View.GONE);
        }
    }
}
