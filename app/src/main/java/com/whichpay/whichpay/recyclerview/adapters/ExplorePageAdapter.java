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
            case Constants.ItemViewType.ExplorePage.TITLE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_explore, parent, false);
                return new ExploreTitleHolder(view);

            case Constants.ItemViewType.ExplorePage.PAGER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pager_explore, parent, false);
                return new ExplorePagerHolder(view);

            case Constants.ItemViewType.ExplorePage.NEARBY:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_explore, parent, false);
                return new ExploreNearbyHolder(view);

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_explore, parent, false);
                return new ExploreNearbyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExploreTitleHolder) {

        } else if (holder instanceof ExplorePagerHolder) {

        } else {
            switch (position) {
                case 2:
                    break;

                case 3:
                    break;

                case 4:
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
                return Constants.ItemViewType.ExplorePage.TITLE;

            case 1:
                return Constants.ItemViewType.ExplorePage.PAGER;

            default:
                return Constants.ItemViewType.ExplorePage.NEARBY;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ExploreTitleHolder extends RecyclerView.ViewHolder {
        ExploreTitleHolder(View itemView) {
            super(itemView);
        }
    }

    public class ExplorePagerHolder extends RecyclerView.ViewHolder {
        ExplorePagerHolder(View itemView) {
            super(itemView);
        }
    }

    public class ExploreNearbyHolder extends RecyclerView.ViewHolder {
        ExploreNearbyHolder(View itemView) {
            super(itemView);
        }
    }
}
