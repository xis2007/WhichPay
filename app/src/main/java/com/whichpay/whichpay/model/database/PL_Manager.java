//package com.whichpay.whichpay.model.database;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.ContentResolver;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AlertDialog;
//import android.widget.Toast;
//
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.model.LatLng;
//import com.whichpay.whichpay.R;
//import com.whichpay.whichpay.objects.PayLocation;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
///**
// * Created by Justin on 1/1/2018.
// */
//
//public class PL_Manager {
////    private static final String TAG = "PL_Manager";
//
//    static Location mLastLocation = null;
//    static List<PayLocation> mPayLocations = null;
//
//    static LatLng defaultLatLng = new LatLng(25.105497, 121.597366);
//
//
//    static LocationManager mLocationManager;
//    static LocationListener mLocationListener;
//    private static Geocoder mGeocoder;
//
//    public PL_Manager() {
//    }
//
//    public static Location getLastKnownLocation(final Activity activity, GoogleApiClient googleApiClient) {
//        // Check for selfPermission first
//
//        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                showAlertDialog(activity);
//            } else {
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_FINE_LOCATION);
//            }
//        } else {
//            if (mLocationManager == null) {
////                Log.d(TAG, "getLastKnownLocation: locationmanager got in getlastknownlocation");
//                setupLocationServices(activity);
//            }
//
////            Log.d(TAG, "getLastKnownLocation: locationmanager=" + mLocationManager);
//            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//            if (location == null) {
////                Log.d(TAG, "getLastKnownLocation: gps failed");
//                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
////                if (location == null) {
//////                    Log.d(TAG, "getLastKnownLocation: gps null");
////                }
//            }
//
//            if (location == null) {
////                Log.d(TAG, "getLastKnownLocation: network failed");
//                location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//            }
//
//            if (location == null) {
//                location = new Location("");
//                location.setLatitude(25.041194);
//                location.setLongitude(121.543825);
////                Log.d(TAG, "getLastKnownLocation: memory lastknown location failed");
//                if (messageCannotFindCurrentLocationCount <= 1) {
//                    Toast.makeText(activity, R.string.cannot_find_current_location_message, Toast.LENGTH_SHORT).show();
////                    Log.d(TAG, "getLastKnownLocation: default location used");
//                    messageCannotFindCurrentLocationCount++;
//                }
//
//            }
////            if(location == null) {
////                if(mPayLocations == null) {
////                    mPayLocations = new ArrayList<>();
////                }
////
////                if (mLastLocation == null && !mPayLocations.isEmpty()) {
////                    PayLocation firstPayLocation = mPayLocations.get(0);
////                    Location firstLocation = new Location("J");
////                    firstLocation.setLatitude(firstPayLocation.getLocationLatitude());
////                    firstLocation.setLongitude(firstPayLocation.getLocationLongitude());
////                    mLastLocation = firstLocation;
////                }
////            }
////            Log.d(TAG, "getLastKnownLocation: location is: " + location.toString());
//            mLastLocation = location;
//        }
//        return mLastLocation;
//    }
//
//    static List<PayLocation> transformData(Activity activity, List<PayLocation> payLocations, Cursor cursor) {
//        payLocations.clear();
//
//        while (cursor.moveToNext()) {
//            final PayLocation payLocation = new PayLocation(cursor.getLong(cursor.getColumnIndex(PayLocationsContract.Columns._ID)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_PLACE_ID)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_NAME)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_BRANCH)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_TYPE)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_ADDRESS)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_DESCRIPTION)),
//                    cursor.getFloat(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_LATITUTDE)),
//                    cursor.getFloat(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_LONGITUDE)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_USE_APPLEPAY)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_USE_GOOGLEPAY)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_USE_LINEPAY)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_USE_JKOPAY)),
//                    cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_USE_SAMSUNGPAY)));
//
//            if (payLocation != null) {
////                Location lastKnownLocation = new Location("J");
////                double startLat = 25.053247; //lastKnownLocation.getLatitude();
////                double startLong = 121.533268; //lastKnownLocation.getLongitude();
////                lastKnownLocation.setLatitude(startLat);
////                lastKnownLocation.setLongitude(startLong);
//
//                double endLat = payLocation.getLocationLatitude();
//                double endLong = payLocation.getLocationLongitude();
//                Location destLocation = new Location("J");
//                destLocation.setLatitude(endLat);
//                destLocation.setLongitude(endLong);
////                Log.d(TAG, "transformData: destination location is: " + destLocation.toString());
//
////                float distance;
////                if((endLat == -1) || (endLong == -1) || (mLastLocation == null)) {
////                    distance = -1;
////                    payLocation.setLocationDistance(distance);
////                } else {
////                    distance = mLastLocation.distanceTo(destLocation);
////                    payLocation.setLocationDistance(distance);
////                }
//
//                float distance = calcDistance(mLastLocation, destLocation);
////                Log.d(TAG, "transformData: lastlocation is: " + mLastLocation.toString());
////                Log.d(TAG, "transformData: destlocation is: " + destLocation.toString());
//                payLocation.setLocationDistance(distance);
//                payLocations.add(payLocation);
//            }
////            Log.d(TAG, "transformData: transformed" + payLocation.getPayLocationName());
//        }
//
//        Collections.sort(payLocations, Comparators.DISTANCE);
//        mPayLocations = payLocations;
//        return payLocations;
//    }
//
//    static float calcDistance(Location lastLocation, Location destLocation) {
//        float distance;
//        if ((destLocation.getLatitude() == -1) || (destLocation.getLongitude() == -1) || (lastLocation == null)) {
//            distance = -1;
//        } else {
//            distance = lastLocation.distanceTo(destLocation);
//        }
//        return distance;
//    }
//
//    static void updateDatabaseForLatLng(Context context) {
//        mGeocoder = new Geocoder(context);
//        new UpdateDatabaseForLatLng().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, context);
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_FINE_LOCATION:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    mLocationPermissionGranted = true;
//                }
//            default:
////                Log.d(TAG, "onRequestPermissionsResult: access denied");
//        }
//    }
//
//    /**
//     * Private class that runs on multiple thread and updates LatLng for locations
//     */
//    private static class UpdateDatabaseForLatLng extends AsyncTask<Object, Integer, Long> {
//
//        @Override
//        protected Long doInBackground(Object... objects) {
//            Context context = (Context) objects[0];
//            ContentResolver contentResolver = context.getContentResolver();
//
//            //get the full list of data first
//            String[] projection = {PayLocationsContract.Columns._ID,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_PLACE_ID,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_NAME,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_BRANCH,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_TYPE,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_ADDRESS,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_DESCRIPTION,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_LATITUTDE,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_LONGITUDE,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_USE_APPLEPAY,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_USE_GOOGLEPAY,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_USE_LINEPAY,
//                    PayLocationsContract.Columns.PAY_LOCATIONS_USE_JKOPAY,
//                    // TODO
//                    PayLocationsContract.Columns.PAY_LOCATIONS_USE_SAMSUNGPAY};
//
//            Cursor cursor = contentResolver.query(PayLocationsContract.CONTENT_URI, projection, null, null, null);
//
//            List<Address> addressList;
//            while (cursor.moveToNext()) {
//                Integer currentId = cursor.getInt(cursor.getColumnIndex(PayLocationsContract.Columns._ID));
//                String currentName = cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_NAME));
//                String currentAddress = cursor.getString(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_ADDRESS));
//                double currentLat = cursor.getDouble(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_LATITUTDE));
//                double currentLng = cursor.getDouble(cursor.getColumnIndex(PayLocationsContract.Columns.PAY_LOCATIONS_LONGITUDE));
////                Log.d(TAG, "doInBackground: about to update location: " + currentName);
//
//                if (currentLat == -1 || currentLng == -1) {
//                    String selection;
//                    String[] selectionArgs = new String[1];
//                    try {
//                        addressList = mGeocoder.getFromLocationName(currentName, 1);
//                        double searchedLat = -1;
//                        double searchedLng = -1;
//
//                        if (addressList != null && addressList.size() > 0) {
//                            Address addressResult = addressList.get(0);
//                            searchedLat = addressResult.getLatitude();
//                            searchedLng = addressResult.getLongitude();
//                        }
//
//                        // update Latitude to database
//                        ContentValues contentValues = new ContentValues(1);
//                        contentValues.put(PayLocationsContract.Columns.PAY_LOCATIONS_LATITUTDE, searchedLat);
//                        selection = PayLocationsContract.Columns._ID + " = ?";
//                        selectionArgs[0] = String.valueOf(currentId);
//                        contentResolver.update(PayLocationsContract.CONTENT_URI, contentValues, selection, selectionArgs);
//
//                        // update Longitude to database
//                        contentValues.clear();
//                        contentValues.put(PayLocationsContract.Columns.PAY_LOCATIONS_LONGITUDE, searchedLng);
//                        selection = PayLocationsContract.Columns._ID + " = ?";
//                        selectionArgs[0] = String.valueOf(currentId);
//                        contentResolver.update(PayLocationsContract.CONTENT_URI, contentValues, selection, selectionArgs);
//
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            cursor.close();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Long aLong) {
////            Log.d(TAG, "onPostExecute: update finished");
//            super.onPostExecute(aLong);
//        }
//    }
//
//    private static class Comparators {
//
//        private static Comparator<PayLocation> DISTANCE = new Comparator<PayLocation>() {
//            @Override
//            public int compare(PayLocation o1, PayLocation o2) {
//                if ((o1.getDistanceFromDevice() == -1) && (o2.getDistanceFromDevice() == -1)) {
////                    Log.d(TAG, "compare: 1");
//                    return 0;
//                } else if ((o1.getDistanceFromDevice() == -1) && (o2.getDistanceFromDevice() != -1)) {
////                    Log.d(TAG, "compare: 2");
//                    return 1;
//                } else if ((o1.getDistanceFromDevice() != -1) && (o2.getDistanceFromDevice() == -1)) {
////                    Log.d(TAG, "compare: 3");
//                    return -1;
//                } else if (o1.getDistanceFromDevice() > o2.getDistanceFromDevice()) {
////                    Log.d(TAG, "compare: 4");
//                    return 1;
//                } else if (o1.getDistanceFromDevice() < o2.getDistanceFromDevice()) {
////                    Log.d(TAG, "compare: 5");
//                    return -1;
//                } else {
////                    Log.d(TAG, "compare: 6");
//                    return 0;
//                }
//            }
//        };
//    }
//
//    public static void setLocationPermissionGranted(boolean mLocationPermissionGranted) {
//        PL_Manager.mLocationPermissionGranted = mLocationPermissionGranted;
//    }
//
//    static void setupLocationServices(Activity activity) {
////        Log.d(TAG, "setupLocationServices: locationmanager is:" + mLocationManager.toString());
//        if (mLocationManager == null) {
//            mLocationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
////            Log.d(TAG, "setupLocationServices: locationManager set");
//        }
//
//        if (mLocationManager != null) {
////            if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
////                Log.d(TAG, "setupLocationServices: created");
//                mLocationListener = new LocationListener() {
//                    @Override
//                    public void onLocationChanged(Location location) {
//                        mLastLocation = location;
////                        Log.d(TAG, "onLocationChanged: location changed");
//                    }
//
//                    @Override
//                    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                    }
//
//                    @Override
//                    public void onProviderEnabled(String provider) {
//
//                    }
//
//                    @Override
//                    public void onProviderDisabled(String provider) {
//
//                    }
//                };
////            }
//
//            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////            Toast.makeText(activity, R.string.turn_on_locationservices_toast, Toast.LENGTH_LONG).show();
////                Log.d(TAG, "setupLocationServices: failed");
//            } else {
////            Log.d(TAG, "setupLocationServices: locationLister setup success");
//
//                if (mLocationManager != null && mLocationListener != null) {
//                    try {
//                        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
//                        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 20, mLocationListener);
//                        locationServiceAvailable = true;
//
////                        Log.d(TAG, "setupLocationServices: tried");
////                        Log.d(TAG, "setupLocationServices: GPS Provider: " + mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
////                        Log.d(TAG, "setupLocationServices: Network Provider: " + mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
////                        Log.d(TAG, "setupLocationServices: location manager: " + mLocationManager.toString());
////                        Log.d(TAG, "setupLocationServices: location listener= " + mLocationListener.toString());
//
//                    } catch (IllegalArgumentException e) {
//                        e.printStackTrace();
//                        Toast.makeText(activity, R.string.message_turn_on_gps_network, Toast.LENGTH_LONG);
//                    }
//                }
//            }
//        }
//    }
//
//    static void removeLocationServices() {
//        mLocationManager.removeUpdates(mLocationListener);
//        locationServiceAvailable = false;
//    }
//
//    static boolean checkForLocationPermissions(Activity activity) {
//        //check for location permissions
//        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                showAlertDialog(activity);
//            } else {
//                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_FINE_LOCATION);
//            }
//        } else {
//            mLocationPermissionGranted = true;
//            if (mLocationManager == null) {
//                setupLocationServices(activity);
//            }
//        }
//
//        return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private static void showAlertDialog(final Activity activity) {
//        new AlertDialog.Builder(activity, R.style.AppTheme_AlertDialog)
//                .setTitle(R.string.title_location_service_dialog)
//                .setMessage(R.string.message_location_service_dialog)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //Prompt the user once explanation has been shown
//                        ActivityCompat.requestPermissions(activity,
//                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                                PERMISSIONS_REQUEST_FINE_LOCATION);
//                    }
//                })
//                .create()
//                .show();
//    }
//}
//
