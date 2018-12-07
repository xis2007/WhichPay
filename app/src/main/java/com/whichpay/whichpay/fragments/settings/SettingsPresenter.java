package com.whichpay.whichpay.fragments.settings;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.activities.main.MainContract;
import com.whichpay.whichpay.activities.main.MainPresenter;
import com.whichpay.whichpay.application.WhichPay;

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
        mSettingsView.showDefaultLocation(WhichPay.getDefaultLocation().getProvider());
    }

    @Override
    public void setNewDefaultLocation(int chekedId) {
        Location defaultLocation;

        switch (chekedId) {
            case R.id.radioButton_keelung_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_keelung_city));
                defaultLocation.setLatitude(25.125869);
                defaultLocation.setLongitude(121.736511);
                break;

            case R.id.radioButton_taipei_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_taipei_city));
                defaultLocation.setLatitude(25.054685);
                defaultLocation.setLongitude(121.543801);
                break;

            case R.id.radioButton_new_taipei_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_new_taipei_city));
                defaultLocation.setLatitude(24.91571);
                defaultLocation.setLongitude(121.6739);
                break;

            case R.id.radioButton_taoyuan_county:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_taoyuan_county));
                defaultLocation.setLatitude(24.93759);
                defaultLocation.setLongitude(121.2168);
                break;

            case R.id.radioButton_taichung_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_taichung_city));
                defaultLocation.setLatitude(24.23321);
                defaultLocation.setLongitude(120.9417);
                break;

            case R.id.radioButton_kaohsiung_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_kaohsiung_city));
                defaultLocation.setLatitude(23.01087);
                defaultLocation.setLongitude(120.666);
                break;

            default:
                Log.d("defaultLocationnn", "setNewDefaultLocation: default called");
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_taipei_city));
                defaultLocation.setLatitude(25.09108);
                defaultLocation.setLongitude(121.5598);
                break;
        }

        WhichPay.setDefaultLocation(defaultLocation);
        mSettingsView.showDefaultLocation(defaultLocation.getProvider());
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
