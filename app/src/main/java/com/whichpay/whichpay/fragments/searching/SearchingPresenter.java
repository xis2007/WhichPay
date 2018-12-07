package com.whichpay.whichpay.fragments.searching;

import android.content.Context;

import com.whichpay.whichpay.activities.main.MainContract;
import com.whichpay.whichpay.activities.main.MainPresenter;
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
    public void informToShowSearchResults(ArrayList<PayLocation> payLocations) {
        mSearchingView.showSearchResults(payLocations);
    }

    @Override
    public void searchByInputQuery(String query) {
        new FirestoreDataManager((Context) mMainView).searchByUserInput(this, query);
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
