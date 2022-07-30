package com.manappuram.reapp.Modules.RETracker.Model.Response;

import com.google.gson.annotations.SerializedName;

public class EndActivityResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("result")
    public String result;

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
}
