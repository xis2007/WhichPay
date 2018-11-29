package com.whichpay.whichpay.fragments.settings;

import com.whichpay.whichpay.activities.main.MainContract;
import com.whichpay.whichpay.activities.main.MainPresenter;

public class SettingsPresenter implements SettingsContract.Presenter {
    private MainContract.View mMainView;
    private MainContract.Presenter mMainPresenter;

    private SettingsContract.View mSettingsView;

    public SettingsPresenter(SettingsContract.View settingsView) {
        mSettingsView = settingsView;
        mSettingsView.setPresenter(this);
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

    /**
     * ***********************************************************************************
     * Getters and Setters
     * ***********************************************************************************
     */
    public MainContract.View getMainView() {
        return mMainView;
    }

    public MainContract.Presenter getMainPresenter() {
        return mMainPresenter;
    }
}
