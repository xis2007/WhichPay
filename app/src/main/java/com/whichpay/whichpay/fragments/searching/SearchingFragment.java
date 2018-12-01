package com.whichpay.whichpay.fragments.searching;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.Util.ImeUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchingFragment extends Fragment implements SearchingContract.View {

    private SearchView mSearchView;
    private Button mButtonCancelSearch;

    public SearchingFragment() {
        // Required empty public constructor
    }

    public static SearchingFragment newInstance() {
        return new SearchingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_searching, container, false);

        initViews(rootView);
//        initRecyclerView(rootView);

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

        mButtonCancelSearch = rootView.findViewById(R.id.button_cancel_search_searching);
        mButtonCancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitSearchState();
            }
        });

        exitSearchState();
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
    public void showSearchResults() {

    }

    @Override
    public void setPresenter(SearchingContract.Presenter presenter) {

    }
}
