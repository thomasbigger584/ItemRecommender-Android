package com.twb.itemrecommender.feature.product.detail;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.twb.itemrecommender.SmartToursApplication;
import com.twb.itemrecommender.data.AttractionRepository;
import com.twb.itemrecommender.data.domain.AttractionPurchase;
import com.twb.itemrecommender.data.helper.DataWrapper;
import com.twb.itemrecommender.data.helper.SingleLiveEvent;
import com.twb.itemrecommender.feature.util.LocationUtil;

import javax.inject.Inject;

public class ProductDetailViewModel extends AndroidViewModel {

    SingleLiveEvent<Long> registerInterestSingleLiveEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> takeActionSingleLiveEvent = new SingleLiveEvent<>();

    SingleLiveEvent<String> errorLiveEvent = new SingleLiveEvent<>();

    @Inject
    AttractionRepository attractionRepository;

    public ProductDetailViewModel(@NonNull Application application) {
        super(application);
        SmartToursApplication.getApplicationComponent().
                inject(this);
    }

    void registerInterest(Long attractionId, String traveling, String activity, Double userDistance, LocationUtil.Location location) {
        AsyncTask.execute(() -> {
            DataWrapper<AttractionPurchase> responseDataWrapper = attractionRepository.takeInterest(attractionId, traveling, activity, userDistance, location);
            if (!responseDataWrapper.hasError()) {
                registerInterestSingleLiveEvent.postValue(responseDataWrapper.getData().getId());
                return;
            }
            errorLiveEvent.postValue(responseDataWrapper.getError().getMessage());
        });
    }

    void takeAction() {
        Long attractionPurchaseId = registerInterestSingleLiveEvent.getValue();
        if (attractionPurchaseId == null) {
            this.errorLiveEvent.postValue("No Attraction Purchase Id");
            return;
        }
        AsyncTask.execute(() -> {
            DataWrapper<AttractionPurchase> responseDataWrapper = attractionRepository.takeAction(attractionPurchaseId);
            if (!responseDataWrapper.hasError()) {
                takeActionSingleLiveEvent.postCall();
                return;
            }
            errorLiveEvent.postValue(responseDataWrapper.getError().getMessage());
        });
    }
}
