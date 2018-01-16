package com.example.loisgussenhoven.puppyplay.entity.json;

/**
 * Created by Nick van Endhoven on 16-1-2018.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Directions {

    @SerializedName("geocoded_waypoints")
    @Expose
    public List<GeocodedWaypoint> geocodedWaypoints = null;
    @SerializedName("routes")
    @Expose
    public List<Route> routes = null;
    @SerializedName("status")
    @Expose
    public String status;

}
