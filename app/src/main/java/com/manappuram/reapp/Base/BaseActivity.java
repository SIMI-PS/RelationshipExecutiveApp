package com.manappuram.reapp.Base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.manappuram.reapp.R;

public class BaseActivity extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Activity mActivity;

    public String empCode = "";
    public String empName = "";
    public String postId = "";
    public String brId = "";
    public String departName = "";
    public String areaId = "";
    public String regId = "";
    public String zoneId = "";
    public String departId = "";
    public boolean login = false;
    public String  leaveTypeId = "";
    public String designation = "";
    public String joinDate = "",brName = "",session_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        sharedPreferences = getSharedPreferences("RE-app", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

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

    }


}