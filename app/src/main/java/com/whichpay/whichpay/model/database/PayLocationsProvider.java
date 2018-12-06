//package com.whichpay.whichpay.model.database;
//
//import android.content.ContentProvider;
//import android.content.ContentValues;
//import android.content.UriMatcher;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteQueryBuilder;
//import android.net.Uri;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//
//import java.io.IOException;
//
///**
// * Created by WhichPay on 10/29/2017.
// *
// * Provider for the TaskTimer App. This is the only that knows about {@link PayLocationsDatabase}
// */
//
//public class PayLocationsProvider extends ContentProvider {
////    private static final String TAG = "PayLocationsProvider";
//
//    private PayLocationsDatabase mOpenHelper;
//    public static final UriMatcher sUriMatcher = buildUriMatcher();
//
//    static final String CONTENT_AUTHORITY = "com.whichpay.whichpay.provider";
//    public static final Uri CONTENT_AUTHORITY_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
//
//    public static final int LOCATIONS = 100;
//    public static final int LOCATIONS_ID = 101;
//
//    private static UriMatcher buildUriMatcher() {
//        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//
//        uriMatcher.addURI(CONTENT_AUTHORITY, PayLocationsContract.TABLE_NAME, LOCATIONS);
//        uriMatcher.addURI(CONTENT_AUTHORITY, PayLocationsContract.TABLE_NAME + "/#", LOCATIONS_ID);
//
//        return uriMatcher;
//    }
//
//    @Override
//    public boolean onCreate() {
//        try {
//            mOpenHelper = PayLocationsDatabase.getInstance(getContext());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//
//    @Nullable
//    @Override
//    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
////        Log.d(TAG, "query: called with Uri = " + uri);
//        int match = sUriMatcher.match(uri);
////        Log.d(TAG, "query: match is: " + match);
//
//        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//
//        switch(match) {
//            case LOCATIONS:
//                queryBuilder.setTables(PayLocationsContract.TABLE_NAME);
//                break;
//
//            case LOCATIONS_ID:
//                queryBuilder.setTables(PayLocationsContract.TABLE_NAME);
//                long locationId = PayLocationsContract.getLocationId(uri);
//                queryBuilder.appendWhere(PayLocationsContract.Columns._ID + " = " + locationId);
//                break;
//
//            default:
//                throw new IllegalArgumentException("Unknown Uri " + uri);
//        }
////        mOpenHelper.getDatabaseName();
////        File outFile = new File(Environment.getDataDirectory(), outFileName);
////        outFile.setWritable(true);
////        SQLiteDatabase.openDatabase(outFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
//
//        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
////        Log.d(TAG, "query: goodoo" + db.toString());
////        Log.d(TAG, "query: goodoo" + projection.toString());
////        Log.d(TAG, "query: goodoo" + selection.toString());
////        Log.d(TAG, "query: goodoo" + selectionArgs.toString());
//        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
//        cursor.setNotificationUri(getContext().getContentResolver(), uri);
//        return cursor;
//    }
//
//    @Nullable
//    @Override
//    public String getType(@NonNull Uri uri) {
//        final int match = sUriMatcher.match(uri);
//        switch (match) {
//            case LOCATIONS:
//                return PayLocationsContract.CONTENT_TYPE;
//
//            case LOCATIONS_ID:
//                return PayLocationsContract.CONTENT_ITEM_TYPE;
//
//            default:
//                throw new IllegalArgumentException("Unknown Uri " + uri);
//        }
//    }
//
//    @Nullable
//    @Override
//    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
////        Log.d(TAG, "insert: starts");
//        int match  = sUriMatcher.match(uri);
////        Log.d(TAG, "insert: match is " + match);
//
//        final SQLiteDatabase db;
//
//        Uri returnUri;
//        long recordId;
//
//        switch (match) {
//            case LOCATIONS:
//                db = mOpenHelper.getWritableDatabase();
//                recordId = db.insert(PayLocationsContract.TABLE_NAME, null, values);
//                if(recordId >= 0) {
//                    returnUri = PayLocationsContract.buildUri(recordId);
//                } else {
//                    throw new android.database.SQLException("Failed to insert into " + uri.toString());
//                }
//                break;
//
//            default:
//                throw new IllegalArgumentException("Unknown uri: " + uri);
//        }
//        if(recordId >=0) {
//            //something was inserted
////            Log.d(TAG, "insert: setting notifyChanged with uri: " + uri);
//            getContext().getContentResolver().notifyChange(uri, null);
//        } else {
////            Log.d(TAG, "insert: nothing inserted");
//        }
////        Log.d(TAG, "Exiting insert: returning " + returnUri);
//        return returnUri;
//    }
//
//    @Override
//    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
////        Log.d(TAG, "delete: starts");
//        int match = sUriMatcher.match(uri);
////        Log.d(TAG, "delete: match is: " + match);
//
//        final SQLiteDatabase db;
//        int count;
//
//        String selectionCriteria;
//
//        switch (match) {
//            case LOCATIONS:
//                db = mOpenHelper.getWritableDatabase();
//                count = db.delete(PayLocationsContract.TABLE_NAME, selection, selectionArgs);
//                break;
//
//            case LOCATIONS_ID:
//                db = mOpenHelper.getWritableDatabase();
//                long locationId = PayLocationsContract.getLocationId(uri);
//                selectionCriteria = PayLocationsContract.Columns._ID + " = " + locationId;
//                if((selection != null) && (selection.length()>0)) {
//                    selectionCriteria += "AND (" + selection + ")";
//                }
//
//                count = db.delete(PayLocationsContract.TABLE_NAME, selectionCriteria, selectionArgs);
//                break;
//
//            default:
//                throw new IllegalArgumentException("Unknown uri: " + uri);
//        }
//        if(count > 0) {
//            //something was deleted
////            Log.d(TAG, "delete: Setting notifyChange with: " + uri);
//            getContext().getContentResolver().notifyChange(uri, null);
//        } else {
////            Log.d(TAG, "delete: nothing was deleted");
//        }
////        Log.d(TAG, "delete: Exiting delete, returning " + count);
//        return count;
//    }
//
//    @Override
//    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
////        Log.d(TAG, "update: starts");
//        int match = sUriMatcher.match(uri);
////        Log.d(TAG, "update: match is: " + match);
//
//        final SQLiteDatabase db;
//        int count;
//
//        String selectionCriteria;
//
//        switch (match) {
//            case LOCATIONS:
//                db = mOpenHelper.getWritableDatabase();
//                count = db.update(PayLocationsContract.TABLE_NAME, values, selection, selectionArgs);
//                break;
//
//            case LOCATIONS_ID:
//                db = mOpenHelper.getWritableDatabase();
//                long locationId = PayLocationsContract.getLocationId(uri);
//                selectionCriteria = PayLocationsContract.Columns._ID + " = " + locationId;
//
//                if((selection != null) && (selection.length() >=0)) {
//                    selectionCriteria += "AND (" + selection + ")";
//                }
//                count = db.update(PayLocationsContract.TABLE_NAME, values, selectionCriteria, selectionArgs);
//                break;
//
//            default:
//                throw new IllegalArgumentException("Unknown uri: " + uri);
//        }
//        if(count > 0) {
//            //something was updated
////            Log.d(TAG, "update: Setting notifyChange with: " + uri);
//            getContext().getContentResolver().notifyChange(uri, null);
//        } else {
////            Log.d(TAG, "update: nothing updated");
//        }
////        Log.d(TAG, "update: Exiting, returning: " + count);
//        return count;
//    }
//}
