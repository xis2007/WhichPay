package com.whichpay.whichpay.objects;

import java.io.Serializable;

/**
 * Created by Justin on 11/26/2017.
 */

class PayLocation implements Serializable {
    public static final long serialVersionUID = 20171126L;

    private long m_Id;
    private String mLocationId;
    private String mPayLocationName;

    private String mPaylocationBranch;
    private String mPayLocationType;
    private String mPayLocationAddress;
    private String mPayLocationDescription;
    private double mLocationLatitude;
    private double mLocationLongitude;
    private float mLocationDistance;

    private String mPayLocationUseApplePay;
    private String mPayLocationUseAndroidPay;
    private String mPaylocationUseSamsungPay;
    private String mPayLocationUseLinePay;
    private String mPayLocationUseJkoPay;

    public PayLocation (long Id,
                       String locationId,
                       String locationName,
                       String locationBranch,
                       String locationType,
                       String locationAddress,
                       String locationDescription,
                       double locationLatitude,
                       double locationLongitude,
                       String locationUseApplePay,
                       String locationUseAndroidPay,
                       String locationUseSamsungPay,
                       String locationUseLinePay,
                       String locationUseJkoPay) {
        this.m_Id = Id;
        mLocationId = locationId;
        mPayLocationName = locationName;
        mPaylocationBranch = locationBranch;
        mPayLocationType = locationType;
        mPayLocationAddress = locationAddress;
        mLocationDistance = -1;

        mPayLocationDescription = locationDescription;
        mLocationLatitude = locationLatitude;
        mLocationLongitude = locationLongitude;

        mPayLocationUseApplePay = locationUseApplePay;
        mPayLocationUseAndroidPay = locationUseAndroidPay;
        mPaylocationUseSamsungPay = locationUseSamsungPay;
        mPayLocationUseLinePay = locationUseLinePay;
        mPayLocationUseJkoPay = locationUseJkoPay;
    }

    String getLocationId() {
        return mLocationId;
    }

    String getPayLocationName() {
        return mPayLocationName;
    }

    String getPaylocationBranch() { return mPaylocationBranch; }

    String getPayLocationType() {
        return mPayLocationType;
    }

    String getPayLocationAddress() {
        return mPayLocationAddress;
    }

    float getDistanceFromDevice() {
        return mLocationDistance;
    }

    String getPayLocationDescription() {
        return mPayLocationDescription;
    }

    double getLocationLatitude() {
        return mLocationLatitude;
    }

    double getLocationLongitude() {
        return mLocationLongitude;
    }

    String isPayLocationUseApplePay() {
        return mPayLocationUseApplePay;
    }

    String isPayLocationUseAndroidPay() {
        return mPayLocationUseAndroidPay;
    }

    String isPayLocationUseLinePay() {
        return mPayLocationUseLinePay;
    }

    String isPayLocationUseJkoPay() {
        return mPayLocationUseJkoPay;
    }

    String isPayLocationUseSamsungPay() {
        return mPaylocationUseSamsungPay;
    }

    void setLocationDistance(float locationDistance) {
        mLocationDistance = locationDistance;
    }

    @Override
    public String toString() {
        return super.toString();
    }

//    @Override
//    public int compareTo(@NonNull PayLocation o) {
//        return PL_Manager.Comparators.DISTANCE.compare(this, o);
//    }
}
