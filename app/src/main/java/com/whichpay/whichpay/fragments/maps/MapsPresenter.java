package com.whichpay.whichpay.fragments.maps;

import com.whichpay.whichpay.activities.main.MainContract;
import com.whichpay.whichpay.activities.main.MainPresenter;


public class MapsPresenter implements MapsContract.Presenter {
    private MapsContract.View mMapsView;

    private MainContract.View mMainView;
    private MainPresenter mMainPresenter;


    public MapsPresenter(MapsContract.View mainView) {
        mMapsView = mainView;
        mMapsView.setPresenter(this);
    }

    @Override
    public void transToExplorePage() {

    }


    @Override
    public void start() {

    }

    /**
     * ***********************************************************************************
     * Getters and Setters
     * ***********************************************************************************
     */





    /**
     * ***********************************************************************************
     * Helper/Other Methods
     * ***********************************************************************************
     */



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
