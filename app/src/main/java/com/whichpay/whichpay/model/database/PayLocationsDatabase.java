package com.whichpay.whichpay.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;

public class PayLocationsDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WhichPay.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "WhichPayLocations";

    public static final String _ID = BaseColumns._ID;
    public static final String PAY_LOCATIONS_PLACE_ID = "place_id";
    public static final String PAY_LOCATIONS_NAME = "place_name";
    public static final String PAY_LOCATIONS_BRANCH = "place_branch";
    public static final String PAY_LOCATIONS_TYPE = "place_type";
    public static final String PAY_LOCATIONS_ADDRESS = "place_address";
    public static final String PAY_LOCATIONS_DESCRIPTION = "place_description";
    public static final String PAY_LOCATIONS_LATITUTDE = "lat";
    public static final String PAY_LOCATIONS_LONGITUDE = "lng";
    public static final String PAY_LOCATIONS_USE_APPLEPAY = "apple_pay";
    public static final String PAY_LOCATIONS_USE_GOOGLEPAY = "google_pay";
    public static final String PAY_LOCATIONS_USE_SAMSUNGPAY = "samsung_pay";
    public static final String PAY_LOCATIONS_USE_LINEPAY = "line_pay";
    public static final String PAY_LOCATIONS_USE_JKOPAY = "jko_pay";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PAY_LOCATIONS_PLACE_ID + " INTEGER, "
            + PAY_LOCATIONS_NAME + " TEXT NOT NULL, "
            + PAY_LOCATIONS_BRANCH + " TEXT, "
            + PAY_LOCATIONS_TYPE + " TEXT NOT NULL, "
            + PAY_LOCATIONS_ADDRESS + " TEXT, "
            + PAY_LOCATIONS_DESCRIPTION + " TEXT, "
            + PAY_LOCATIONS_LATITUTDE + " REAL NOT NULL, "
            + PAY_LOCATIONS_LONGITUDE + " REAL NOT NULL, "
            + PAY_LOCATIONS_USE_APPLEPAY + " TEXT NOT NULL, "
            + PAY_LOCATIONS_USE_GOOGLEPAY + " TEXT NOT NULL, "
            + PAY_LOCATIONS_USE_SAMSUNGPAY + " TEXT NOT NULL, "
            + PAY_LOCATIONS_USE_LINEPAY + " TEXT NOT NULL, "
            + PAY_LOCATIONS_USE_JKOPAY + " TEXT NOT NULL) ";


    public static final String CAFES_DRINKS = "Cafe&Drink";
    public static final String DINING = "Dining";
    public static final String SHOPPING = "Shopping";
    public static final String SUPERMARKETS = "Supermarket";
    public static final String BEAUTIES = "Beauty";
    public static final String TRAVEL_ENTERTAIN = "Travel&Entertain";


    public PayLocationsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public Cursor getData(int fromIndex) {
        int indexIncrementation = 113;
        SQLiteDatabase db = getReadableDatabase();

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);

        String selection = PAY_LOCATIONS_PLACE_ID + " BETWEEN ? AND ?";
        String[] selectionArgs = {String.valueOf(fromIndex + 1), String.valueOf(fromIndex + indexIncrementation)};

        return queryBuilder.query(db, null, selection, selectionArgs, null, null, null);
    }
}
