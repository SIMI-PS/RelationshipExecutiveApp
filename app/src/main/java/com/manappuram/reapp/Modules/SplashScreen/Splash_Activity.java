package com.manappuram.reapp.Modules.SplashScreen;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.manappuram.reapp.Base.BaseActivity;
import com.manappuram.reapp.Login.View.LoginActivity;
import com.manappuram.reapp.R;
import com.manappuram.reapp.Utils.Navigator.navigator;
import com.manappuram.reapp.Utils.RootUtil;
import com.manappuram.reapp.databinding.ActivitySplashBinding;

public class Splash_Activity extends BaseActivity {
    private navigator mNavigator;
    ActivitySplashBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mActivity = this;
        mNavigator = new navigator(this);
        binding.shimmerViewContainer1.startShimmerAnimation();

        Log.i("sssssssssss","sssssssssss");


        if (RootUtil.isDeviceRooted()) {
            showPopupForRootedDevice();
        } else {
            startLogin();
        }


    }

    public void showPopupForRootedDevice() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.unsupported_device))
                .setMessage(getString(R.string.app_not_supported))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok), (dialog, id) -> {
                    finish();
                })
                .show();
    }

    private void startLogin() {

        empCode = sharedPreferences.getString("empCode", "");
        empName = sharedPreferences.getString("empName", "");
        departId = sharedPreferences.getString("departId", "");
        departName = sharedPreferences.getString("departName", "");
        postId = sharedPreferences.getString("postId", "");
        designation = sharedPreferences.getString("designation", "");
        joinDate = sharedPreferences.getString("joinDate", "");
        brName = sharedPreferences.getString("brName", "");
        brId = sharedPreferences.getString("brId", "");
        session_id = sharedPreferences.getString("session_id", "");
        areaId = sharedPreferences.getString("areaId", "");
        regId = sharedPreferences.getString("regionId", "");
        zoneId = sharedPreferences.getString("zoneId", "");


       // mHandler = new Handler();
        //mHandler.postDelayed(mRunnable, SPLASH_DISPLAY_LENGTH);



        new Handler().postDelayed(() -> {
            mNavigator.startActivityWithAnimation(LoginActivity.class);
            mNavigator.finishActivity();
            binding.shimmerViewContainer1.stopShimmerAnimation();

        },3000);

    }
}