package com.whichpay.whichpay.model.firestore;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.fragments.searching.SearchingContract;
import com.whichpay.whichpay.objects.PayLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PayLocationsProcessingTask extends AsyncTask<QuerySnapshot, Void, ArrayList<PayLocation>> {
    private int mSearchByType = 0;
    private String mUserInputOrPayLocationType;
    private SearchingContract.Presenter mPresenter;

    public PayLocationsProcessingTask(int searchByType, String userInputOrPayLocationType, SearchingContract.Presenter presenter) {
        mSearchByType = searchByType;
        mUserInputOrPayLocationType = userInputOrPayLocationType;
        mPresenter = presenter;
    }

    @Override
    protected ArrayList<PayLocation> doInBackground(QuerySnapshot... querySnapshots) {
        QuerySnapshot querySnapshot = querySnapshots[0];

        ArrayList<PayLocation> filteredList;
        ArrayList<PayLocation> distancedList;
        ArrayList<PayLocation> sortedList = null;

        switch (mSearchByType) {
            case Constants.Firestore.SEARCH_BY_USER_INPUT:
                filteredList = filterByNameOrAddress(mUserInputOrPayLocationType, filterByLongitude(transformSnapshotToPayLocations(querySnapshot)));
                distancedList = addDistanceToPayLocations(filteredList);
                sortedList = sortListByDistance(distancedList);
                break;

            case Constants.Firestore.SEARCH_BY_PAY_LOCATION_TYPE:
                filteredList = filterByPayLocationType(mUserInputOrPayLocationType, filterByLongitude(transformSnapshotToPayLocations(querySnapshot)));
                distancedList = addDistanceToPayLocations(filteredList);
                sortedList = sortListByDistance(distancedList);
                break;

            default:
                break;
        }

        return sortedList;
    }

    @Override
    protected void onPostExecute(ArrayList<PayLocation> payLocations) {
        super.onPostExecute(payLocations);
        mPresenter.informToShowSearchResults(payLocations);
    }

    /**
     * *********************************************************************************
     * Helper Methods
     * *********************************************************************************
     */
    private ArrayList<PayLocation> transformSnapshotToPayLocations(QuerySnapshot querySnapshot) {
        List<DocumentSnapshot> documentSnapshots = querySnapshot.getDocuments();
        ArrayList<PayLocation> payLocationList = new ArrayList<>();

        for (DocumentSnapshot documentSnapshot : documentSnapshots) {
            PayLocation payLocation = documentSnapshot.toObject(PayLocation.class);
            if (payLocation != null) {
                payLocationList.add(payLocation);
            }
        }

        return payLocationList;
    }

    private ArrayList<PayLocation> filterByLongitude(ArrayList<PayLocation> payLocations) {
        ArrayList<PayLocation> filteredList = new ArrayList<>();

        for (PayLocation payLocation : payLocations) {
            if (payLocation.getLocationLongitude() >= (WhichPay.getCurrentLocation().getLongitude() - 0.025)
                    && payLocation.getLocationLongitude() <= (WhichPay.getCurrentLocation().getLongitude() + 0.025)) {
                filteredList.add(payLocation);
            }
        }

        return filterByPayLocationMethod(filteredList);
    }

    private ArrayList<PayLocation> filterByNameOrAddress(String userInput, ArrayList<PayLocation> payLocations) {
        ArrayList<PayLocation> filteredList = new ArrayList<>();

        for (PayLocation payLocation : payLocations) {
            if(payLocation.getPayLocationName().toUpperCase().contains(userInput.toUpperCase())) {
                filteredList.add(payLocation);
            }
        }

        return filteredList;
    }

    private ArrayList<PayLocation> filterByPayLocationType(String locationType, ArrayList<PayLocation> payLocations) {
        ArrayList<PayLocation> filteredList = new ArrayList<>();

        for (PayLocation payLocation : payLocations) {
            if(payLocation.getPayLocationType().toUpperCase().contains(locationType.toUpperCase())) {
                filteredList.add(payLocation);
            }
        }

        return filteredList;
    }

    private ArrayList<PayLocation> filterByPayLocationMethod(ArrayList<PayLocation> payLocations) {
        ArrayList<PayLocation> filteredList = new ArrayList<>();

        SharedPreferences sharedPref = WhichPay.getAppContext().getSharedPreferences(Constants.SharedPreferences.PAY_TYPE_SETTINGS, Context.MODE_PRIVATE);

        for (PayLocation payLocation : payLocations) {
            if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_APPLE_PAY, true)) {
                if(stringIsTrueOrFalse(payLocation.getPayLocationUseApplePay())) {
                    Log.d("filterrrrr", "filterByPayLocationMethod: sharedPref: " + sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_APPLE_PAY, true));
                    Log.d("filterrrrr", "filterByPayLocationMethod: payLocation: " + stringIsTrueOrFalse(payLocation.getPayLocationUseApplePay()));

                    filteredList.add(payLocation);
                }

            } else if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_GOOGLE_PAY, true)) {
                if(stringIsTrueOrFalse(payLocation.getPayLocationUseGooglePay())) {
                    filteredList.add(payLocation);
                }

            } else if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_SAMSUNG_PAY, true)) {
                if(stringIsTrueOrFalse(payLocation.getPaylocationUseSamsungPay())) {
                    filteredList.add(payLocation);
                }

            } else if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_LINE_PAY, true)) {
                if(stringIsTrueOrFalse(payLocation.getPayLocationUseLinePay())) {
                    filteredList.add(payLocation);
                }

            } else if(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_JKO_PAY, true)) {
                if(stringIsTrueOrFalse(payLocation.getPayLocationUseJkoPay())) {
                    filteredList.add(payLocation);
                }
            }
        }

        return filteredList;
    }

    private ArrayList<PayLocation> addDistanceToPayLocations(ArrayList<PayLocation> list) {
        for (PayLocation payLocation : list) {
            Location location = new Location(payLocation.getPayLocationName());
            location.setLatitude(payLocation.getLocationLatitude());
            location.setLongitude(payLocation.getLocationLongitude());

            payLocation.setLocationDistance(WhichPay.getCurrentLocation().distanceTo(location));
        }

        return list;
    }

    private ArrayList<PayLocation> sortListByDistance(ArrayList<PayLocation> list) {
        Collections.sort(list, Comparators.DISTANCE);

        return list;
    }

    private static class Comparators {

        private static Comparator<PayLocation> DISTANCE = new Comparator<PayLocation>() {
            @Override
            public int compare(PayLocation o1, PayLocation o2) {
                if ((o1.getLocationDistance() == -1) && (o2.getLocationDistance() == -1)) {
                    return 0;

                } else if ((o1.getLocationDistance() == -1) && (o2.getLocationDistance() != -1)) {
                    return 1;

                } else if ((o1.getLocationDistance() != -1) && (o2.getLocationDistance() == -1)) {
                    return -1;

                } else if (o1.getLocationDistance() > o2.getLocationDistance()) {
                    return 1;

                } else if (o1.getLocationDistance() < o2.getLocationDistance()) {
                    return -1;

                } else {
                    return 0;
                }
            }
        };
    }

    static boolean stringIsTrueOrFalse(String string) {
        switch (string.toUpperCase()) {
            case "Y":
                return true;
            case "N":
                return false;
            default:
                return false;
        }
    }
}
