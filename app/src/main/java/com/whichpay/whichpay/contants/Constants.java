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
}
