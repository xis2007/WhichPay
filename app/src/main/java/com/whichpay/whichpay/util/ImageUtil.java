package com.whichpay.whichpay.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.whichpay.whichpay.R;
import com.whichpay.whichpay.contants.Constants;

public class ImageUtil {
    Context mContext;

    public ImageUtil(Context context) {
        mContext = context;
    }

    public Bitmap resizeMarker(int resourceId, int scale){
        Bitmap imageBitmap = BitmapFactory.decodeResource(mContext.getResources(), resourceId);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, scale, scale, false);
        return resizedBitmap;
    }

    public int matchPaylocationTypeDrawable(String paylocationType) {
        switch (paylocationType) {
            case Constants.PayLocationsType.CAFES_DRINKS:
                return R.drawable.icon_cafe;
            case Constants.PayLocationsType.DINING:
                return R.drawable.icon_dining;
            case Constants.PayLocationsType.SHOPPING:
                return R.drawable.icon_shopping;
            case Constants.PayLocationsType.SUPERMARKETS:
                return R.drawable.icon_supermarket;
            case Constants.PayLocationsType.BEAUTIES:
                return R.drawable.icon_beauty;
            case Constants.PayLocationsType.TRAVEL_ENTERTAIN:
                return R.drawable.icon_entertain;
            default:
                return R.drawable.icon_cafe;
        }
    }
}
