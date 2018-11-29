package com.whichpay.whichpay.fragments.home;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showNearbyResults();
    }

    interface Presenter extends BasePresenter {
        void informToShowNearbyResults();
    }
}
