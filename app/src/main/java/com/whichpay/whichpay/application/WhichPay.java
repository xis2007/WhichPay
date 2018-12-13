package com.whichpay.whichpay.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import com.google.firebase.firestore.FirebaseFirestore;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.model.database.PayLocationsDatabase;

public class WhichPay extends Application {

    private static Context mContext;
    private static FirebaseFirestore mFirestoreDb;
    private static PayLocationsDatabase mPayLocationsOpenHelper;

    private static Location mDefaultLocation;
    private static Location mCurrentLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        initializeFirebaseDb();
        initializeLocationSettings();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static FirebaseFirestore getmFirestoreDb() {
        return mFirestoreDb;
    }

    private void initializeFirebaseDb() {
        mFirestoreDb = FirebaseFirestore.getInstance();
    }

    public static PayLocationsDatabase getPayLocationsDatabase() {
        if (mPayLocationsOpenHelper == null) mPayLocationsOpenHelper = new PayLocationsDatabase(getAppContext());
        return mPayLocationsOpenHelper;
    }


    /**
     * ********************************************************************************************
     * For Location Settings
     * ********************************************************************************************
     */
    private void initializeLocationSettings() {
        if (mDefaultLocation == null) mDefaultLocation = new Location("default");

        SharedPreferences sharedPreferences =
                getAppContext().getSharedPreferences(Constants.SharedPreferences.LOCATION_SETTINGS, MODE_PRIVATE);

        mDefaultLocation.setProvider(sharedPreferences.getString(Constants.SharedPreferences.DEFAULT_LOCATION_NAME, getString(R.string.default_location_taipei_city)));
        mDefaultLocation.setLatitude(Double.valueOf(sharedPreferences.getString(Constants.SharedPreferences.DEFAULT_LOCATION_LAT, "25.054685")));
        mDefaultLocation.setLongitude(Double.valueOf(sharedPreferences.getString(Constants.SharedPreferences.DEFAULT_LOCATION_LNG, "121.543801")));
    }

    public static void setDefaultLocation(Location defaultLocation) {
        mDefaultLocation = defaultLocation;
        getAppContext()
                .getSharedPreferences(Constants.SharedPreferences.LOCATION_SETTINGS, Context.MODE_PRIVATE)
                .edit()
                .putString(Constants.SharedPreferences.DEFAULT_LOCATION_NAME, defaultLocation.getProvider())
                .putString(Constants.SharedPreferences.DEFAULT_LOCATION_LAT, String.valueOf(defaultLocation.getLatitude()))
                .putString(Constants.SharedPreferences.DEFAULT_LOCATION_LNG, String.valueOf(defaultLocation.getLongitude()))
                .apply();
    }

    public static Location getDefaultLocation() {
        return mDefaultLocation;
    }

    public static Location getCurrentLocation() {
        // TODO to be changed
        return mDefaultLocation;
    }




}
