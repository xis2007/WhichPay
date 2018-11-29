package com.whichpay.whichpay.activities.main;

import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.activities.base.BaseActivity;
import com.whichpay.whichpay.contants.Constants;

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

    }

    @Override
    public void showHomePageUi() {

    }

    @Override
    public void showSearchingPageUi() {

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
        mBottomNavigation.setInactiveColor(getResources().getColor(R.color.grey));

        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case Constants.BottomNavPosition.POSITION_HOME:
                        mMainPresenter.transToHomePage();
                        break;

                    case Constants.BottomNavPosition.POSITION_SEARCH:
                        mMainPresenter.transToSearchingPage();
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
}
