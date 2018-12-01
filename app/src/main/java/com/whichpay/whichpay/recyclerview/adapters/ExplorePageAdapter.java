package com.whichpay.whichpay.recyclerview.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.fragments.explore.ExplorePresenter;

public class ExplorePageAdapter extends RecyclerView.Adapter {
    private ExplorePresenter mExplorePresenter;

    public ExplorePageAdapter (ExplorePresenter explorePresenter) {
        mExplorePresenter = explorePresenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case Constants.ItemViewType.ExplorePage.PAGER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager_explore, parent, false);
                view.setVisibility(View.INVISIBLE);
                return new ExplorePagerAdapter(view);

            case Constants.ItemViewType.ExplorePage.NEARBY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_explore, parent, false);
                view.setVisibility(View.VISIBLE);
                return new ExploreNearbyAdapter(view);

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_explore, parent, false);
                view.setVisibility(View.VISIBLE);
                return new ExploreNearbyAdapter(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExplorePagerAdapter) {

        } else {
            switch (position) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return Constants.ItemViewType.ExplorePage.PAGER;

            default:
                return Constants.ItemViewType.ExplorePage.NEARBY;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ExplorePagerAdapter extends RecyclerView.ViewHolder {


        ExplorePagerAdapter (View itemView) {
            super(itemView);

        }

    }

    public class ExploreNearbyAdapter extends RecyclerView.ViewHolder {
        ExploreNearbyAdapter(View itemView) {
            super(itemView);
        }
    }
}
