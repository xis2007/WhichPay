package com.whichpay.whichpay.fragments.searching;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.objects.PayLocation;
import com.whichpay.whichpay.recyclerview.adapters.SearchingResultsAdapter;
import com.whichpay.whichpay.recyclerview.decoration.SearchResultsItemDecoration;
import com.whichpay.whichpay.util.ImeUtil;

import java.util.ArrayList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchingFragment extends Fragment implements SearchingContract.View {
    private SearchingContract.Presenter mSearchingPresenter;

    private SearchView mSearchView;
    private Button mButtonCancelSearch;
    private TextView mTextNearbyType;

    private RecyclerView mSearchedResultsRecyclerView;
    private SearchingResultsAdapter mSearchingResultsAdapter;

    public SearchingFragment() {
        // Required empty public constructor
    }

    public static SearchingFragment newInstance() {
        return new SearchingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchingResultsAdapter = new SearchingResultsAdapter(this, mSearchingPresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searching, container, false);

        initViews(rootView);
        initRecyclerView(rootView);

        return rootView;
    }

    private void initViews(View rootView) {
        mSearchView = rootView.findViewById(R.id.searchView_searching);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setQueryHint("Search by Name or Address");
        mSearchView.setQueryRefinementEnabled(true);
        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("focussssstext", "onFocusChange: focus " + hasFocus);
                if (hasFocus) {
                    enterSearchState();
                } else {
                    exitSearchState();
                }
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchingPresenter.searchByPayLocationNameOrAddress(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        mButtonCancelSearch = rootView.findViewById(R.id.button_cancel_search_searching);
        mButtonCancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitSearchState();
            }
        });

        mTextNearbyType = rootView.findViewById(R.id.text_title_nearby_type);

        exitSearchState();
    }

    private void initRecyclerView(View rootView) {
        mSearchedResultsRecyclerView = rootView.findViewById(R.id.recyclerView_searching);
        mSearchedResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchedResultsRecyclerView.setAdapter(mSearchingResultsAdapter);
        mSearchedResultsRecyclerView.addItemDecoration(new SearchResultsItemDecoration());

        OverScrollDecoratorHelper.setUpOverScroll(mSearchedResultsRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    private void enterSearchState() {
        mButtonCancelSearch.setVisibility(View.VISIBLE);
    }

    private void exitSearchState() {
        mSearchView.clearFocus();
        new ImeUtil(getActivity()).hideSoftKeyboard();
        mButtonCancelSearch.setVisibility(View.GONE);
    }

    @Override
    public void showSearchResults(ArrayList<PayLocation> payLocations) {
        mSearchingResultsAdapter.swapList(payLocations);
    }

    @Override
    public void setSearchViewEnabled(boolean isEnabled, String payLocationsType) {
        if (isEnabled) {
            mSearchView.setVisibility(View.VISIBLE);
            mButtonCancelSearch.setVisibility(View.VISIBLE);
            mTextNearbyType.setVisibility(View.GONE);
            exitSearchState();

        } else {
            mSearchView.setVisibility(View.GONE);
            mButtonCancelSearch.setVisibility(View.GONE);
            mTextNearbyType.setVisibility(View.VISIBLE);
            mTextNearbyType.setText(payLocationsType);
        }
    }

    @Override
    public void setPresenter(SearchingContract.Presenter presenter) {
        mSearchingPresenter = checkNotNull(presenter);
    }
}
