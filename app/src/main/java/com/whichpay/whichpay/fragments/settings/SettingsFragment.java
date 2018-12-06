package com.whichpay.whichpay.fragments.settings;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.popup.DefaultLocationBottomSheetDialog;


public class SettingsFragment extends Fragment implements SettingsContract.View {
    private SettingsContract.Presenter mSettingsPresenter;

    private Button mButtonDefaultLocation;

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
    public void initDefaultLocation() {
        mButtonDefaultLocation.setText(WhichPay.getDefaultLocation().getProvider());
    }

    @Override
    public void showDefaultLocation(String defaultLocation) {
        mButtonDefaultLocation.setText(defaultLocation);
    }
}
