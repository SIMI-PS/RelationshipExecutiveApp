package com.manappuram.reapp.Login.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manappuram.reapp.Base.BaseResponse;
import com.manappuram.reapp.Login.Model.Request.LoginRequest;
import com.manappuram.reapp.Login.Model.Response.LoginResponse;
import com.manappuram.reapp.Login.Repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    public LoginRequest loginRequest;
    protected LoginRepository loginRepository;
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData;
    private MutableLiveData<BaseResponse> logoutResponseMutableLiveData;

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public LoginViewModel() {
        loginRepository = new LoginRepository();
        loginResponseMutableLiveData = new MutableLiveData<>();
        logoutResponseMutableLiveData =  new MutableLiveData<>();
        loginRequest = new LoginRequest();
    }

    public void login(String final_data ) {

        Log.d("request", final_data);

        loginRepository.loginUser(final_data,
                (LoginRepository.SuccessResponse<LoginResponse>) loginResponse -> {
                    loginResponseMutableLiveData.setValue(loginResponse);
                });
    }

    public void logOutUser(String final_data ) {

        Log.d("request", final_data);

        loginRepository.logOutUser(final_data,
                (LoginRepository.SuccessResponse<BaseResponse>) baseResponse -> {
                    logoutResponseMutableLiveData.setValue(baseResponse);
                });
    }


    public MutableLiveData<LoginResponse> getLoginResponseMutableLiveData() {
        return loginResponseMutableLiveData;
    }

    public MutableLiveData<BaseResponse> getLogoutResponseMutableLiveData() {
        return logoutResponseMutableLiveData;
    }
}
