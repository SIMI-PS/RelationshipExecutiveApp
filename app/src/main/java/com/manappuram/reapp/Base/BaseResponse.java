package com.manappuram.reapp.Base;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("doorkey")
    private String doorkey;
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private String result;

    public BaseResponse(String i, String s) {
        status = i;
        result = s;
    }

    public BaseResponse() {
    }
    public String getDoorkey() {        return doorkey;    }

    public void setDoorkey(String doorkey) {        this.doorkey = doorkey;    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "BaseResponse{" +
                "doorkey='" + doorkey + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
