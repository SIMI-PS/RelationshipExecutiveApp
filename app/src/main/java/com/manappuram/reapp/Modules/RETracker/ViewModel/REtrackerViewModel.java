package com.manappuram.reapp.Modules.RETracker.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.manappuram.reapp.Login.Repository.LoginRepository;
import com.manappuram.reapp.Modules.RETracker.Model.Response.ActivityResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.EndActivityResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.StartActivityResponse;

public class REtrackerViewModel extends ViewModel {

    protected LoginRepository loginRepository;
    private MutableLiveData<ActivityResponse> activityResponseMutableLiveData;
    private MutableLiveData<StartActivityResponse> startActivityResponseMutableLiveData;
    private MutableLiveData<EndActivityResponse> endActivityResponseMutableLiveData;



    public REtrackerViewModel() {
        loginRepository = new LoginRepository();
        activityResponseMutableLiveData = new MutableLiveData<>();
        startActivityResponseMutableLiveData = new MutableLiveData<>();
        endActivityResponseMutableLiveData = new MutableLiveData<>();
    }

    public void getActivity(String data) {
        loginRepository.getActivity(data,
                (LoginRepository.SuccessResponse<ActivityResponse>) activityResponse -> {
                    activityResponseMutableLiveData.setValue(activityResponse);
                });
    }

    public void startActivity(String data) {
        loginRepository.startActivity(data,
                (LoginRepository.SuccessResponse<StartActivityResponse>) startActivityResponse -> {
                    startActivityResponseMutableLiveData.setValue(startActivityResponse);
                });
    }

    public void endActivity(String data) {
        loginRepository.endActivity(data,
                (LoginRepository.SuccessResponse<EndActivityResponse>) endActivityResponse -> {
                    endActivityResponseMutableLiveData.setValue(endActivityResponse);
                });
    }

    public MutableLiveData<ActivityResponse> getActivityResponseMutableLiveData() {
        return activityResponseMutableLiveData;
    }

    public MutableLiveData<StartActivityResponse> getStartActivityResponseMutableLiveData() {
        return startActivityResponseMutableLiveData;
    }

    public MutableLiveData<EndActivityResponse> getEndActivityResponseMutableLiveData() {
        return endActivityResponseMutableLiveData;
    }
}
