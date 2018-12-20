package com.whichpay.whichpay.model.firestore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.activities.main.MainActivity;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.fragments.searching.SearchingContract;

public class FirestoreDataManager {
    Context mContext;

    public FirestoreDataManager(Context context) {
        mContext = context;
    }

    public void searchByUserInput(final SearchingContract.Presenter presenter, final String userInput) {
        CollectionReference payLocationsRef = WhichPay.getmFirestoreDb().collection(Constants.Firestore.COLLECTION_PAY_LOCATIONS);

        payLocationsRef
                .whereGreaterThanOrEqualTo(Constants.PayLocationsAttribute.LOCATION_LAT, WhichPay.getCurrentLocation().getLatitude() - 0.025)
                .whereLessThanOrEqualTo(Constants.PayLocationsAttribute.LOCATION_LAT, WhichPay.getCurrentLocation().getLatitude() + 0.025)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (!querySnapshot.isEmpty()) {
                                new PayLocationsProcessingTask(Constants.Firestore.SEARCH_BY_USER_INPUT, userInput, presenter).execute(querySnapshot);

                            } else {
                                presenter.informToFinishLoading();
                                Snackbar.make(((MainActivity) mContext).findViewById(R.id.container_main), "Nothing Found", Snackbar.LENGTH_SHORT).show();
                            }

                        } else {
                            presenter.informToFinishLoading();
                            Snackbar.make(((MainActivity) mContext).findViewById(R.id.container_main), "Something went Wrong, please try again", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void searchByPayLocationType(final SearchingContract.Presenter presenter, final String locationType) {
        CollectionReference payLocationsRef = WhichPay.getmFirestoreDb().collection(Constants.Firestore.COLLECTION_PAY_LOCATIONS);

        payLocationsRef
                .whereGreaterThanOrEqualTo(Constants.PayLocationsAttribute.LOCATION_LAT, WhichPay.getCurrentLocation().getLatitude() - 0.025)
                .whereLessThanOrEqualTo(Constants.PayLocationsAttribute.LOCATION_LAT, WhichPay.getCurrentLocation().getLatitude() + 0.025)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (!querySnapshot.isEmpty()) {
                                new PayLocationsProcessingTask(Constants.Firestore.SEARCH_BY_PAY_LOCATION_TYPE, locationType, presenter).execute(querySnapshot);

                            } else {
                                presenter.informToFinishLoading();
                                Snackbar.make(((MainActivity) mContext).findViewById(R.id.container_main), "Nothing Found", Snackbar.LENGTH_SHORT).show();
                            }

                        } else {
                            presenter.informToFinishLoading();
                            Snackbar.make(((MainActivity) mContext).findViewById(R.id.container_main), "Something went Wrong, please try again", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
