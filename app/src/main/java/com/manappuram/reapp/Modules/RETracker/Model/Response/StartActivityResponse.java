package com.manappuram.reapp.Modules.RETracker.Model.Response;

import com.google.gson.annotations.SerializedName;

public class StartActivityResponse {

    @SerializedName("trav_id")
    public String trav_id;

    @SerializedName("status")
    public String status;

    @SerializedName("result")
    public String result;

    public String getTrav_id() {
        return trav_id;
    }

    public void setTrav_id(String trav_id) {
        this.trav_id = trav_id;
    }

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
