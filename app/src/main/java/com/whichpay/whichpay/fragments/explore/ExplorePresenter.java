package com.whichpay.whichpay.fragments.explore;

import com.whichpay.whichpay.activities.main.MainContract;
import com.whichpay.whichpay.activities.main.MainPresenter;

public class ExplorePresenter implements ExploreContract.Presenter {
    private MainContract.View mMainView;
    private MainContract.Presenter mMainPresenter;

    private ExploreContract.View mHomeView;

    public ExplorePresenter(ExploreContract.View homeView) {
        mHomeView = homeView;
        mHomeView.setPresenter(this);
    }


    @Override
    public void informToShowNearbyResults(int searchType, String payLocationsType) {
        mMainPresenter.transToSearchingPage(searchType, payLocationsType);
        mMainPresenter.getSearchingPresenter().searchByPayLocationType(payLocationsType);
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
