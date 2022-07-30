package com.manappuram.reapp.Modules.RETracker.View;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.manappuram.reapp.Base.BaseActivity;
import com.manappuram.reapp.Login.ViewModel.LoginViewModel;
import com.manappuram.reapp.Modules.RETracker.Model.Response.ActivityResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.EndActivityResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.StartActivityResponse;
import com.manappuram.reapp.Modules.RETracker.ViewModel.REtrackerViewModel;
import com.manappuram.reapp.Modules.SplashScreen.Splash_Activity;
import com.manappuram.reapp.R;
import com.manappuram.reapp.Utils.Utility;
import com.manappuram.reapp.databinding.ActivityRetrackerBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class REtrackerActivity extends BaseActivity {

    ActivityRetrackerBinding binding;
    REtrackerViewModel viewModel;
    LoginViewModel loginViewModel;

    ArrayList<String> activityList = new ArrayList<>();
    ArrayAdapter activityArrayAdapter;

    String selectedActivityId = "", activityStartDesc, city, activityEndDesc, travelId;
    ActivityResponse activityResponse1;

    double currentLatitude, currentLongitude;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 7;
    boolean flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retracker);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_retracker);
        viewModel = ViewModelProviders.of(this).get(REtrackerViewModel.class);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mActivity = REtrackerActivity.this;

        binding.tvEndActivity.setEnabled(false);
        buttonClicks();
        setActivityAdapter();
        getActivityRequest();
        responses();
    }

    private void buttonClicks() {

        binding.tvStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag = true;

                if (binding.etStartRemarks.getText().toString().equals("")) {
                    showErrorMsg("Please enter the Activity remarks");
                } else {

                    if (checkAndRequestPermissions()) {
                        getCurrentLocation();
                    }
                }

            }
        });


        binding.tvEndActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.etStartRemarks.setVisibility(View.GONE);
                binding.etEndRemarks.setVisibility(View.VISIBLE);
                binding.tvConfirm.setVisibility(View.VISIBLE);

            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                if (binding.etEndRemarks.getText().toString().equals("")) {
                    showErrorMsg("Please enter the Activity remarks");
                } else {
                    getCurrentLocation();
                }

            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void logout() {
        editor.clear();
        editor.apply();
        Intent intent = new Intent(mActivity, Splash_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private boolean checkAndRequestPermissions() {

        int locationfine = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int locationcoarse = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (locationcoarse != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (locationfine != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    private void setActivityAdapter() {

        activityArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, activityList);
        activityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spReActivity.setAdapter(activityArrayAdapter);

    }

    private void getActivityRequest() {

        String data = empCode + "~" + session_id + "$";
        String input_data = Utility.encodeString(data);
        String final_data = input_data.trim().replaceAll("\\s+", "");


        Utility.setProgressbar(mActivity);
        viewModel.getActivity(final_data);

        binding.spReActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                selectedActivityId = activityResponse1.getActivityData().get(position).getActivityid();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void responses() {

        viewModel.getActivityResponseMutableLiveData().observe((LifecycleOwner) mActivity, new Observer<ActivityResponse>() {

            @Override
            public void onChanged(ActivityResponse activityResponse) {
                Utility.cancelProgressbar();
                activityList.clear();
                activityResponse1 = activityResponse;
                if (activityResponse != null) {
                    if (activityResponse.getStatus().equals("111")) {

                        if (activityResponse.getActivityData().size() > 0) {
                            for (int i = 0; i < activityResponse.getActivityData().size(); i++) {
                                String ss = activityResponse.getActivityData().get(i).getActivityid();

                                activityList.add(activityResponse.getActivityData().get(i).getActivityName());

                            }
                            activityArrayAdapter.notifyDataSetChanged();
                        }

                    } else {
                        showErrorMsg(activityResponse.getResult());

                    }
                }
            }
        });

        viewModel.getStartActivityResponseMutableLiveData().observe((LifecycleOwner) mActivity, new Observer<StartActivityResponse>() {

            @Override
            public void onChanged(StartActivityResponse startActivityResponse) {

                Utility.cancelProgressbar();

                if (startActivityResponse != null) {
                    if (startActivityResponse.getStatus().equals("111")) {

                        travelId = startActivityResponse.getTrav_id();
                        binding.tvEndActivity.setEnabled(true);
                        binding.tvStartActivity.setEnabled(false);
                        binding.etEndRemarks.setVisibility(View.VISIBLE);
                        binding.etStartRemarks.setVisibility(View.GONE);

                        binding.tvStartActivity.setBackgroundResource(R.drawable.button_yellow_shadow);
                        binding.tvStartActivity.setTextColor(getResources().getColor(R.color.gray_808080));

                        binding.tvEndActivity.setTextColor(getResources().getColor(R.color.black));
                        binding.tvEndActivity.setBackgroundResource(R.drawable.button_yellow_bg);
                        showErrorMsg(startActivityResponse.getResult());

                    } else {

                        showErrorMsg(startActivityResponse.getResult());

                    }
                }

            }
        });

        viewModel.getEndActivityResponseMutableLiveData().observe((LifecycleOwner) mActivity, new Observer<EndActivityResponse>() {
            @Override
            public void onChanged(EndActivityResponse endActivityResponse) {
                Utility.cancelProgressbar();
                Log.i("success::::::::","success::::::::");

                if (endActivityResponse.getStatus().equals("111")) {

                    Utility.showAlertDialog(mActivity, endActivityResponse.getResult(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           finish();
                        }
                    });
                } else {

                    showErrorMsg(endActivityResponse.getResult());
                }
            }
        });

       /* loginViewModel.getLogoutResponseMutableLiveData().observe((LifecycleOwner) mActivity, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                Utility.cancelProgressbar();

                if (baseResponse.getStatus().equals("111")) {

                    Utility.showAlertDialog(mActivity, baseResponse.getResult(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                } else {

                    showErrorMsg(baseResponse.getResult());
                }
            }
        });*/
    }


    private void getCurrentLocation() {
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(mActivity).
                requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(mActivity).
                                removeLocationUpdates(this);

                        if (locationResult != null && locationResult.getLocations().size() > 0) {


                            int lastLocationIndex = locationResult.getLocations().size() - 1;
                            currentLatitude = locationResult.getLocations().get(lastLocationIndex).getLatitude();
                            currentLongitude = locationResult.getLocations().get(lastLocationIndex).getLongitude();


                            try {
                                Geocoder geocoder;
                                List<Address> addresses;
                                geocoder = new Geocoder(mActivity, Locale.getDefault());
                                addresses = geocoder.getFromLocation(currentLatitude, currentLongitude, 1);
                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                city = addresses.get(0).getLocality();
                                String state = addresses.get(0).getAdminArea();
                                String country = addresses.get(0).getCountryName();
                                String postalCode = addresses.get(0).getPostalCode();
                                String knownName = addresses.get(0).getFeatureName();

                                if (flag) {
                                    setStartActivityRequest(currentLatitude, currentLongitude);
                                } else {
                                    setEndActivityRequest(currentLatitude, currentLongitude);
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }

                    }
                }, Looper.getMainLooper());
    }

    private void setStartActivityRequest(double currentLatitude, double currentLongitude) {

        activityStartDesc = binding.etStartRemarks.getText().toString();
        String data = empCode + "~" + session_id + "$" + empCode + "~" + brId + "~" + activityStartDesc + "~" + currentLatitude + "~" + currentLongitude + "~" + city + "~" + selectedActivityId;
        String input_data = Utility.encodeString(data);
        String final_data = input_data.trim().replaceAll("\\s+", "");

        Utility.setProgressbar(mActivity);
        viewModel.startActivity(final_data);

    }

    private void setEndActivityRequest(double currentLatitude, double currentLongitude) {

        activityEndDesc = binding.etEndRemarks.getText().toString();
        String data = empCode + "~" + session_id + "$" + travelId + "~" + city + "~" + currentLatitude + "~" + currentLongitude + "~" + activityEndDesc;
        String input_data = Utility.encodeString(data);
        String final_data = input_data.trim().replaceAll("\\s+", "");

        Utility.setProgressbar(mActivity);
        viewModel.endActivity(final_data);

    }


    private void showErrorMsg(String msg) {
        Utility.showAlertDialog(mActivity, msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Utility.dismissAlertDialog();
            }
        });
    }
}