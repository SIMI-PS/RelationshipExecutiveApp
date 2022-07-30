package com.manappuram.reapp.Interfaces;

import com.manappuram.reapp.Base.BaseResponse;
import com.manappuram.reapp.Login.Model.Response.LoginResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.ActivityResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.EndActivityResponse;
import com.manappuram.reapp.Modules.RETracker.Model.Response.StartActivityResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("getReletionshipExecutiveLogin")
    @FormUrlEncoded
    Call<LoginResponse> Login(@Field("Indata") String Indata);

    @POST("RelationAppLogout")
    @FormUrlEncoded
    Call<BaseResponse> RelationAppLogout(@Field("Indata") String Indata);

    @POST("getRElationshipExcutiveActivities")
    @FormUrlEncoded
    Call<ActivityResponse> getRElationshipExcutiveActivities(@Field("Indata") String Indata);

    @POST("getRelationshipExcTravelLogStartInsert")
    @FormUrlEncoded
    Call<StartActivityResponse> getRelationshipExcTravelLogStartInsert(@Field("Indata") String Indata);

    @POST("getRelationshipExcTravelLogEndUpdate")
    @FormUrlEncoded
    Call<EndActivityResponse> getRelationshipExcTravelLogEndUpdate(@Field("Indata") String Indata);
}
