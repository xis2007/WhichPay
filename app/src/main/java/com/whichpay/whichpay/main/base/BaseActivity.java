package com.whichpay.whichpay.main.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.whichpay.whichpay.R;
import com.whichpay.whichpay.contants.Constants;

/**
 * Created by Justin on 11/25/2017.
 *
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "checkkkkk";

    AHBottomNavigation mBottomNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setStatusBar();
//        setToolBarHeight();
        initViews();
    }

//    private void setStatusBar() {
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //4.4
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //5.0
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);//calculateStatusColor(Color.WHITE, (int) alphaValue)
//        }
//    }
//
//    private void setToolBarHeight() {
//        Toolbar toolbar = findViewById(R.id.toolbar_main);
//        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
//    }
//
//    /**
//     * @return height of status bar
//     */
//    public int getStatusBarHeight() {
//        int result = 0;
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = getResources().getDimensionPixelSize(resourceId);
//        }
//
//        return result;
//    }

    private void initViews() {
        initBottomNavigation();
    }

    private void initBottomNavigation() {
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.color_bottom_nav);
        AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_main);
        navigationAdapter.setupWithBottomNavigation(mBottomNavigation, tabColors);

        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
        mBottomNavigation.setAccentColor(getResources().getColor(R.color.colorPrimary));
        mBottomNavigation.setInactiveColor(getResources().getColor(R.color.grey));

        mBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                Log.d(TAG, "onTabSelected: position is: " + position);
                switch (position) {
                    case Constants.BottomNavPosition.POSITION_HOME:

                        break;

                    case Constants.BottomNavPosition.POSITION_SEARCH:

                        break;

                    case Constants.BottomNavPosition.POSITION_SETTINGS:
                        
                        break;

                }
                return true;
            }
        });
    }




    //    void activateToolbar(boolean enableHome) {
//        Toolbar toolbar = findViewById(R.id.toolbar);
//
//
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar == null) {
//            if (toolbar != null) {
//                setSupportActionBar(toolbar);
//                actionBar = getSupportActionBar();
//            }
//        }
//
//        if(actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(enableHome);
//        }
//    }


}
