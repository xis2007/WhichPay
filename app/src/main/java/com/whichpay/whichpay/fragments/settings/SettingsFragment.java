package com.whichpay.whichpay.fragments.settings;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.suke.widget.SwitchButton;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.popup.DefaultLocationBottomSheetDialog;


public class SettingsFragment extends Fragment implements SettingsContract.View {
    private SettingsContract.Presenter mSettingsPresenter;

    private Button mButtonDefaultLocation;
    private SwitchButton mSwitchApplePay;
    private SwitchButton mSwitchGooglePay;
    private SwitchButton mSwitchSamsungPay;
    private SwitchButton mSwitchLinePay;
    private SwitchButton mSwitchJkoPay;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        initiateViews(rootView);

        return rootView;
    }

    private void initiateViews(View rootView) {
        mButtonDefaultLocation = rootView.findViewById(R.id.button_default_location);
        mButtonDefaultLocation.setOnClickListener(defaultLocationOnclickListener);

        mSwitchApplePay = rootView.findViewById(R.id.switch_apple_pay);
        mSwitchGooglePay = rootView.findViewById(R.id.switch_google_pay);
        mSwitchSamsungPay = rootView.findViewById(R.id.switch_samsung_pay);
        mSwitchLinePay = rootView.findViewById(R.id.switch_line_pay);
        mSwitchJkoPay = rootView.findViewById(R.id.switch_jko_pay);

        mSwitchApplePay.setOnCheckedChangeListener(switchButtonOnCheckedChangeListener);
        mSwitchGooglePay.setOnCheckedChangeListener(switchButtonOnCheckedChangeListener);
        mSwitchSamsungPay.setOnCheckedChangeListener(switchButtonOnCheckedChangeListener);
        mSwitchLinePay.setOnCheckedChangeListener(switchButtonOnCheckedChangeListener);
        mSwitchJkoPay.setOnCheckedChangeListener(switchButtonOnCheckedChangeListener);


    }

    private View.OnClickListener defaultLocationOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DefaultLocationBottomSheetDialog dialog = new DefaultLocationBottomSheetDialog(getActivity(), WhichPay.getDefaultLocation(), mSettingsPresenter);
            View view = getActivity().getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_default_location, null);
            dialog.setContentView(view);
            dialog.show();
        }
    };

    private SwitchButton.OnCheckedChangeListener switchButtonOnCheckedChangeListener = new SwitchButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            mSettingsPresenter.setUserPayLocationTypePref(view.getId(), isChecked);
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSettingsPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull SettingsContract.Presenter presenter) {
        mSettingsPresenter = presenter;
    }

    @Override
    public void showDefaultLocation(String defaultLocation) {
        mButtonDefaultLocation.setText(defaultLocation);
    }

    @Override
    public void showPayLocationTypeSettings() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences(Constants.SharedPreferences.PAY_TYPE_SETTINGS, Context.MODE_PRIVATE);

        mSwitchApplePay.setChecked(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_APPLE_PAY, true));
        mSwitchGooglePay.setChecked(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_GOOGLE_PAY, true));
        mSwitchSamsungPay.setChecked(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_SAMSUNG_PAY, true));
        mSwitchLinePay.setChecked(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_LINE_PAY, true));
        mSwitchJkoPay.setChecked(sharedPref.getBoolean(Constants.SharedPreferences.PAY_TYPE_JKO_PAY, true));
    }
}
