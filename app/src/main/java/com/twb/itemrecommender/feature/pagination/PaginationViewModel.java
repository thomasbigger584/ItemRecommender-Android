package com.twb.itemrecommender.feature.pagination;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.data.helper.SingleLiveEvent;

import java.lang.ref.WeakReference;
import java.util.List;

/*
 * Subclass this and also implement FirstPageAsyncTask and NextPageAsyncTask to
 * retrieve a paginated list of ItemType
 */
public abstract class PaginationViewModel<ItemType> extends AndroidViewModel {
    /*
     * SingleLiveEvent used to notify observers of any errors. Error string being sent to be displayed
     */
    public SingleLiveEvent<String> errorLiveEvent = new SingleLiveEvent<>();
    /*
     * LiveData used to notify observers of new data retrieved by each page
     */
    public MutableLiveData<List<ItemType>> itemLiveData = new MutableLiveData<>();
    /*
     * Integer pointing to the current page
     */
    public int currentPage = 0;
    /*
     * Boolean to hold if we are connected to the internet
     */
    private boolean isConnected = false;
    /*
     * Boolean to hold whether we are current loading a page,
     * so we dont load other pages at the same time while scrolling
     */
    private boolean isLoading = false;
    /*
     * Boolean to hold whether we have reached the last page and
     * not to get any more pages when scrolling
     */
    private boolean isLastPage = false;
    /*
     * Integer to hold the page size which is set by the subclass
     */
    private int pageSize;
    /*
     * scroll listener used to determine whether or not we need to load more images from the database
     */
    public RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = llm.getChildCount();
                int totalItemCount = llm.getItemCount();
                int firstVisibleItemPosition = llm.findFirstVisibleItemPosition();
                PaginationViewModel.this.onScrolled(visibleItemCount, totalItemCount, firstVisibleItemPosition);
            }
        }
    };


    public PaginationViewModel(@NonNull Application application) {
        super(application);
        this.pageSize = getPageSize();
    }

    /*
     * The subclass should specify the size of the pages as it could be different for different lists
     */
    protected abstract int getPageSize();

    /*
     * Called every time the recyclerview is scrolled
     */
    private void onScrolled(int visibleItemCount, int totalItemCount, int firstVisibleItemPosition) {
        if (!isLoading && !isLastPage) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= pageSize) {
//                checking  again for safety
                if (!isLoading && !isLastPage) {
                    onLoadMoreItems();
                }
            }
        }
    }

    /*
     * Allow the subclass to call its own FirstPageAsyncTask subclasses with its
     * own implementation of retrieving data per page
     */
    public void startInitialPage() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            isConnected = networkInfo != null &&
                    networkInfo.isConnected();
        }
        onStartInitialPage();
    }

    protected abstract void onStartInitialPage();

    /*
     * Allow the subclass to call its own NextPageAsyncTask subclasses with its
     * own implementation of retrieving data per page
     */
    protected abstract void onLoadMoreItems();

    private static abstract class PaginationAsyncTask<ItemType>
            extends AsyncTask<Void, Void, DataWrapper<List<ItemType>>> {

        /*
         * Holding a weak reference so we don't leak memory.
         * The AsyncTask could outlive the ViewModel so we check always for
         * the view model to be null. This is used to get references of the
         * fields required for pagination
         */
        WeakReference<? extends PaginationViewModel<ItemType>> weakViewModel;

        PaginationAsyncTask(PaginationViewModel<ItemType> viewModel) {
            this.weakViewModel = new WeakReference<>(viewModel);
        }

        @Override
        protected void onPreExecute() {
            PaginationViewModel<ItemType> viewModel = weakViewModel.get();
            if (viewModel == null || viewModel.isLoading) {
                cancel(true);
                return;
            }
            viewModel.isLoading = true;
            viewModel.isLastPage = false;
            doOnPreExecute(viewModel);
        }

        @Override
        protected DataWrapper<List<ItemType>> doInBackground(Void... voids) {
            PaginationViewModel<ItemType> viewModel = weakViewModel.get();
            if (viewModel == null) {
                cancel(true);
                return new DataWrapper<>(new Throwable("An error occurred"));
            }

            /*
             * retrieve the pagination items from the subclass
             */
            DataWrapper<List<ItemType>> itemDataWrapper = new DataWrapper<>();
            if (viewModel.isConnected) {
                itemDataWrapper = getNetworkPaginatedItems();
            }
            /*
             * if the response is an empty list then we are at the end of the list.
             * We decrement the current page to hold the last viable item of the list
             */
            if (itemDataWrapper != null && itemDataWrapper.isPresent()) {
                List<ItemType> itemTypeList = itemDataWrapper.getData();
                if (itemTypeList.size() == 0) {
                    viewModel.isLastPage = true;
                } else {
                    viewModel.isLastPage = false;
                }
            }

            return itemDataWrapper;
        }

        @Override
        /*
         * check if there is any errors in the pagination response, and signal to show an error.
         * At this stage we have finished loading the pagination request
         */
        protected void onPostExecute(DataWrapper<List<ItemType>> listDataWrapper) {
            PaginationViewModel<ItemType> viewModel = weakViewModel.get();
            if (viewModel == null) {
                return;
            }
            viewModel.isLoading = false;
            if (listDataWrapper == null) {
                viewModel.errorLiveEvent.setValue("data wrapper is null");
                return;
            }
            if (listDataWrapper.hasError()) {
                Throwable throwable = listDataWrapper.getError();
                viewModel.errorLiveEvent.setValue(throwable.getMessage());
                return;
            }
            doOnPostExecute(viewModel, listDataWrapper);
        }

        /*
         * generify sub classes to implement incrementing or reset of current page
         */
        abstract void doOnPreExecute(PaginationViewModel<ItemType> viewModel);

        /*
         * Allow sub classes to implement their own retrieval of data
         */
        public DataWrapper<List<ItemType>> getNetworkPaginatedItems() {
            throw new UnsupportedOperationException("Must override for functionality");
        }

        /*
         * set the new retrieved data to the live data to be observed for in the activity.
         * his is implemented so the subclasses dont have to. otherwise then can override and call super for extended functionality
         */
        void doOnPostExecute(PaginationViewModel<ItemType> viewModel, DataWrapper<List<ItemType>> listDataWrapper) {
            if (listDataWrapper.isPresent()) {
                viewModel.itemLiveData.setValue(listDataWrapper.getData());
            }
        }
    }

    /*
     * Before retrieving the first page then set the current page to 0 in order to get the first page
     */
    public abstract static class FirstPageAsyncTask<ItemType> extends PaginationAsyncTask<ItemType> {

        public FirstPageAsyncTask(PaginationViewModel<ItemType> viewModel) {
            super(viewModel);
        }

        @Override
        public void doOnPreExecute(PaginationViewModel<ItemType> viewModel) {
            viewModel.currentPage = 0;
        }
    }

    /*
     * Before each subsequent page then increment the current page count to get the next page
     */
    public abstract static class NextPageAsyncTask<ItemType> extends PaginationAsyncTask<ItemType> {

        public NextPageAsyncTask(PaginationViewModel<ItemType> viewModel) {
            super(viewModel);
        }

        @Override
        void doOnPreExecute(PaginationViewModel<ItemType> viewModel) {
            viewModel.currentPage++;
        }
    }
}
