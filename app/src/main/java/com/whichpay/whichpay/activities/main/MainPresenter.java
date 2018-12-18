package com.whichpay.whichpay.activities.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.fragments.explore.ExploreFragment;
import com.whichpay.whichpay.fragments.explore.ExplorePresenter;
import com.whichpay.whichpay.fragments.searching.SearchingFragment;
import com.whichpay.whichpay.fragments.searching.SearchingPresenter;
import com.whichpay.whichpay.fragments.settings.SettingsFragment;
import com.whichpay.whichpay.fragments.settings.SettingsPresenter;
import com.whichpay.whichpay.model.firestore.RemoteSettingsManager;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mMainView;

    private FragmentManager mFragmentManager;

    // fragments
    private ExploreFragment mExploreFragment;
    private SearchingFragment mSearchingFragment;
    private SettingsFragment mSettingsFragment;

    // presenters
    private ExplorePresenter mExplorePresenter;
    private SearchingPresenter mSearchingPresenter;
    private SettingsPresenter mSettingsPresenter;

    public MainPresenter(MainContract.View mainView, FragmentManager fragmentManager) {
        mMainView = mainView;
        mFragmentManager = fragmentManager;
    }

    @Override
    public void transToExplorePage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mExploreFragment == null) mExploreFragment = ExploreFragment.newInstance();

        if (mSearchingFragment != null) transaction.hide(mSearchingFragment);
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
    public void transToNearbyResultsPage() {

    }

    @Override
    public void transToSettingsPage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mSettingsFragment == null) mSettingsFragment = SettingsFragment.newInstance();

        if (mExploreFragment != null) transaction.hide(mExploreFragment);
        if (mSearchingFragment != null) transaction.hide(mSearchingFragment);

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

    }

    @Override
    public void isNotLoading() {

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
     * Helper Methods
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
