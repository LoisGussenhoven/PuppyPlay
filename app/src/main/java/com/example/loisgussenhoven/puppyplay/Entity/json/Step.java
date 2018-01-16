package com.example.loisgussenhoven.puppyplay.entity.json;

/**
 * Created by Nick van Endhoven on 16-1-2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step {
    @SerializedName("distance")
    @Expose
    public Distance distance;
    @SerializedName("duration")
    @Expose
    public Duration duration;
    @SerializedName("end_location")
    @Expose
    public EndLocation endLocation;
    @SerializedName("html_instructions")
    @Expose
    public String htmlInstructions;
    @SerializedName("polyline")
    @Expose
    public Polyline polyline;
    @SerializedName("start_location")
    @Expose
    public StartLocation startLocation;
    @SerializedName("travel_mode")
    @Expose
    public String travelMode;
    @SerializedName("maneuver")
    @Expose
    public String maneuver;
}
