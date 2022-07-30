package com.manappuram.reapp.Login.Model.Request;

import com.manappuram.reapp.Utils.Utility;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("empCode")
    @Expose
    private String empCode;

    @SerializedName("pwd")
    @Expose
    private String pwd;

    public LoginRequest(String empCode, String pwd) {

        this.empCode = empCode;
        this.pwd = Utility.encodeString(pwd);
    }

    public LoginRequest() {

    }


    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
