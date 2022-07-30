package com.manappuram.reapp.Modules.RETracker.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityResponse {

    @SerializedName("listval")
    @Expose
    public ArrayList<ActivityListData> activityListData = null;

    public ArrayList<ActivityListData> getActivityData() {
        return activityListData;
    }

    @SerializedName("status")
    public String status;

    @SerializedName("result")
    public String result;

    public static class ActivityListData implements Serializable {


        @SerializedName("activityid")
        public String activityid;

        @SerializedName("activityName")
        public String activityName;


        public ActivityListData(String activityid, String activityName) {
            this.activityid = activityid;
            this.activityName = activityName;
        }

        public String getActivityid() {
            return activityid;
        }

        public void setActivityid(String activityid) {
            this.activityid = activityid;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }
    }

    public ArrayList<ActivityListData> getActivityListData() {
        return activityListData;
    }

    public void setActivityListData(ArrayList<ActivityListData> activityListData) {
        this.activityListData = activityListData;
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
