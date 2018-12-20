package com.whichpay.whichpay.activities.main;

import android.location.Location;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;
import com.whichpay.whichpay.fragments.searching.SearchingPresenter;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showUpdateRequirementDialog();

        // explore page
        void showExplorePageUi();

        // search page
        void showSearchingPageUi(int searchType, String payLocationsType);

        // nearby results page
        void showMapsPageUi();

        // settings
        void showSettingsPageUi();

        // loading Ui
        void showLoadingUi(String loadingHint);

        void hideLoadingUi();

        // other messages
        void showNoNetworkAlert();

        void showNoGpsAlert();
    }

    interface Presenter extends BasePresenter {
        // main page
        void transToExplorePage();

        // search page
        void transToSearchingPage(int searchType, String payLocationsType);

        // nearby results page
        void transToMapsPage(int positionInList);

        // settings
        void transToSettingsPage();

        // loading Ui
        void isLoading(String loadingHint);

        void isNotLoading();

        // other messages
        void promptNoNetworkAlert();

        void promptNoGpsAlert();

        // getters
        SearchingPresenter getSearchingPresenter();

        // app update
        void checkIfAppUpdateIsRequired();

        void promptUpdateRequirementMessage();

        // location related
        void updateCurrentLocation(Location location);

        void registerReceivers();

        void unregisterReceivers();

        void startLocationService();

        void stopLocationService();
    }


}
