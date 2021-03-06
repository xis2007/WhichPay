package com.whichpay.whichpay.fragments.settings;

import com.whichpay.whichpay.activities.base.BasePresenter;
import com.whichpay.whichpay.activities.base.BaseView;

public interface SettingsContract {
    interface View extends BaseView<Presenter> {
        void showDefaultLocation(String defaultLocation);

        void showPayLocationTypeSettings();
    }

    interface Presenter extends BasePresenter {
        void setNewDefaultLocation(int defaultLocationIndex);

        void setUserPayLocationTypePref(int viewId, boolean isChecked);
    }
}
