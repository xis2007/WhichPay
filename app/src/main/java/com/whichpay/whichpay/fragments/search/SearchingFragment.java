package com.whichpay.whichpay.fragments.search;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whichpay.whichpay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchingFragment extends Fragment implements SearchingContract.View {

    public SearchingFragment() {
        // Required empty public constructor
    }

    public static SearchingFragment newInstance() {
        return new SearchingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_searching, container, false);
    }

    @Override
    public void showSearchResults() {

    }

    @Override
    public void setPresenter(SearchingContract.Presenter presenter) {

    }
}
