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
//                .whereEqualTo(Constants.PayLocationsAttribute.LOCATION_NAME, "Watsons")
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
                                Snackbar.make(((MainActivity) mContext).findViewById(R.id.container_main), "Nothing Found", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
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

//                                ArrayList<PayLocation> filteredList = filterByPayLocationType(locationType, filterByLongitude(transformSnapshotToPayLocations(querySnapshot)));
//                                ArrayList<PayLocation> distancedList = addDistanceToPayLocations(filteredList);
//                                ArrayList<PayLocation> sortedList = sortListByDistance(distancedList);
//                                presenter.informToShowSearchResults(sortedList);

                            } else {
                                Snackbar.make(((MainActivity) mContext).findViewById(R.id.container_main), "Nothing Found", Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            Snackbar.make(((MainActivity) mContext).findViewById(R.id.container_main), "Something went Wrong, please try again", Snackbar.LENGTH_SHORT).show();
                        }
                    }


                });
    }



//    /**
//     * *********************************************************************************
//     * Helper Methods
//     * *********************************************************************************
//     */
//    private ArrayList<PayLocation> transformSnapshotToPayLocations(QuerySnapshot querySnapshot) {
//        List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();
//        ArrayList<PayLocation> payLocationList = new ArrayList<>();
//
//        for (DocumentSnapshot documentSnapshot : documentSnapshots) {
//            PayLocation payLocation = documentSnapshot.toObject(PayLocation.class);
//            if (payLocation != null) {
//                payLocationList.add(payLocation);
//            }
//        }
//
//        return payLocationList;
//    }
//
//    private ArrayList<PayLocation> filterByLongitude(ArrayList<PayLocation> payLocations) {
//        ArrayList<PayLocation> filteredList = new ArrayList<>();
//
//        for (PayLocation payLocation : payLocations) {
//            if (payLocation.getLocationLongitude() >= (WhichPay.getCurrentLocation().getLongitude() - 0.025)
//                    && payLocation.getLocationLongitude() <= (WhichPay.getCurrentLocation().getLongitude() + 0.025)) {
//                filteredList.add(payLocation);
//            }
//        }
//
//        return filterByPayLocationMethod(filteredList);
//    }
//
//    private ArrayList<PayLocation> filterByNameOrAddress(String userInput, ArrayList<PayLocation> payLocations) {
//        ArrayList<PayLocation> filteredList = new ArrayList<>();
//
//        for (PayLocation payLocation : payLocations) {
//            if(payLocation.getPayLocationName().toUpperCase().contains(userInput.toUpperCase())) {
//                filteredList.add(payLocation);
//            }
//        }
//
//        return filteredList;
//    }
//
//    private ArrayList<PayLocation> filterByPayLocationType(String locationType, ArrayList<PayLocation> payLocations) {
//        ArrayList<PayLocation> filteredList = new ArrayList<>();
//
//        for (PayLocation payLocation : payLocations) {
//            if(payLocation.getPayLocationType().toUpperCase().contains(locationType.toUpperCase())) {
//                filteredList.add(payLocation);
//            }
//        }
//
//        return filteredList;
//    }
//
//    private ArrayList<PayLocation> filterByPayLocationMethod(ArrayList<PayLocation> payLocations) {
//        ArrayList<PayLocation> filteredList = new ArrayList<>();
//
//        SharedPreferences sharedPref = mContext.getSharedPreferences(Constants.SharedPreferences.PAY_TYPE_SETTINGS, Context.MODE_PRIVATE);
//
//        for (PayLocation payLocation : payLocations) {
//            if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_APPLE_PAY, true)) {
//                if(stringIsTrueOrFalse(payLocation.getPayLocationUseApplePay())) {
//                    Log.d("filterrrrr", "filterByPayLocationMethod: sharedPref: " + sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_APPLE_PAY, true));
//                    Log.d("filterrrrr", "filterByPayLocationMethod: payLocation: " + stringIsTrueOrFalse(payLocation.getPayLocationUseApplePay()));
//
//                    filteredList.add(payLocation);
//                }
//
//            } else if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_GOOGLE_PAY, true)) {
//                if(stringIsTrueOrFalse(payLocation.getPayLocationUseGooglePay())) {
//                    filteredList.add(payLocation);
//                }
//
//            } else if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_SAMSUNG_PAY, true)) {
//                if(stringIsTrueOrFalse(payLocation.getPaylocationUseSamsungPay())) {
//                    filteredList.add(payLocation);
//                }
//
//            } else if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_LINE_PAY, true)) {
//                if(stringIsTrueOrFalse(payLocation.getPayLocationUseLinePay())) {
//                    filteredList.add(payLocation);
//                }
//
//            } else if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_JKO_PAY, true)) {
//                if(stringIsTrueOrFalse(payLocation.getPayLocationUseJkoPay())) {
//                    filteredList.add(payLocation);
//                }
//            }
//        }
//
//        return filteredList;
//    }
//
//    private ArrayList<PayLocation> addDistanceToPayLocations(ArrayList<PayLocation> list) {
//        for (PayLocation payLocation : list) {
//            Location location = new Location(payLocation.getPayLocationName());
//            location.setLatitude(payLocation.getLocationLatitude());
//            location.setLongitude(payLocation.getLocationLongitude());
//
//            payLocation.setLocationDistance(WhichPay.getCurrentLocation().distanceTo(location));
//        }
//
//        return list;
//    }
//
//    private ArrayList<PayLocation> sortListByDistance(ArrayList<PayLocation> list) {
//        Collections.sort(list, Comparators.DISTANCE);
//
//        return list;
//    }
//
//    private static class Comparators {
//
//        private static Comparator<PayLocation> DISTANCE = new Comparator<PayLocation>() {
//            @Override
//            public int compare(PayLocation o1, PayLocation o2) {
//                if ((o1.getLocationDistance() == -1) && (o2.getLocationDistance() == -1)) {
//                    return 0;
//
//                } else if ((o1.getLocationDistance() == -1) && (o2.getLocationDistance() != -1)) {
//                    return 1;
//
//                } else if ((o1.getLocationDistance() != -1) && (o2.getLocationDistance() == -1)) {
//                    return -1;
//
//                } else if (o1.getLocationDistance() > o2.getLocationDistance()) {
//                    return 1;
//
//                } else if (o1.getLocationDistance() < o2.getLocationDistance()) {
//                    return -1;
//
//                } else {
//                    return 0;
//                }
//            }
//        };
//    }
//
//    static boolean stringIsTrueOrFalse(String string) {
//        switch (string.toUpperCase()) {
//            case "Y":
//                return true;
//            case "N":
//                return false;
//            default:
//                return false;
//        }
//    }
}
