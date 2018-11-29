package com.whichpay.whichpay.activities.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.fragments.home.HomeFragment;
import com.whichpay.whichpay.fragments.home.HomePresenter;
import com.whichpay.whichpay.fragments.search.SearchingFragment;
import com.whichpay.whichpay.fragments.search.SearchingPresenter;
import com.whichpay.whichpay.fragments.settings.SettingsFragment;
import com.whichpay.whichpay.fragments.settings.SettingsPresenter;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mMainView;

    private FragmentManager mFragmentManager;

    // fragments
    private HomeFragment mHomeFragment;
    private SearchingFragment mSearchingFragment;
    private SettingsFragment mSettingsFragment;

    // presenters
    private HomePresenter mHomePresenter;
    private SearchingPresenter mSearchingPresenter;
    private SettingsPresenter mSettingsPresenter;

    public MainPresenter(MainContract.View mainView, FragmentManager fragmentManager) {
        mMainView = mainView;
        mFragmentManager = fragmentManager;
    }

    @Override
    public void transToHomePage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mHomeFragment == null) mHomeFragment = HomeFragment.newInstance();

        if (mSearchingFragment != null) transaction.hide(mSearchingFragment);
        if (mSettingsFragment != null) transaction.hide(mSettingsFragment);

        if (!mHomeFragment.isAdded()) {
            transaction.add(R.id.container_main, mHomeFragment, Constants.FragmentFlags.FLAG_HOME);
        } else {
            transaction.show(mHomeFragment);
        }

        if (mHomePresenter == null) {
            mHomePresenter = new HomePresenter(mHomeFragment);
            mHomePresenter.setMainView(mMainView);
            mHomePresenter.setMainPresenter(this);
        }

        transaction.commit();

        mMainView.showHomePageUi();
    }

    @Override
    public void transToSearchingPage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mSearchingFragment == null) mSearchingFragment = SearchingFragment.newInstance();

        if (mHomeFragment != null) transaction.hide(mHomeFragment);
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

        mMainView.showSearchingPageUi();
    }

    @Override
    public void transToNearbyResultsPage() {

    }

    @Override
    public void transToSettingsPage() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        if (mSettingsFragment == null) mSettingsFragment = SettingsFragment.newInstance();

        if (mHomeFragment != null) transaction.hide(mHomeFragment);
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
    public void start() {
        transToHomePage();
    }

    /**
     * ***********************************************************************************
     * Getters and Setters
     * ***********************************************************************************
     */
}
