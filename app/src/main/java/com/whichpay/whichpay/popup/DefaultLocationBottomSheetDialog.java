package com.whichpay.whichpay.popup;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.RadioGroup;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.fragments.settings.SettingsContract;

public class DefaultLocationBottomSheetDialog extends BottomSheetDialog {
    SettingsContract.Presenter mSettingsPresenter;

    Location mDefaultLocation;

    public DefaultLocationBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public DefaultLocationBottomSheetDialog(@NonNull Context context, Location defaultLocation, SettingsContract.Presenter settingsPresenter) {
        super(context);
        mDefaultLocation = defaultLocation;
        mSettingsPresenter = settingsPresenter;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        setUpRadioButtons();
    }

    private void setUpRadioButtons() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup_defaultLocation);
        radioGroup.check(getDefaultLocationIndex());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mSettingsPresenter.setNewDefaultLocation(checkedId);
            }
        });
    }

    private int getDefaultLocationIndex() {
        if (mDefaultLocation.getProvider().equals(getContext().getResources().getString(R.string.default_location_keelung_city))) {
            return R.id.radioButton_keelung_city;

        } else if (mDefaultLocation.getProvider().equals(getContext().getResources().getString(R.string.default_location_taipei_city))) {
            return R.id.radioButton_taipei_city;

        } else if (mDefaultLocation.getProvider().equals(getContext().getResources().getString(R.string.default_location_new_taipei_city))) {
            return R.id.radioButton_new_taipei_city;

        } else if (mDefaultLocation.getProvider().equals(getContext().getResources().getString(R.string.default_location_taoyuan_county))) {
            return R.id.radioButton_taoyuan_county;

        } else if (mDefaultLocation.getProvider().equals(getContext().getResources().getString(R.string.default_location_taichung_city))) {
            return R.id.radioButton_taichung_city;

        } else {
            return R.id.radioButton_kaohsiung_city;
        }
    }
}
