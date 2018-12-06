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
        mSettingsView.initDefaultLocation();
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

    @Override
    public void setNewDefaultLocation(int chekedId) {
        Location defaultLocation;

        switch (chekedId) {
            case R.id.radioButton_keelung_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_keelung_city));
                defaultLocation.setLatitude(121.7081);
                defaultLocation.setLongitude(25.10898);
                break;

            case R.id.radioButton_taipei_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_taipei_city));
                defaultLocation.setLatitude(121.5598);
                defaultLocation.setLongitude(25.09108);
                break;

            case R.id.radioButton_new_taipei_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_new_taipei_city));
                defaultLocation.setLatitude(121.6739);
                defaultLocation.setLongitude(24.91571);
                break;

            case R.id.radioButton_taoyuan_county:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_taoyuan_county));
                defaultLocation.setLatitude(121.2168);
                defaultLocation.setLongitude(24.93759);
                break;

            case R.id.radioButton_taichung_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_taichung_city));
                defaultLocation.setLatitude(120.9417);
                defaultLocation.setLongitude(24.23321);
                break;

            case R.id.radioButton_kaohsiung_city:
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_kaohsiung_city));
                defaultLocation.setLatitude(120.666);
                defaultLocation.setLongitude(23.01087);
                break;

            default:
                Log.d("defaultLocationnn", "setNewDefaultLocation: default called");
                defaultLocation = new Location(((Context) mMainView).getString(R.string.default_location_taipei_city));
                defaultLocation.setLatitude(121.5598);
                defaultLocation.setLongitude(25.09108);
                break;
        }

        WhichPay.setDefaultLocation(defaultLocation);
        mSettingsView.showDefaultLocation(defaultLocation.getProvider());
    }
}
