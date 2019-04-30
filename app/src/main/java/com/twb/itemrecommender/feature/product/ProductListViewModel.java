package com.twb.itemrecommender.feature.product;

import android.app.Application;

import androidx.annotation.NonNull;

import com.twb.itemrecommender.SmartToursApplication;
import com.twb.itemrecommender.data.AttractionRepository;
import com.twb.itemrecommender.data.domain.Attraction;
import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.feature.pagination.PaginationViewModel;
import com.twb.itemrecommender.feature.util.LocationUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public final class ProductListViewModel extends PaginationViewModel<Attraction> {

    private LocationUtil.Location location;

    private static final int PAGE_SIZE = 15;

    @Inject
    AttractionRepository attractionRepository;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
        SmartToursApplication.getApplicationComponent().
                inject(this);
    }

    private static DataWrapper<List<Attraction>> getAttractions(WeakReference<ProductListViewModel> weakReference) {
        ProductListViewModel viewModel = weakReference.get();
        AttractionRepository attractionRepository = viewModel.attractionRepository;
        LocationUtil.Location location = viewModel.location;
        int currentPage = viewModel.currentPage;
        return attractionRepository.getAllByLocation(location, currentPage, PAGE_SIZE);
    }

    @Override
    public int getPageSize() {
        return PAGE_SIZE;
    }

    public void setLocation(LocationUtil.Location location) {
        this.location = location;
    }

    @Override
    protected void onStartInitialPage() {
        FirstPageAsyncTask firstPageAsyncTask =
                new FirstPageAsyncTask(this);
        firstPageAsyncTask.execute();
    }

    @Override
    public void onLoadMoreItems() {
        NextPageAsyncTask nextPageAsyncTask =
                new NextPageAsyncTask(this);
        nextPageAsyncTask.execute();
    }

    private final static class FirstPageAsyncTask
            extends PaginationViewModel.FirstPageAsyncTask<Attraction> {

        private WeakReference<ProductListViewModel> weakReference;

        FirstPageAsyncTask(ProductListViewModel viewModel) {
            super(viewModel);
            weakReference = new WeakReference<>(viewModel);
        }

        @Override
        public DataWrapper<List<Attraction>> getNetworkPaginatedItems() {
            return getAttractions(weakReference);
        }
    }

    private final static class NextPageAsyncTask
            extends PaginationViewModel.NextPageAsyncTask<Attraction> {

        private WeakReference<ProductListViewModel> weakReference;

        NextPageAsyncTask(ProductListViewModel viewModel) {
            super(viewModel);
            weakReference = new WeakReference<>(viewModel);
        }

        @Override
        public DataWrapper<List<Attraction>> getNetworkPaginatedItems() {
            return getAttractions(weakReference);
        }
    }
}
