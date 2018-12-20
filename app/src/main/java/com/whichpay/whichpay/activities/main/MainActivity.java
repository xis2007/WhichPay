package com.whichpay.whichpay.activities.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.activities.base.BaseActivity;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.popup.UpdateAppDialog;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends BaseActivity implements MainContract.View {
    private MainContract.Presenter mMainPresenter;

    private AHBottomNavigation mBottomNavigation;

    private ConstraintLayout mLoadingLayout;
    private TextView mTextHintLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    public void showUpdateRequirementDialog() {
        new UpdateAppDialog(this).show();
    }

    @Override
    public void showExplorePageUi() {

    }

    @Override
    public void showSearchingPageUi(int searchType, String payLocationsType) {

    }

    @Override
    public void showNearbyResultsPageUi() {

    }

    @Override
    public void showSettingsPageUi() {

    }

    @Override
    public void showLoadingUi(String loadingHint) {
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingUi() {
        mLoadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void showNoNetworkAlert() {

    }

    @Override
    public void showNoGpsAlert() {

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mMainPresenter = checkNotNull(presenter);
    }


    /**
     * ***********************************************************************************
     * Initiation at App launch
     * ***********************************************************************************
     */
    private void init() {
        initViews();
        initPresenter();
        mMainPresenter.registerReceivers();
        checkSelfPermissions();
    }

    private void initViews() {
        initLoadingView();
        initBottomNavigation();
    }

    private void initLoadingView() {
        mLoadingLayout = findViewById(R.id.layout_loading);
        mTextHintLoading = findViewById(R.id.text_hint_loading);
        mTextHintLoading.setText("");
    }

    private void initBottomNavigation() {
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.color_bottom_nav);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_main);
        navigationAdapter.setupWithBottomNavigation(mBottomNavigation, tabColors);

        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        mBottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimary));
        mBottomNavigation.setInactiveColor(getResources().getColor(R.color.grey40));

        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case Constants.BottomNavPosition.POSITION_HOME:
                        mMainPresenter.transToExplorePage();
                        break;

                    case Constants.BottomNavPosition.POSITION_SEARCH:
                        mMainPresenter.transToSearchingPage(Constants.SearchType.TYPE_SEARCH_NAME_OR_ADDRESS, null);
                        break;

                    case Constants.BottomNavPosition.POSITION_SETTINGS:
                        mMainPresenter.transToSettingsPage();
                        break;

                }
                return true;
            }
        });
    }

    private void initPresenter() {
        mMainPresenter = new MainPresenter(this, getFragmentManager());
        mMainPresenter.start();
    }


    /**
     * ***********************************************************************************
     * Called only to update data on Firestore
     * Users should not be able to use this method
     * ***********************************************************************************
     */
    int beginningIndex = 9000;
    int stoppingIndex = 9000;

    public void updateFirestoreData() {

//        DataUpdater dataUpdater = new DataUpdater(this);
//
//        ArrayList<PayLocation> payLocations = dataUpdater.getDataFromDatabase(beginningIndex);
//        dataUpdater.updateToFirestore(payLocations);
    }

    public void incrementUpdateIndexAndUpdateAgain(int updateAmount) {
        beginningIndex += updateAmount;
        if(beginningIndex < stoppingIndex) {
            updateFirestoreData();
        }
    }

    /**
     * ***********************************************************************************
     * Activity Lifecycle
     * ***********************************************************************************
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.checkIfAppUpdateIsRequired();
    }

    @Override
    protected void onDestroy() {
//        stopService(new Intent(MainActivity.this, LocationService.class));
        mMainPresenter.stopLocationService();
        mMainPresenter.unregisterReceivers();
        super.onDestroy();
    }

    /**
     * ***********************************************************************************
     * Permission Checks
     * ***********************************************************************************
     */
    private void checkSelfPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setMessage("This App requires location data to provide the best services")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        Constants.Permissions.PERMISSIONS_REQUEST_FINE_LOCATION);
                            }
                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        })
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION},
                        Constants.Permissions.PERMISSIONS_REQUEST_FINE_LOCATION);
            }

        } else {
            mMainPresenter.startLocationService();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Constants.Permissions.PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMainPresenter.startLocationService();
                }
            }
        }
    }
}
