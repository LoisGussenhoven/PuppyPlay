package com.example.loisgussenhoven.puppyplay.entity.json;

/**
 * Created by Nick van Endhoven on 16-1-2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Duration {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("value")
    @Expose
    public Integer value;

}
