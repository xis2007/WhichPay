package com.whichpay.whichpay.fragments.home;

import com.whichpay.whichpay.activities.main.MainContract;
import com.whichpay.whichpay.activities.main.MainPresenter;

public class HomePresenter implements HomeContract.Presenter {
    private MainContract.View mMainView;
    private MainContract.Presenter mMainPresenter;

    private HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View homeView) {
        mHomeView = homeView;
        mHomeView.setPresenter(this);
    }


    @Override
    public void informToShowNearbyResults() {

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
