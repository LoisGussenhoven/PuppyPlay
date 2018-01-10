package com.example.loisgussenhoven.puppyplay.ApiHandling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.example.loisgussenhoven.puppyplay.Services.VolleyListener;
import com.example.loisgussenhoven.puppyplay.Services.VolleyService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick van Endhoven on 10-1-2018.
 */

public class ApiHandler implements VolleyListener {
    public static ApiHandler instance;

    private static final String rootUrl = "";
    List<ApiHandlerResponse> listeners;

    public static ApiHandler getInstance() {
        if (instance == null)
            Log.e("ApiHandler", "needs init first!");
        return instance;
    }

    public static void init(Context context) {
        if (instance == null)
            instance = new ApiHandler(context);
    }

    private ApiHandler(Context context){
        listeners = new ArrayList<>();
        VolleyService.getInstance(this, context);
    }

    public void attachListener(ApiHandlerResponse listener){
        this.listeners.add(listener);
    }

    public void getSomething(double lat, double lon){
       // VolleyService.doRequest(rootUrl
       //
       //         , Request.Method.GET);
    }

    @Override
    public void onReceive(String body) {
        Log.e("ApiHandler", "received: " + body);

        for(ApiHandlerResponse l: listeners)
            l.onReceived(body);
    }

    @Override
    public void onReceiveError(String error) {
        Log.e("ApiHandler", "Received volley error...");
    }
}

