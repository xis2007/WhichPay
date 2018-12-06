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
    }
}
