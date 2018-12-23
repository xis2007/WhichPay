package com.whichpay.whichpay.fragments.maps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.contants.Constants;
import com.whichpay.whichpay.objects.PayLocation;
import com.whichpay.whichpay.util.ImageUtil;

import java.util.ArrayList;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

public class MapsFragment extends Fragment implements MapsContract.View {
    private MapsContract.Presenter mMapsPresenter;

    private MapView mMapView;
    private GoogleMap mGoogleMap;

    private ArrayList<PayLocation> mCustomAreaPayLoationsList;

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

                setUpListeners();

                moveToSelectedLocationFromSearchedList();
            }
        });
    }

    @Override
    public void moveToSelectedLocationFromSearchedList() {
        mGoogleMap.clear();

        ArrayList<PayLocation> listToShow = WhichPay.getSearchedAndFilteredPayLocationsList();
        int positionOfPayLocationToShow = WhichPay.getPositionOfPayLocationToShow();
        PayLocation payLocationToShow = listToShow.get(positionOfPayLocationToShow);

        for (int i = 0; i < listToShow.size(); i++) {
            if (i != positionOfPayLocationToShow) {
                PayLocation paylocation = listToShow.get(i);
                LatLng latLng = new LatLng(paylocation.getLocationLatitude(), paylocation.getLocationLongitude());

                Marker marker = mGoogleMap.addMarker(new MarkerOptions().position(latLng).icon(attachMarkerImage(paylocation)));
                marker.setTag(paylocation);
            }
        }

        LatLng latLngForMarker = new LatLng(payLocationToShow.getLocationLatitude(), payLocationToShow.getLocationLongitude());
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngForMarker, 15));
        Marker selectedLocationMarker = mGoogleMap.addMarker(new MarkerOptions().position(latLngForMarker).icon(attachMarkerImage(payLocationToShow)));
        selectedLocationMarker.setTag(payLocationToShow);

        selectedLocationMarker.showInfoWindow();

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


    /**
     * ***********************************************************************************
     * Helper/Other Methods
     * ***********************************************************************************
     */
    private BitmapDescriptor attachMarkerImage(PayLocation payLocation){
        BitmapDescriptor descriptorToShow;

        switch(payLocation.getPayLocationType()) {
            case Constants.PayLocationsType.CAFES_DRINKS:
                descriptorToShow = BitmapDescriptorFactory.fromBitmap(new ImageUtil(getActivity()).resizeMarker(R.drawable.marker_cafe, 85));
                break;

            case Constants.PayLocationsType.DINING:
                descriptorToShow = BitmapDescriptorFactory.fromBitmap(new ImageUtil(getActivity()).resizeMarker(R.drawable.marker_dining, 85));
                break;

            case Constants.PayLocationsType.SHOPPING:
                descriptorToShow = BitmapDescriptorFactory.fromBitmap(new ImageUtil(getActivity()).resizeMarker(R.drawable.marker_shopping, 85));
                break;

            case Constants.PayLocationsType.SUPERMARKETS:
                descriptorToShow = BitmapDescriptorFactory.fromBitmap(new ImageUtil(getActivity()).resizeMarker(R.drawable.marker_supermarket, 85));
                break;

            case Constants.PayLocationsType.BEAUTIES:
                descriptorToShow = BitmapDescriptorFactory.fromBitmap(new ImageUtil(getActivity()).resizeMarker(R.drawable.marker_beauty, 85));
                break;

            case Constants.PayLocationsType.TRAVEL_ENTERTAIN:
                descriptorToShow = BitmapDescriptorFactory.fromBitmap(new ImageUtil(getActivity()).resizeMarker(R.drawable.marker_entertain, 85));
                break;

            default:
                // TODO fix it
                descriptorToShow = BitmapDescriptorFactory.defaultMarker();
                break;
        }

        return descriptorToShow;
    }



    private void setUpListeners() {
        mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                Object tag = marker.getTag();

                if (tag instanceof PayLocation) {
                    PayLocation payLocation = (PayLocation) tag;
                    View infoWindow = LayoutInflater.from(getActivity()).inflate(R.layout.infowindow_maps, null);

                    ImageView imagePayLocationType = infoWindow.findViewById(R.id.image_location_type_maps);
                    Button buttonDistance = infoWindow.findViewById(R.id.button_distance);
                    TextView textName = infoWindow.findViewById(R.id.info_place_name);
                    TextView textBranch = infoWindow.findViewById(R.id.info_place_branch);
                    TextView textAddress = infoWindow.findViewById(R.id.info_place_address);
                    ImageView imageApplePay = infoWindow.findViewById(R.id.iw_applepay_image);
                    ImageView imageGooglePay = infoWindow.findViewById(R.id.iw_googlepay_image);
                    ImageView imageSamsungPay = infoWindow.findViewById(R.id.iw_samsungpay_image);
                    ImageView imageLinePay = infoWindow.findViewById(R.id.iw_linepay_image);
                    ImageView imageJkoPay = infoWindow.findViewById(R.id.iw_jkopay_image);

                    // PayLocation Type Image
                    Glide.with(getActivity()).load(new ImageUtil(getActivity()).matchPaylocationTypeDrawable(payLocation.getPayLocationType())).into(imagePayLocationType);
                    imagePayLocationType.setBackgroundResource(new ImageUtil(getActivity()).matchPaylocationTypeDrawable(payLocation.getPayLocationType()));

                    // Distance Info
                    String stringDistance = String.format("%.1f", payLocation.getLocationDistance() / 1000);
                    buttonDistance.setText(stringDistance + " km away");

                    // Text Info
                    textName.setText(payLocation.getPayLocationName());
                    textBranch.setText(payLocation.getPaylocationBranch());
                    textAddress.setText(payLocation.getPayLocationAddress());

                    // Payment Method Info
                    if(payLocation.isUseApplePay()) {
                        imageApplePay.setVisibility(View.VISIBLE);
                    } else {
                        imageApplePay.setVisibility(View.INVISIBLE);
                    }

                    if(payLocation.isUseGooglePay()) {
                        imageGooglePay.setVisibility(View.VISIBLE);
                    } else {
                        imageGooglePay.setVisibility(View.INVISIBLE);
                    }

                    if(payLocation.isUseSamsungPay()) {
                        imageSamsungPay.setVisibility(View.VISIBLE);
                    } else {
                        imageSamsungPay.setVisibility(View.INVISIBLE);
                    }

                    if(payLocation.isUseLinePay()) {
                        imageLinePay.setVisibility(View.VISIBLE);
                    } else {
                        imageLinePay.setVisibility(View.INVISIBLE);
                    }

                    if(payLocation.isUseJkoPay()) {
                        imageJkoPay.setVisibility(View.VISIBLE);
                    } else {
                        imageJkoPay.setVisibility(View.INVISIBLE);
                    }

                    return infoWindow;

                } else {
                    return null;
                }
            }
        });
    }

    public boolean isMapReady() {
        return mGoogleMap != null;
    }

    /**
     * ***********************************************************************************
     * Getters and Setters
     * ***********************************************************************************
     */

}
