package com.manappuram.reapp.Login.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.manappuram.reapp.Base.BaseActivity;
import com.manappuram.reapp.BuildConfig;
import com.manappuram.reapp.Login.Model.Request.LoginRequest;
import com.manappuram.reapp.Login.ViewModel.LoginViewModel;
import com.manappuram.reapp.Modules.DashBoard.DashboardActivity;
import com.manappuram.reapp.R;
import com.manappuram.reapp.Utils.Utility;
import com.manappuram.reapp.databinding.ActivityLoginBinding;


public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    String password = "";
    String employecode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        //binding.setViewModel(loginViewModel);
        mActivity = LoginActivity.this;
        binding.tvVersion.setText("Version " + BuildConfig.VERSION_NAME);

        loginRequest();
        responses();


    }

    private void loginRequest() {

        binding.login.setOnClickListener(v -> {

            //if (isValid(binding.getViewModel().getLoginRequest())) {

            if (binding.usernameInput.getText().toString().isEmpty() || (binding.usernameInput.getText().toString().length() < 6)) {
                //dismissKeyboard();

                Toast.makeText(mActivity, "Please Enter Employee code", Toast.LENGTH_SHORT).show();
            } else {
                if (binding.passwordInput.getText().toString().isEmpty()) {
                    Toast.makeText(mActivity, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else {

                    if (Utility.isNetworkAvailable(mActivity)) {

                        Utility.setProgressbar(mActivity);
                        employecode = binding.usernameInput.getText().toString();
                        password = binding.passwordInput.getText().toString();
                        String data = employecode + "$" + password;

                        String input_data = Utility.encodeString(data);
                        String final_data = input_data.trim().replaceAll("\\s+", "");

                        loginViewModel.login(final_data);
                    } else {
                        Toast.makeText(mActivity, "No Internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }

    private void dismissKeyboard() {

        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    private boolean isValid(LoginRequest loginRequest) {
        if (TextUtils.isEmpty(loginRequest.getEmpCode())) {
            return showFormError(binding.username, "Please enter Employee Id");
        } else if (TextUtils.isEmpty(loginRequest.getPwd())) {
            return showFormError(binding.password, "Please enter Password");
        }
        binding.username.setErrorEnabled(false);
        binding.password.setErrorEnabled(false);
        return true;
    }

    private boolean showFormError(TextInputLayout layout, String msg) {
        layout.setError(msg);
        layout.requestFocus();
        return false;
    }


    private void responses() {

        loginViewModel.getLoginResponseMutableLiveData().observe((LifecycleOwner) mActivity, loginResponse -> {
            Utility.cancelProgressbar();

            if (loginResponse != null) {
                if (loginResponse.getStatus().equals("111")) {
                    editor.putString("empCode", loginResponse.getEmp_Code());
                    editor.putString("empName", loginResponse.getEMP_NAME());
                    editor.putString("departId", loginResponse.getDepartment_Id());
                    editor.putString("departName", loginResponse.getDep_name());
                    editor.putString("postId", loginResponse.getPOST_ID());
                    editor.putString("designation", loginResponse.getDesignation());
                    editor.putString("joinDate", loginResponse.getJOIN_DT());
                    editor.putString("brName", loginResponse.getBRANCH_NAME());
                    editor.putString("brId", loginResponse.getBRANCH_ID());
                    editor.putString("session_id", loginResponse.getSession_id());
                    editor.putString("areaId", loginResponse.getArea_id());
                    editor.putString("regionId", loginResponse.getReg_id());
                    editor.putString("zoneId", loginResponse.getFzm_id());

                    editor.apply();
                    Intent toMain = new Intent(mActivity, DashboardActivity.class);
                    startActivity(toMain);
                    mActivity.finish();
                } else {
                    showErrorMsg(loginResponse.getResult());
                }
            } else {
                showErrorMsg(loginResponse.getResult());
            }


        });

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