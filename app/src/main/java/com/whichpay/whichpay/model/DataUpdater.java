package com.whichpay.whichpay.model;

import android.database.Cursor;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.WriteBatch;
import com.whichpay.whichpay.activities.main.MainActivity;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.model.database.PayLocationsDatabase;
import com.whichpay.whichpay.objects.PayLocation;

import java.util.ArrayList;

public class DataUpdater {
    MainActivity mMainActivity;

    public DataUpdater(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public ArrayList<PayLocation> getDataFromDatabase (int indexFrom) {
        Cursor cursor = WhichPay.getPayLocationsDatabase().getData(indexFrom);

        return transformCursorToList(cursor);
    }

    private ArrayList<PayLocation> transformCursorToList(Cursor cursor) {
        ArrayList<PayLocation> payLocations = new ArrayList<>();

        while (cursor.moveToNext()) {
            final PayLocation payLocation = new PayLocation();
            payLocation.set_Id(cursor.getLong(cursor.getColumnIndex(PayLocationsDatabase._ID)));;
            payLocation.setLocationId(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_PLACE_ID)));
            payLocation.setPayLocationName(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_NAME)));
            payLocation.setPaylocationBranch(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_BRANCH)));
            payLocation.setPayLocationType(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_TYPE)));
            payLocation.setPayLocationAddress(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_ADDRESS)));
            payLocation.setPayLocationDescription(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_DESCRIPTION)));
            payLocation.setLocationLatitude(cursor.getFloat(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_LATITUTDE)));
            payLocation.setLocationLongitude(cursor.getFloat(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_LONGITUDE)));
            payLocation.setPayLocationUseApplePay(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_USE_APPLEPAY)));
            payLocation.setPayLocationUseGooglePay(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_USE_GOOGLEPAY)));
            payLocation.setPaylocationUseSamsungPay(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_USE_LINEPAY)));
            payLocation.setPayLocationUseLinePay(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_USE_JKOPAY)));
            payLocation.setPayLocationUseJkoPay(cursor.getString(cursor.getColumnIndex(PayLocationsDatabase.PAY_LOCATIONS_USE_SAMSUNGPAY)));

            payLocations.add(payLocation);
        }

        return payLocations;
    }


    public void updateToFirestore(ArrayList<PayLocation> payLocations) {
        Log.d("firestoreeeeee", "updateToFirestore: paylocations size is: " + payLocations.size());
        WriteBatch batch = WhichPay.getmFirestoreDb().batch();
        CollectionReference collectionReference = WhichPay.getmFirestoreDb()
                .collection(Constants.Firestore.COLLECTION_PAY_LOCATIONS);

        for (PayLocation payLocation : payLocations) {
            DocumentReference documentReference = collectionReference.document();
            batch.set(documentReference, payLocation);
        }

        batch.commit();

//        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()) {
//                    mMainActivity.incrementUpdateIndexAndUpdateAgain(500);
//                }
//            }
//        });
    }
}
