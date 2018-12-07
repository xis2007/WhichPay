package com.whichpay.whichpay.fragments.searching;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;
import com.whichpay.whichpay.objects.PayLocation;

import java.util.ArrayList;

public interface SearchingContract {
    interface View extends BaseView<Presenter> {
        void showSearchResults(ArrayList<PayLocation> payLocations);

        void setSearchViewEnabled(boolean isEnabled, String payLocationsType);
    }

    interface Presenter extends BasePresenter {
        void informToShowSearchResults(ArrayList<PayLocation> payLocations);

        void searchByPayLocationNameOrAddress(String query);

        void searchByPayLocationType(String locationType);
    }
}
