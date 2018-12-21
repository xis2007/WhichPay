package com.whichpay.whichpay.fragments.searching;

import android.content.Context;

import com.whichpay.whichpay.activities.main.MainContract;
import com.whichpay.whichpay.activities.main.MainPresenter;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.model.firestore.FirestoreDataManager;
import com.whichpay.whichpay.objects.PayLocation;

import java.util.ArrayList;

public class SearchingPresenter implements SearchingContract.Presenter {
    private MainContract.View mMainView;
    private MainContract.Presenter mMainPresenter;

    private SearchingContract.View mSearchingView;

    public SearchingPresenter(SearchingContract.View homeView) {
        mSearchingView = homeView;
        mSearchingView.setPresenter(this);
    }

    @Override
    public void informNewSearchResults(ArrayList<PayLocation> payLocations) {
        mSearchingView.showSearchResults(payLocations);
        WhichPay.setSearchedAndFilteredPayLocationsList(payLocations);
    }

    @Override
    public void searchByPayLocationNameOrAddress(String query) {
        mMainPresenter.isLoading("Loading Data");
        new FirestoreDataManager((Context) mMainView).searchByUserInput(this, query);
    }

    @Override
    public void searchByPayLocationType(String locationType) {
        mMainPresenter.isLoading("Loading Data");
        new FirestoreDataManager((Context) mMainView).searchByPayLocationType(this, locationType);
    }

    @Override
    public void clearList() {
        mSearchingView.showNoResults();
    }

    @Override
    public void informToFinishLoading() {
        mMainPresenter.isNotLoading();
    }

    @Override
    public void informToShowInMaps(int positionInList) {
        mMainPresenter.transToMapsPage(positionInList);
    }

    @Override
    public void start() {

    }

    /**
     * ***********************************************************************************
     * Set MainView and MainPresenters to get reference to them
     * ***********************************************************************************
     */
    public void setMainView(MainContract.View mainView) {
        mMainView = mainView;
    }


    public void setMainPresenter(MainPresenter mainPresenter) {
        mMainPresenter = mainPresenter;
    }


}
