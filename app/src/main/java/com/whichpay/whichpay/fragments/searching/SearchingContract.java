package com.whichpay.whichpay.fragments.searching;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;

public interface SearchingContract {
    interface View extends BaseView<Presenter> {
        void showSearchResults();

        void setSearchViewEnabled(boolean isEnabled);
    }

    interface Presenter extends BasePresenter {
        void informToShowSearchResults();
    }
}
