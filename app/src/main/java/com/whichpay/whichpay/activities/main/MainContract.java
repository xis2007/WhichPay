package com.whichpay.whichpay.activities.main;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showUpdateRequirementDialog();

        // home page
        void showHomePageUi();

        // search page
        void showSearchingPageUi();

        // nearby results page
        void showNearbyResultsPageUi();

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
        void transToHomePage();

        // search page
        void transToSearchingPage();

        // nearby results page
        void transToNearbyResultsPage();

        // settings
        void transToSettingsPage();

        // loading Ui
        void isLoading(String loadingHint);

        void isNotLoading();

        // other messages
        void promptNoNetworkAlert();

        void promptNoGpsAlert();

    }


}
