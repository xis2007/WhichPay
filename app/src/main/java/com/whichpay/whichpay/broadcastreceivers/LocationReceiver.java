package com.whichpay.whichpay.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.whichpay.whichpay.activities.main.MainContract;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.contants.Constants;

public class LocationReceiver extends BroadcastReceiver {
    private MainContract.Presenter mMainPresenter;

    public LocationReceiver(MainContract.Presenter mainPresenter) {
        mMainPresenter = mainPresenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        double currentLat = intent.getDoubleExtra(Constants.LocationIntentExtras.LOCATION_LOC_LAT, WhichPay.getDefaultLocation().getLatitude());
        double currentLng = intent.getDoubleExtra(Constants.LocationIntentExtras.LOCATION_LOC_LNG, WhichPay.getDefaultLocation().getLongitude());

        Location currentLocation = new Location("currentLocation");
        currentLocation.setLatitude(currentLat);
        currentLocation.setLongitude(currentLng);

        mMainPresenter.updateCurrentLocation(currentLocation);
    }
}
