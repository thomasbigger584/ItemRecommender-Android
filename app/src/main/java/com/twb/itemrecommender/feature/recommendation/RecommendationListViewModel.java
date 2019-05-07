package com.twb.itemrecommender.feature.recommendation;

import android.app.Application;

import androidx.annotation.NonNull;

import com.twb.itemrecommender.SmartToursApplication;
import com.twb.itemrecommender.data.AttractionRepository;
import com.twb.itemrecommender.data.domain.Recommendation;
import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.feature.pagination.PaginationViewModel;
import com.twb.itemrecommender.feature.util.LocationUtil;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public final class RecommendationListViewModel extends PaginationViewModel<Recommendation> {

    private static final int PAGE_SIZE = 15;
    @Inject
    AttractionRepository attractionRepository;
    private LocationUtil.Location location;

    public RecommendationListViewModel(@NonNull Application application) {
        super(application);
        SmartToursApplication.getApplicationComponent().
                inject(this);
    }

    private static DataWrapper<List<Recommendation>> getRecommendations(WeakReference<RecommendationListViewModel> weakReference) {
        RecommendationListViewModel viewModel = weakReference.get();
        AttractionRepository attractionRepository = viewModel.attractionRepository;
        LocationUtil.Location location = viewModel.location;
        int currentPage = viewModel.currentPage;
        return attractionRepository.getRecommendations(location, currentPage, PAGE_SIZE);
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
            extends PaginationViewModel.FirstPageAsyncTask<Recommendation> {

        private WeakReference<RecommendationListViewModel> weakReference;

        FirstPageAsyncTask(RecommendationListViewModel viewModel) {
            super(viewModel);
            weakReference = new WeakReference<>(viewModel);
        }

        @Override
        public DataWrapper<List<Recommendation>> getNetworkPaginatedItems() {
            return getRecommendations(weakReference);
        }
    }

    private final static class NextPageAsyncTask
            extends PaginationViewModel.NextPageAsyncTask<Recommendation> {

        private WeakReference<RecommendationListViewModel> weakReference;

        NextPageAsyncTask(RecommendationListViewModel viewModel) {
            super(viewModel);
            weakReference = new WeakReference<>(viewModel);
        }

        @Override
        public DataWrapper<List<Recommendation>> getNetworkPaginatedItems() {
            return getRecommendations(weakReference);
        }
    }
}
