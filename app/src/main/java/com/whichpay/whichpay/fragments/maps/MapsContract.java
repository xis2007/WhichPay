package com.whichpay.whichpay.fragments.maps;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;

public interface MapsContract {
    interface View extends BaseView<Presenter> {
        void moveToSelectedLocationFromSearchedList();
    }

    interface Presenter extends BasePresenter {
        void transToExplorePage();
    }


}
