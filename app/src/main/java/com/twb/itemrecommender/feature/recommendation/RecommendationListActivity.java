package com.twb.itemrecommender.feature.recommendation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.twb.itemrecommender.R;
import com.twb.itemrecommender.feature.navigation.BaseNavigationActivity;
import com.twb.itemrecommender.feature.product.detail.ProductDetailActivity;
import com.twb.itemrecommender.feature.product.detail.ProductDetailFragment;
import com.twb.itemrecommender.feature.util.LocationUtil;

public class RecommendationListActivity extends BaseNavigationActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private RecommendationListAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecommendationListViewModel recommendationListViewModel;

    @Override
    protected int getContentView() {
        return R.layout.activity_recommendation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Attraction Recommendations");
        }
        if (findViewById(R.id.product_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        adapter = new RecommendationListAdapter(recommendation -> {
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(ProductDetailFragment.ARG_ITEM, recommendation.getAttraction());
                ProductDetailFragment fragment = new ProductDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.product_detail_container, fragment)
                        .commit();
            } else {
                Context context = RecommendationListActivity.this;
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra(ProductDetailFragment.ARG_ITEM, recommendation.getAttraction());
                context.startActivity(intent);
            }
        });
        adapter.setHasStableIds(true);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this::startInitialPage);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));

        recommendationListViewModel = ViewModelProviders.of(this).
                get(RecommendationListViewModel.class);

        recommendationListViewModel.itemLiveData.observe(this, attractions -> {
            if (attractions == null) {
                stopSwipeRefresh();
            } else {
                int itemSize = adapter.addItems(attractions);
//                do something if there is no attractions
                stopSwipeRefresh();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.product_list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(recommendationListViewModel.scrollListener);

        startInitialPage();
    }

    private void startInitialPage() {
        stopSwipeRefresh();
        adapter.clear();
        startSwipeRefresh();
        LocationUtil.Location location = LocationUtil.getSavedLocation(this);
        recommendationListViewModel.setLocation(location);
        recommendationListViewModel.startInitialPage();
    }

    private void startSwipeRefresh() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    private void stopSwipeRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
