package com.whichpay.whichpay.contants;

public class Constants {
    public class BottomNavPosition {
        public static final int POSITION_HOME = 0;
        public static final int POSITION_SEARCH = 1;
        public static final int POSITION_SETTINGS = 2;
    }

    public class FragmentFlags {
        public static final String FLAG_EXPLORE = "EXPLORE";
        public static final String FLAG_SEARCHING = "SEARCHING";
        public static final String FLAG_SETTINGS = "SETTINGS";
    }

    public class ItemViewType {
        public class ExplorePage {
            public static final int TITLE = 1000;
            public static final int PAGER = 1001;
            public static final int NEARBY = 1002;
        }
    }

    public class Firestore {
        public static final String COLLECTION_PAY_LOCATIONS = "PayLocations";

        public static final String COLLECTION_SETTINGS = "settings";
        public static final String DOCUMENT_UPDATE_APP = "updateApp";

        public static final String KEY_REQUIRED = "required";
        public static final String KEY_REQUIRED_verSION_CODE = "requiredVersionCode";

    }

//    public class DefaultLocation {
//        public static final int LOCATION_KEELUNG_CITY = 10000;
//        public static final int LOCATION_TAIPEI_CITY = 10001;
//        public static final int LOCATION_NEW_TAIPEI_CITY = 10002;
//        public static final int LOCATION_TAOYUAN_COUNTY = 10003;
//        public static final int LOCATION_TAICHUNG_CITY = 10004;
//        public static final int LOCATION_KAOHSIUNG_CITY = 10005;
//    }

    public class SharedPreferences {
        public static final String LOCATION_SETTINGS = "Location Settings";
        public static final String DEFAULT_LOCATION_NAME = "Default Location Name";
        public static final String DEFAULT_LOCATION_LAT = "Default Location Latitude";
        public static final String DEFAULT_LOCATION_LNG = "Default Location Longitude";

        public static final String PAY_TYPE_SETTINGS = "Pay Type Settings";
        public static final String PAY_TYPE_APPLE_PAY = "Apple Pay";
        public static final String PAY_TYPE_GOOGLE_PAY = "Google Pay";
        public static final String PAY_TYPE_SAMSUNG_PAY = "Samsung Pay";
        public static final String PAY_TYPE_LINE_PAY = "Line Pay";
        public static final String PAY_TYPE_JKO_PAY = "Jko Pay";
    }

    public class PayLocationsAttribute {
        public static final String _ID = "_Id";
        public static final String LOCATION_ID= "locationId";
        public static final String LOCATION_NAME= "payLocationName";
        public static final String LOCATION_BRANCH = "paylocationBranch";
        public static final String LOCATION_TYPE = "paylocationType";
        public static final String LOCATION_ADDRESS = "paylocationAddress";
        public static final String LOCATION_DESCRIPTION = "paylocationDescription";
        public static final String LOCATION_LAT = "locationLatitude";
        public static final String LOCATION_LNG = "locationLongitude";
        public static final String LOCATION_USE_APPLE_PAY = "paylocationUseApplePay";
        public static final String LOCATION_USE_GOOGLE_PAY = "paylocationUseAndroidPay";
        public static final String LOCATION_USE_SAMSUNG_PAY = "paylocationUseSamsungPay";
        public static final String LOCATION_USE_LINE_PAY = "paylocationUseLinePay";
        public static final String LOCATION_USE_JKO_PAY = "paylocationUseJkoPay";
    }

    public class PayLocationsType {
        public static final String CAFES_DRINKS = "Cafe&Drink";
        public static final String DINING = "Dining";
        public static final String SHOPPING = "Shopping";
        public static final String SUPERMARKETS = "Supermarket";
        public static final String BEAUTIES = "Beauty";
        public static final String TRAVEL_ENTERTAIN = "Travel&Entertain";
    }

    public class SearchType {
        public static final int TYPE_SEARCH_NAME_OR_ADDRESS = 600;
        public static final int TYPE_SEARCH_LOCATION_TYPE = 601;
    }
}
