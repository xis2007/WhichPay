package com.whichpay.whichpay.recyclerview.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.fragments.explore.ExploreFragment;
import com.whichpay.whichpay.fragments.explore.ExplorePresenter;

public class ExplorePageAdapter extends RecyclerView.Adapter {
    private ExploreFragment mExploreFragment;
    private ExplorePresenter mExplorePresenter;

    public ExplorePageAdapter (ExploreFragment exploreFragment, ExplorePresenter explorePresenter) {
        mExploreFragment = exploreFragment;
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
                    Glide.with(mExploreFragment.getActivity())
                            .load(R.drawable.icon_cafe)
                            .into(((ExploreNearbyHolder) holder).getImageViewLeft());

//                    // TODO refactor
//                    ((ExploreNearbyHolder) holder).getImageViewLeft().setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            new FirestoreDataManager().searchByType(mExplorePresenter, Constants.PayLocationsType.CAFES_DRINKS);
//                        }
//                    });


                    Glide.with(mExploreFragment.getActivity())
                            .load(R.drawable.icon_dining)
                            .into(((ExploreNearbyHolder) holder).getImageViewRight());
                    break;

                case 3:
                    Glide.with(mExploreFragment.getActivity())
                            .load(R.drawable.icon_supermarket)
                            .into(((ExploreNearbyHolder) holder).getImageViewLeft());

                    Glide.with(mExploreFragment.getActivity())
                            .load(R.drawable.icon_shopping)
                            .into(((ExploreNearbyHolder) holder).getImageViewRight());
                    break;

                case 4:
                    Glide.with(mExploreFragment.getActivity())
                            .load(R.drawable.icon_entertain)
                            .into(((ExploreNearbyHolder) holder).getImageViewLeft());

                    Glide.with(mExploreFragment.getActivity())
                            .load(R.drawable.icon_beauty)
                            .into(((ExploreNearbyHolder) holder).getImageViewRight());
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
        ImageView mImageViewLeft;
        ImageView mImageViewRight;

        ExploreNearbyHolder(View itemView) {
            super(itemView);

            mImageViewLeft = itemView.findViewById(R.id.image_left);
            mImageViewRight = itemView.findViewById(R.id.image_right);
        }

        public ImageView getImageViewLeft() {
            return mImageViewLeft;
        }

        public ImageView getImageViewRight() {
            return mImageViewRight;
        }
    }
}
