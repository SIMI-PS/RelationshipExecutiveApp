package com.manappuram.reapp.Modules.DashBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.manappuram.reapp.Base.BaseActivity;
import com.manappuram.reapp.Modules.RETracker.View.REtrackerActivity;
import com.manappuram.reapp.R;
import com.manappuram.reapp.databinding.ActivityDashboardBinding;

public class DashboardActivity extends BaseActivity {

    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        mActivity = this;

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        binding.liREActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(mActivity, REtrackerActivity.class);
                startActivity(in);

            }
        });
    }
}