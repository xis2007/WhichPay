package com.whichpay.whichpay.model.firestore;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.whichpay.whichpay.activities.main.MainPresenter;
import com.whichpay.whichpay.application.WhichPay;
import com.whichpay.whichpay.contants.Constants;


public class RemoteSettingsManager {
    private Context mContext;
    private MainPresenter mMainPresenter;

    public RemoteSettingsManager(Context context, MainPresenter mainPresenter) {
        mContext = context;
        mMainPresenter = mainPresenter;
    }

    public void checkForAppUpdateRequirement() {
        WhichPay.getmFirestoreDb()
                .collection(Constants.Firestore.COLLECTION_SETTINGS)
                .document(Constants.Firestore.DOCUMENT_UPDATE_APP)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            boolean requireAppUpdate = (Boolean) documentSnapshot.get(Constants.Firestore.KEY_REQUIRED);
                            int requiredAppVersionCode = (int) ((long) documentSnapshot.get(Constants.Firestore.KEY_REQUIRED_verSION_CODE));

                            if (isUpdateRequired(requireAppUpdate, requiredAppVersionCode)) {
                                mMainPresenter.promptUpdateRequirementMessage();
                            }
                        }
                    }
                });
    }

    int getCurrentAppVersionCode() {
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            int verCode = pInfo.versionCode;
            return verCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }

    boolean isUpdateRequired(boolean requireAppUpdate, int requiredAppVersionCode) {
        if ((requireAppUpdate) && (getCurrentAppVersionCode() < requiredAppVersionCode)) {
            return true;
        } else {
            return false;
        }
    }
}
