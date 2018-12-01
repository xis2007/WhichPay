package com.whichpay.whichpay.fragments.explore;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.recyclerview.adapters.ExplorePageAdapter;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

import static com.google.android.gms.common.internal.Preconditions.checkNotNull;

public class ExploreFragment extends Fragment implements ExploreContract.View {
    private ExploreContract.Presenter mExplorePresenter;

    private RecyclerView mPrimaryRecyclerView;
    private RecyclerView.Adapter mPrimaryAdapter;

    public ExploreFragment() {
    }

    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrimaryAdapter = new ExplorePageAdapter((ExplorePresenter) mExplorePresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);

        initRecyclerView(rootView);

        return rootView;
    }

    private void initRecyclerView(View rootView) {
        mPrimaryRecyclerView = rootView.findViewById(R.id.recyclerView_primary_explore);
        mPrimaryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPrimaryRecyclerView.setAdapter(mPrimaryAdapter);

        OverScrollDecoratorHelper.setUpOverScroll(mPrimaryRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    @Override
    public void showNearbyResults() {

    }

    @Override
    public void setPresenter(ExploreContract.Presenter presenter) {
        mExplorePresenter = checkNotNull(presenter);
    }
}
