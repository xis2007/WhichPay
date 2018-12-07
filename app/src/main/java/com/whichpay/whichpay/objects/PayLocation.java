package com.whichpay.whichpay.objects;

import java.io.Serializable;

/**
 * Created by Justin on 11/26/2017.
 */

public class PayLocation implements Serializable {
    public static final long serialVersionUID = 20171126L;

    private long _Id;
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
    private String mPayLocationUseGooglePay;
    private String mPaylocationUseSamsungPay;
    private String mPayLocationUseLinePay;
    private String mPayLocationUseJkoPay;

    public PayLocation() {
        mLocationDistance = -1;
    }

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
        this._Id = Id;
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
        mPayLocationUseGooglePay = locationUseAndroidPay;
        mPaylocationUseSamsungPay = locationUseSamsungPay;
        mPayLocationUseLinePay = locationUseLinePay;
        mPayLocationUseJkoPay = locationUseJkoPay;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long get_Id() {
        return _Id;
    }

    public void set_Id(long _Id) {
        this._Id = _Id;
    }

    public String getLocationId() {
        return mLocationId;
    }

    public void setLocationId(String locationId) {
        mLocationId = locationId;
    }

    public String getPayLocationName() {
        return mPayLocationName;
    }

    public void setPayLocationName(String payLocationName) {
        mPayLocationName = payLocationName;
    }

    public String getPaylocationBranch() {
        return mPaylocationBranch;
    }

    public void setPaylocationBranch(String paylocationBranch) {
        mPaylocationBranch = paylocationBranch;
    }

    public String getPayLocationType() {
        return mPayLocationType;
    }

    public void setPayLocationType(String payLocationType) {
        mPayLocationType = payLocationType;
    }

    public String getPayLocationAddress() {
        return mPayLocationAddress;
    }

    public void setPayLocationAddress(String payLocationAddress) {
        mPayLocationAddress = payLocationAddress;
    }

    public String getPayLocationDescription() {
        return mPayLocationDescription;
    }

    public void setPayLocationDescription(String payLocationDescription) {
        mPayLocationDescription = payLocationDescription;
    }

    public double getLocationLatitude() {
        return mLocationLatitude;
    }

    public void setLocationLatitude(double locationLatitude) {
        mLocationLatitude = locationLatitude;
    }

    public double getLocationLongitude() {
        return mLocationLongitude;
    }

    public void setLocationLongitude(double locationLongitude) {
        mLocationLongitude = locationLongitude;
    }

    public float getLocationDistance() {
        return mLocationDistance;
    }

    public void setLocationDistance(float locationDistance) {
        mLocationDistance = locationDistance;
    }

    public String getPayLocationUseApplePay() {
        return mPayLocationUseApplePay;
    }

    public void setPayLocationUseApplePay(String payLocationUseApplePay) {
        mPayLocationUseApplePay = payLocationUseApplePay;
    }

    public String getPayLocationUseGooglePay() {
        return mPayLocationUseGooglePay;
    }

    public void setPayLocationUseGooglePay(String payLocationUseGooglePay) {
        mPayLocationUseGooglePay = payLocationUseGooglePay;
    }

    public String getPaylocationUseSamsungPay() {
        return mPaylocationUseSamsungPay;
    }

    public void setPaylocationUseSamsungPay(String paylocationUseSamsungPay) {
        mPaylocationUseSamsungPay = paylocationUseSamsungPay;
    }

    public String getPayLocationUseLinePay() {
        return mPayLocationUseLinePay;
    }

    public void setPayLocationUseLinePay(String payLocationUseLinePay) {
        mPayLocationUseLinePay = payLocationUseLinePay;
    }

    public String getPayLocationUseJkoPay() {
        return mPayLocationUseJkoPay;
    }

    public void setPayLocationUseJkoPay(String payLocationUseJkoPay) {
        mPayLocationUseJkoPay = payLocationUseJkoPay;
    }

    @Override
    public String toString() {
        return super.toString();
    }

//    @Override
//    public int compareTo(@NonNull PayLocation o) {
//        return PL_Manager.Comparators.DISTANCE.compare(this, o);
//    }

    public boolean isUseApplePay() {
        return getPayLocationUseApplePay().equals("Y");
    }

    public boolean isUseGooglePay() {
        return getPayLocationUseGooglePay().equals("Y");
    }

    public boolean isUseSamsungPay() {
        return getPaylocationUseSamsungPay().equals("Y");
    }

    public boolean isUseLinePay() {
        return getPayLocationUseLinePay().equals("Y");
    }

    public boolean isUseJkoPay() {
        return getPayLocationUseJkoPay().equals("Y");
    }
}
