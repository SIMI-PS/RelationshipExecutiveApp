package com.manappuram.reapp.Login.Repository;

import com.manappuram.reapp.Base.BaseRepository;
import com.manappuram.reapp.Base.BaseResponse;
import com.manappuram.reapp.Base.Event;
import com.manappuram.reapp.Interfaces.ResponseListener;
import com.manappuram.reapp.Login.Model.Response.LoginResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.ActivityResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.EndActivityResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.StartActivityResponse;
import com.manappuram.reapp.Retrofit.RetrofitClient;
import com.manappuram.reapp.Retrofit.RetrofitRequest;

import okhttp3.Headers;
import retrofit2.Call;

public class LoginRepository extends BaseRepository {

    public void loginUser(String final_data, SuccessResponse successResponse) {

        Call<LoginResponse> call = RetrofitClient.getAPIInterface().Login(final_data);

        new RetrofitRequest<>(call, new ResponseListener<LoginResponse>() {
            @Override
            public void onResponse(LoginResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));

            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue();
    }

    public void logOutUser(String final_data, SuccessResponse successResponse) {

        Call<BaseResponse> call = RetrofitClient.getAPIInterface().RelationAppLogout(final_data);

        new RetrofitRequest<>(call, new ResponseListener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));

            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue();
    }


    public void getActivity(String final_data, SuccessResponse successResponse) {

        Call<ActivityResponse> call = RetrofitClient.getAPIInterface().getRElationshipExcutiveActivities(final_data);

        new RetrofitRequest<>(call, new ResponseListener<ActivityResponse>() {
            @Override
            public void onResponse(ActivityResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));

            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue();
    }

    public void startActivity(String final_data, SuccessResponse successResponse) {

        Call<StartActivityResponse> call = RetrofitClient.getAPIInterface().getRelationshipExcTravelLogStartInsert(final_data);

        new RetrofitRequest<>(call, new ResponseListener<StartActivityResponse>() {
            @Override
            public void onResponse(StartActivityResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));

            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue();
    }

    public void endActivity(String final_data, SuccessResponse successResponse) {

        Call<EndActivityResponse> call = RetrofitClient.getAPIInterface().getRelationshipExcTravelLogEndUpdate(final_data);

        new RetrofitRequest<>(call, new ResponseListener<EndActivityResponse>() {
            @Override
            public void onResponse(EndActivityResponse response, Headers headers) {
                if (null != successResponse) {
                    successResponse.onResponse(response);
                }
            }

            @Override
            public void onError(int status, BaseResponse errors) {
                errorsMutable.postValue(new Event<>(errors));

            }


            @Override
            public void onFailure(Throwable throwable) {
                failMessageMutable.postValue(new Event<>(throwable.getMessage()));
            }

        }).enqueue();
    }
}
