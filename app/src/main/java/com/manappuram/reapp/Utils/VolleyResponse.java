package com.manappuram.reapp.Utils;

import org.json.JSONObject;

public interface VolleyResponse {

    void success(JSONObject response);
    void error(String msg);

}
