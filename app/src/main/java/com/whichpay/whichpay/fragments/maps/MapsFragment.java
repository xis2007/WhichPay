package com.whichpay.whichpay.fragments.maps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.whichpay.whichpay.R;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

public class MapsFragment extends Fragment implements MapsContract.View {
    private MapsContract.Presenter mMapsPresenter;

    private MapView mMapView;
    private GoogleMap mGoogleMap;

    public MapsFragment() {
    }

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        initiateMapView(rootView, savedInstanceState);

        return rootView;
    }

    private void initiateMapView(View rootView, Bundle savedInstanceState) {
        mMapView = rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
            }
        });




//        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
//        try {
//            MapsInitializer.initialize(this.getActivity());
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupport().findFragmentById(R.id.mapView);
//        mapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap googleMap) {
//                mGoogleMap = googleMap;
//            }
//        });
    }


    @Override
    public void showUpdateRequirementDialog() {

    }

    @Override
    public void setPresenter(MapsContract.Presenter presenter) {
        mMapsPresenter = checkNotNull(presenter);
    }


    /**
     * ***********************************************************************************
     * Fragment Lifecycle
     * ***********************************************************************************
     */
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
