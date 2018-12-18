package com.whichpay.whichpay.activities.main;

import android.os.Bundle;

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

    }

    @Override
    public void hideLoadingUi() {

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
    }

    private void initViews() {
        initBottomNavigation();
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
    protected void onPostResume() {
        super.onPostResume();
        mMainPresenter.checkIfAppUpdateIsRequired();
    }
}
