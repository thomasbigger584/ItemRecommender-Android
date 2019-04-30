package com.twb.itemrecommender.feature.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.twb.itemrecommender.R;
import com.twb.itemrecommender.feature.navigation.BaseNavigationActivity;


/**
 * An activity representing a list of Products. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ProductDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ProductListActivity extends BaseNavigationActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private ProductListAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ProductListViewModel productListViewModel;

    @Override
    protected int getContentView() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Train Attractions");
        }
        if (findViewById(R.id.product_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        adapter = new ProductListAdapter(attraction -> {
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(ProductDetailFragment.ARG_ITEM, attraction);
                ProductDetailFragment fragment = new ProductDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.product_detail_container, fragment)
                        .commit();
            } else {
                Context context = ProductListActivity.this;
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra(ProductDetailFragment.ARG_ITEM, attraction);
                context.startActivity(intent);
            }
        });
        adapter.setHasStableIds(true);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this::startInitialPage);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));

        productListViewModel = ViewModelProviders.of(this).
                get(ProductListViewModel.class);

        productListViewModel.itemLiveData.observe(this, attractions -> {
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
        recyclerView.addOnScrollListener(productListViewModel.scrollListener);

        startInitialPage();
    }

    private void startInitialPage() {
        stopSwipeRefresh();
        adapter.clear();
        startSwipeRefresh();
        productListViewModel.startInitialPage();
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
