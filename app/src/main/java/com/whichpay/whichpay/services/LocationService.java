package com.whichpay.whichpay.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.whichpay.whichpay.activities.main.MainActivity;
import com.whichpay.whichpay.contants.Constants;

public class LocationService extends Service {
    private Intent mLocationBroadcastIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupIntentComponentsForBroadcasts();
        listenToCurrentLocationChanges();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("current locationnnnn", "onDestroy: service stopped");
        super.onDestroy();
    }


    private void setupIntentComponentsForBroadcasts() {
        mLocationBroadcastIntent = new Intent(Constants.ReceiverFilters.FILTER_LOCATION);
    }

    private void listenToCurrentLocationChanges() {
        LocationManager locationManager = (LocationManager) getSystemService(MainActivity.LOCATION_SERVICE);

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30 * 1000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    mLocationBroadcastIntent.removeExtra(Constants.LocationIntentExtras.LOCATION_LOC_LAT);
                    mLocationBroadcastIntent.removeExtra(Constants.LocationIntentExtras.LOCATION_LOC_LNG);

                    mLocationBroadcastIntent.putExtra(Constants.LocationIntentExtras.LOCATION_LOC_LAT, location.getLatitude());
                    mLocationBroadcastIntent.putExtra(Constants.LocationIntentExtras.LOCATION_LOC_LNG, location.getLongitude());
                    sendBroadcast(mLocationBroadcastIntent);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        } catch (java.lang.SecurityException ex) {
            Log.i("GPS locationnnnn", "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d("GPS Locationnnnn", "gps provider does not exist " + ex.getMessage());
        }


        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30 * 1000, 10, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    mLocationBroadcastIntent.removeExtra(Constants.LocationIntentExtras.LOCATION_LOC_LAT);
                    mLocationBroadcastIntent.removeExtra(Constants.LocationIntentExtras.LOCATION_LOC_LNG);

                    mLocationBroadcastIntent.putExtra(Constants.LocationIntentExtras.LOCATION_LOC_LAT, location.getLatitude());
                    mLocationBroadcastIntent.putExtra(Constants.LocationIntentExtras.LOCATION_LOC_LNG, location.getLongitude());
                    sendBroadcast(mLocationBroadcastIntent);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        } catch (java.lang.SecurityException ex) {
            Log.i("Network Locationnnnn", "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d("Network Locationnnnn", "gps provider does not exist " + ex.getMessage());
        }


//        try {
//            Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (loc != null) {
//                double lat = loc.getLatitude();
//                Log.d("GPSsss", "latitude: " + lat);
//                double lng = loc.getLongitude();
//                Log.d("GPSsss", "longitude: " + lng);
//            }
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        }
    }
}
