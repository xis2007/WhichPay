package com.whichpay.whichpay.fragments.searching;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;

public interface SearchingContract {
    interface View extends BaseView<Presenter> {
        void showSearchResults();
    }

    interface Presenter extends BasePresenter {
        void informToShowSearchResults();
    }
}
