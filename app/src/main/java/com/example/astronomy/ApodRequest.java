package com.example.astronomy;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class ApodRequest {
    private String url;
    private ApodCallback callback;

    public ApodRequest(String url, ApodCallback callback) {
        this.url = url;
        this.callback = callback;
    }

    // ApodCallback interface definition will be added later
    public void execute() {
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                response -> {
                    try {
                        String date = response.getString("date");
                        String description = response.getString("explanation");
                        String photoUrl = response.getString("url");
                        callback.onSuccess(date, description, photoUrl);
                    } catch (JSONException e) {
                        callback.onError();
                    }
                },
                error -> {
                    callback.onError();
                });
        Volley.newRequestQueue(MyApplication.context).add(request);
    }

    public interface ApodCallback {
        void onSuccess(String date, String description, String photoUrl);
        void onError();
    }


}

