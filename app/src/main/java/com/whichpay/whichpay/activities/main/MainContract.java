package com.whichpay.whichpay.activities.main;

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
        void transToExplorePage();

        // search page
        void transToSearchingPage(int searchType, String payLocationsType);

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

        // getters
        SearchingPresenter getSearchingPresenter();

        // app update
        void checkIfAppUpdateIsRequired();

        void promptUpdateRequirementMessage();

    }


}
