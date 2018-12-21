package com.whichpay.whichpay.activities.main;

import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.broadcastreceivers.LocationReceiver;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.fragments.explore.ExploreFragment;
import com.whichpay.whichpay.fragments.explore.ExplorePresenter;
import com.whichpay.whichpay.fragments.maps.MapsFragment;
import com.whichpay.whichpay.fragments.maps.MapsPresenter;
import com.whichpay.whichpay.fragments.searching.SearchingFragment;
import com.whichpay.whichpay.fragments.searching.SearchingPresenter;
import com.whichpay.whichpay.fragments.settings.SettingsFragment;
import com.whichpay.whichpay.fragments.settings.SettingsPresenter;
import com.whichpay.whichpay.model.firestore.RemoteSettingsManager;
import com.whichpay.whichpay.services.LocationService;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mMainView;

    private FragmentManager mFragmentManager;

    // fragments
    private ExploreFragment mExploreFragment;
    private SearchingFragment mSearchingFragment;
    private SettingsFragment mSettingsFragment;
    private MapsFragment mMapsFragment;

    // presenters
    private ExplorePresenter mExplorePresenter;
    private SearchingPresenter mSearchingPresenter;
    private SettingsPresenter mSettingsPresenter;
    private MapsPresenter mMapsPresenter;

    // BroadcastReceiver
    private LocationReceiver mLocationReceiver;

    public MainPresenter(MainContract.View mainView, FragmentManager fragmentManager) {
        mMainView = mainView;
        mFragmentManager = fragmentManager;
    }

    @Override
    public void transToExplorePage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mExploreFragment == null) mExploreFragment = ExploreFragment.newInstance();

        if (mSearchingFragment != null) transaction.hide(mSearchingFragment);
        if (mMapsFragment != null) transaction.hide(mMapsFragment);
        if (mSettingsFragment != null) transaction.hide(mSettingsFragment);

        if (!mExploreFragment.isAdded()) {
            transaction.add(R.id.container_main, mExploreFragment, Constants.FragmentFlags.FLAG_EXPLORE);
        } else {
            transaction.show(mExploreFragment);
        }

        if (mExplorePresenter == null) {
            mExplorePresenter = new ExplorePresenter(mExploreFragment);
            mExplorePresenter.setMainView(mMainView);
            mExplorePresenter.setMainPresenter(this);
        }

        transaction.commit();

        mMainView.showExplorePageUi();
    }

    @Override
    public void transToSearchingPage(int searchType, String payLocationsType) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mSearchingFragment == null) mSearchingFragment = SearchingFragment.newInstance();

        if (mExploreFragment != null) transaction.hide(mExploreFragment);
        if (mMapsFragment != null) transaction.hide(mMapsFragment);
        if (mSettingsFragment != null) transaction.hide(mSettingsFragment);

        if (!mSearchingFragment.isAdded()) {
            transaction.add(R.id.container_main, mSearchingFragment, Constants.FragmentFlags.FLAG_SEARCHING);
        } else {
            transaction.show(mSearchingFragment);
        }

        if (mSearchingPresenter == null) {
            mSearchingPresenter = new SearchingPresenter(mSearchingFragment);
            mSearchingPresenter.setMainView(mMainView);
            mSearchingPresenter.setMainPresenter(this);
        }

        transaction.commit();

        mSearchingPresenter.clearList();

        if(searchType == Constants.SearchType.TYPE_SEARCH_NAME_OR_ADDRESS) {
            mSearchingFragment.setSearchViewEnabled(true, payLocationsType);
        } else {
            mSearchingFragment.setSearchViewEnabled(false, payLocationsType);
        }

        mMainView.showSearchingPageUi(searchType, payLocationsType);
    }

    @Override
    public void transToMapsPage(int positionInList) {
        WhichPay.setPositionOfPayLocationToShow(positionInList);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mMapsFragment == null) mMapsFragment = MapsFragment.newInstance();

        if (mExploreFragment != null) transaction.hide(mExploreFragment);
        if (mSearchingFragment != null) transaction.hide(mSearchingFragment);
        if (mSettingsFragment != null) transaction.hide(mSettingsFragment);

        if (!mMapsFragment.isAdded()) {
            transaction.add(R.id.container_main, mMapsFragment, Constants.FragmentFlags.FLAG_MAPS);
        } else {
            transaction.show(mMapsFragment);
        }

        if (mMapsPresenter == null) {
            mMapsPresenter = new MapsPresenter(mMapsFragment);
            mMapsPresenter.setMainView(mMainView);
            mMapsPresenter.setMainPresenter(this);
        }

        // as maps fragment already exists, do not wait for map to get ready, show the locations right away
        if(mMapsFragment != null && mMapsFragment.isMapReady()) mMapsFragment.moveToSelectedLocationFromSearchedList();


        transaction.commit();

        mMainView.showMapsPageUi();
    }

    @Override
    public void transToSettingsPage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mSettingsFragment == null) mSettingsFragment = SettingsFragment.newInstance();

        if (mExploreFragment != null) transaction.hide(mExploreFragment);
        if (mSearchingFragment != null) transaction.hide(mSearchingFragment);
        if (mMapsFragment != null) transaction.hide(mMapsFragment);

        if (!mSettingsFragment.isAdded()) {
            transaction.add(R.id.container_main, mSettingsFragment, Constants.FragmentFlags.FLAG_SETTINGS);
        } else {
            transaction.show(mSettingsFragment);
        }

        if (mSettingsPresenter == null) {
            mSettingsPresenter = new SettingsPresenter(mSettingsFragment);
            mSettingsPresenter.setMainView(mMainView);
            mSettingsPresenter.setMainPresenter(this);
        }

        transaction.commit();

        mMainView.showSettingsPageUi();
    }

    @Override
    public void isLoading(String loadingHint) {
        mMainView.showLoadingUi(loadingHint);
    }

    @Override
    public void isNotLoading() {
        mMainView.hideLoadingUi();
    }

    @Override
    public void promptNoNetworkAlert() {

    }

    @Override
    public void promptNoGpsAlert() {

    }

    @Override
    public SearchingPresenter getSearchingPresenter() {
        return mSearchingPresenter;
    }

    @Override
    public void checkIfAppUpdateIsRequired() {
        new RemoteSettingsManager((MainActivity) mMainView, this).checkForAppUpdateRequirement();
    }

    @Override
    public void promptUpdateRequirementMessage() {
        mMainView.showUpdateRequirementDialog();
    }

    @Override
    public void updateCurrentLocation(Location currentLocation) {
        WhichPay.updateCurrentLocation(currentLocation);
    }


    @Override
    public void registerReceivers() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ReceiverFilters.FILTER_LOCATION);
        mLocationReceiver = new LocationReceiver(this);
        ((MainActivity) mMainView).registerReceiver(mLocationReceiver, intentFilter);
    }

    @Override
    public void unregisterReceivers() {
        ((MainActivity) mMainView).unregisterReceiver(mLocationReceiver);
    }

    @Override
    public void startLocationService() {
        ((MainActivity) mMainView).startService(new Intent((MainActivity) mMainView, LocationService.class));
    }

    @Override
    public void stopLocationService() {
        ((MainActivity) mMainView).stopService(new Intent((MainActivity) mMainView, LocationService.class));
    }

    @Override
    public void start() {
        // initialize searching fragment at start up to avoid null pointer exception
        initSearchingPage();

        transToExplorePage();
    }

    /**
     * ***********************************************************************************
     * Getters and Setters
     * ***********************************************************************************
     */




    /**
     * ***********************************************************************************
     * Helper/Other Methods
     * ***********************************************************************************
     */
    private void initSearchingPage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mSearchingFragment == null) mSearchingFragment = SearchingFragment.newInstance();

        transaction.add(R.id.container_main, mSearchingFragment, Constants.FragmentFlags.FLAG_SEARCHING);

        if (mSearchingPresenter == null) {
            mSearchingPresenter = new SearchingPresenter(mSearchingFragment);
            mSearchingPresenter.setMainView(mMainView);
            mSearchingPresenter.setMainPresenter(this);
        }

        transaction.commit();
    }
}
