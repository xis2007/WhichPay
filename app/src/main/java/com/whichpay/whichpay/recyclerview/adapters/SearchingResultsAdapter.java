package com.whichpay.whichpay.recyclerview.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.fragments.searching.SearchingContract;
import com.whichpay.whichpay.objects.PayLocation;

import java.util.ArrayList;

public class SearchingResultsAdapter extends RecyclerView.Adapter {
    private Context mContext;

    private SearchingContract.View mSearchingView;
    private SearchingContract.Presenter mSearchingPresenter;

    private ArrayList<PayLocation> mPayLocationsList;

    public SearchingResultsAdapter(SearchingContract.View searchingView, SearchingContract.Presenter searchingPresenter) {
        mContext = ((Fragment) searchingView).getActivity();
        mSearchingView = searchingView;
        mSearchingPresenter = searchingPresenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paylocation, parent, false);

        return new PayLocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        PayLocation currentPayLocation = mPayLocationsList.get(position);

        ((PayLocationHolder) holder).getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchingPresenter.informToShowInMaps(position);
            }
        });

        ((PayLocationHolder) holder).getLocationName().setText(currentPayLocation.getPayLocationName());
        ((PayLocationHolder) holder).getLocationBranch().setText(currentPayLocation.getPaylocationBranch());
        ((PayLocationHolder) holder).getLocationDistanceFromUser().setText(String.valueOf(currentPayLocation.getLocationDistance()));

        // set distance format
        if (currentPayLocation.getLocationDistance() > -1) {
            String stringDistance = String.format("%.1f", currentPayLocation.getLocationDistance() / 1000);
            ((PayLocationHolder) holder).getLocationDistanceFromUser().setText(stringDistance + " KM");
        } else {
            ((PayLocationHolder) holder).getLocationDistanceFromUser().setText(" ");
        }

        // set location type
        if(currentPayLocation.getPayLocationType().equals(Constants.PayLocationsType.CAFES_DRINKS)) {
            Glide.with(mContext)
                    .load(R.drawable.icon_cafe)
                    .thumbnail(0.2f)
                    .into(((PayLocationHolder) holder).getImageLocationType());
        } else if(currentPayLocation.getPayLocationType().equals(Constants.PayLocationsType.DINING)) {
            Glide.with(mContext)
                    .load(R.drawable.icon_dining)
                    .thumbnail(0.2f)
                    .into(((PayLocationHolder) holder).getImageLocationType());
        } else if(currentPayLocation.getPayLocationType().equals(Constants.PayLocationsType.SHOPPING)) {
            Glide.with(mContext)
                    .load(R.drawable.icon_shopping)
                    .thumbnail(0.2f)
                    .into(((PayLocationHolder) holder).getImageLocationType());
        } else if(currentPayLocation.getPayLocationType().equals(Constants.PayLocationsType.SUPERMARKETS)) {
            Glide.with(mContext)
                    .load(R.drawable.icon_supermarket)
                    .thumbnail(0.2f)
                    .into(((PayLocationHolder) holder).getImageLocationType());
        } else if(currentPayLocation.getPayLocationType().equals(Constants.PayLocationsType.BEAUTIES)) {
            Glide.with(mContext)
                    .load(R.drawable.icon_beauty)
                    .thumbnail(0.2f)
                    .into(((PayLocationHolder) holder).getImageLocationType());
        } else if(currentPayLocation.getPayLocationType().equals(Constants.PayLocationsType.TRAVEL_ENTERTAIN)) {
            Glide.with(mContext)
                    .load(R.drawable.icon_entertain)
                    .thumbnail(0.2f)
                    .into(((PayLocationHolder) holder).getImageLocationType());
        }


        // set pay types
        if(currentPayLocation.isUseApplePay()) {
            ((PayLocationHolder) holder).getLocationUseApplePay().setVisibility(View.VISIBLE);
        } else {
            ((PayLocationHolder) holder).getLocationUseApplePay().setVisibility(View.INVISIBLE);
        }

        if(currentPayLocation.isUseGooglePay()) {
            ((PayLocationHolder) holder).getLocationUseGooglePay().setVisibility(View.VISIBLE);
        } else {
            ((PayLocationHolder) holder).getLocationUseGooglePay().setVisibility(View.INVISIBLE);
        }

        if(currentPayLocation.isUseSamsungPay()) {
            ((PayLocationHolder) holder).getLocationUseSamsungPay().setVisibility(View.VISIBLE);
        } else {
            ((PayLocationHolder) holder).getLocationUseSamsungPay().setVisibility(View.INVISIBLE);
        }

        if(currentPayLocation.isUseLinePay()) {
            ((PayLocationHolder) holder).getLocationUseLinePay().setVisibility(View.VISIBLE);
        } else {
            ((PayLocationHolder) holder).getLocationUseLinePay().setVisibility(View.INVISIBLE);
        }

        if(currentPayLocation.isUseJkoPay()) {
            ((PayLocationHolder) holder).getLocationUseJkoPay().setVisibility(View.VISIBLE);
        } else {
            ((PayLocationHolder) holder).getLocationUseJkoPay().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mPayLocationsList == null ? 0 : mPayLocationsList.size();
    }

    public void swapList(ArrayList<PayLocation> payLocationsList) {
        if (mPayLocationsList != payLocationsList) {
            mPayLocationsList = payLocationsList;
            notifyDataSetChanged();
        }
    }

    public void refreshList() {
        notifyDataSetChanged();
    }

    public void clearList() {
        if(mPayLocationsList != null) mPayLocationsList.clear();
        notifyDataSetChanged();
    }

    public class PayLocationHolder extends RecyclerView.ViewHolder {
        ConstraintLayout container;

        TextView locationName;
        TextView locationBranch;
        TextView locationDistanceFromUser;

        ImageView imageLocationType;
        ImageView locationUseApplePay;
        ImageView locationUseGooglePay;
        ImageView locationUseSamsungPay;
        ImageView locationUseLinePay;
        ImageView locationUseJkoPay;

        PayLocationHolder(View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.item_container);

            locationName = itemView.findViewById(R.id.item_name);
            locationBranch = itemView.findViewById(R.id.item_branch);
            locationDistanceFromUser = itemView.findViewById(R.id.item_distance);

            imageLocationType = itemView.findViewById(R.id.image_location_type);
            locationUseApplePay = itemView.findViewById(R.id.image_applepay);
            locationUseGooglePay = itemView.findViewById(R.id.image_googlepay);
            locationUseSamsungPay = itemView.findViewById(R.id.image_samsungpay);
            locationUseLinePay = itemView.findViewById(R.id.image_linepay);
            locationUseJkoPay = itemView.findViewById(R.id.image_jkopay);
        }

        public ConstraintLayout getContainer() {
            return container;
        }

        public TextView getLocationName() {
            return locationName;
        }

        public TextView getLocationBranch() {
            return locationBranch;
        }

        public TextView getLocationDistanceFromUser() {
            return locationDistanceFromUser;
        }

        public ImageView getImageLocationType() {
            return imageLocationType;
        }

        public ImageView getLocationUseApplePay() {
            return locationUseApplePay;
        }

        public ImageView getLocationUseGooglePay() {
            return locationUseGooglePay;
        }

        public ImageView getLocationUseSamsungPay() {
            return locationUseSamsungPay;
        }

        public ImageView getLocationUseLinePay() {
            return locationUseLinePay;
        }

        public ImageView getLocationUseJkoPay() {
            return locationUseJkoPay;
        }
    }


}
