package com.whichpay.whichpay.application;

import android.app.Application;
import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;
import com.whichpay.whichpay.model.database.PayLocationsDatabase;

public class WhichPay extends Application {

    private static Context mContext;
    private static FirebaseFirestore mFirestoreDb;
    private static PayLocationsDatabase mPayLocationsOpenHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        initializeFirebaseDb();
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
}
