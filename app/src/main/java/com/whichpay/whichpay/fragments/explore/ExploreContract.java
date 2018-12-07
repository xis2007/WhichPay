package com.whichpay.whichpay.fragments.explore;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;

public interface ExploreContract {
    interface View extends BaseView<Presenter> {
        void showNearbyResults();
    }

    interface Presenter extends BasePresenter {
        void informToShowNearbyResults(int searchType, String payLocationsType);
    }
}
