package com.example.loisgussenhoven.puppyplay.entity.json;

/**
 * Created by Nick van Endhoven on 16-1-2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Southwest {

    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lng")
    @Expose
    public Double lng;

}
