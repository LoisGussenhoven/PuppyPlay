package com.example.loisgussenhoven.puppyplay.handlers;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.android.volley.Request;
import com.example.loisgussenhoven.puppyplay.entity.Park;
import com.example.loisgussenhoven.puppyplay.services.VolleyListener;
import com.example.loisgussenhoven.puppyplay.services.VolleyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick van Endhoven on 10-1-2018.
 */

public class ApiHandler implements VolleyListener {
    public static ApiHandler instance;

    private static final String rootUrl = "https://services7.arcgis.com/21GdwfcLrnTpiju8/arcgis/rest/services/Hondenuitlaatplaatsen/FeatureServer/0/query?where=1%3D1&outFields=&outSR=4326&f=json";
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

    public void getParks(){
        VolleyService.doRequest(rootUrl, Request.Method.GET);
    }

    @Override
    public void onReceive(String body) {
        readJson(body);
    }

    private void readJson(String body){
        List<Park> parks = new ArrayList<>();

        try {
            JSONObject request = new JSONObject(body);
            JSONArray features = request.getJSONArray("features");

            for (int i = 0; i < features.length(); i++){

                if( i % 4 == 0) {
                    JSONObject feature = features.getJSONObject(i);
                    JSONObject geo = feature.getJSONObject("geometry");
                    JSONArray rings = geo.getJSONArray("rings").getJSONArray(0);
                    JSONArray longlat = rings.getJSONArray(0);

                    double lon = longlat.getDouble(0);
                    double lat = longlat.getDouble(1);

                    Location loc = new Location("");
                    loc.setLongitude(lon);
                    loc.setLatitude(lat);

                    Park p = new Park(loc);
                    parks.add(p);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(ApiHandlerResponse l: listeners)
            l.onReceived(parks);
    }

    @Override
    public void onReceiveError(String error) {
        Log.e("ApiHandler", "Received volley error...");
    }
}

