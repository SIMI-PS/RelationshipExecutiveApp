package com.manappuram.reapp.Login.Model.Response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("Emp_Code")
    private String Emp_Code;

    @SerializedName("EMP_NAME")
    private String EMP_NAME;

    @SerializedName("Department_Id")
    private String Department_Id;

    @SerializedName("dep_name")
    private String dep_name;

    @SerializedName("POST_ID")
    private String POST_ID;

    @SerializedName("designation")
    private String designation;

    @SerializedName("JOIN_DT")
    private String JOIN_DT;

    @SerializedName("BRANCH_NAME")
    private String BRANCH_NAME;

    @SerializedName("session_id")
    private String session_id;

    @SerializedName("BRANCH_ID")
    private String BRANCH_ID;

    @SerializedName("area_id")
    private String area_id;

    @SerializedName("reg_id")
    private String reg_id;

    @SerializedName("fzm_id")
    private String fzm_id;

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private String result;

    public LoginResponse(String emp_Code, String EMP_NAME, String department_Id, String dep_name, String POST_ID, String designation, String JOIN_DT, String BRANCH_NAME, String session_id, String BRANCH_ID, String area_id, String reg_id, String fzm_id, String status, String result) {
        Emp_Code = emp_Code;
        this.EMP_NAME = EMP_NAME;
        this.Department_Id = department_Id;
        this.dep_name = dep_name;
        this.POST_ID = POST_ID;
        this.designation = designation;
        this.JOIN_DT = JOIN_DT;
        this.BRANCH_NAME = BRANCH_NAME;
        this.session_id = session_id;
        this.BRANCH_ID = BRANCH_ID;
        this.area_id = area_id;
        this.reg_id = reg_id;
        this.fzm_id = fzm_id;
        this.status = status;
        this.result = result;
    }

    public String getEmp_Code() {
        return Emp_Code;
    }

    public void setEmp_Code(String emp_Code) {
        Emp_Code = emp_Code;
    }

    public String getEMP_NAME() {
        return EMP_NAME;
    }

    public void setEMP_NAME(String EMP_NAME) {
        this.EMP_NAME = EMP_NAME;
    }

    public String getDepartment_Id() {
        return Department_Id;
    }

    public void setDepartment_Id(String department_Id) {
        Department_Id = department_Id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getPOST_ID() {
        return POST_ID;
    }

    public void setPOST_ID(String POST_ID) {
        this.POST_ID = POST_ID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJOIN_DT() {
        return JOIN_DT;
    }

    public void setJOIN_DT(String JOIN_DT) {
        this.JOIN_DT = JOIN_DT;
    }

    public String getBRANCH_NAME() {
        return BRANCH_NAME;
    }

    public void setBRANCH_NAME(String BRANCH_NAME) {
        this.BRANCH_NAME = BRANCH_NAME;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getBRANCH_ID() {
        return BRANCH_ID;
    }

    public void setBRANCH_ID(String BRANCH_ID) {
        this.BRANCH_ID = BRANCH_ID;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getFzm_id() {
        return fzm_id;
    }

    public void setFzm_id(String fzm_id) {
        this.fzm_id = fzm_id;
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
