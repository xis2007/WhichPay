package com.whichpay.whichpay.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ImeUtil {
    Context mContext;

    public ImeUtil(Context context) {
        mContext = context;
    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = ((Activity) mContext).getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(((Activity) mContext));
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
