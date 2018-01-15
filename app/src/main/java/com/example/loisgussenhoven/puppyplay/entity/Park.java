package com.example.loisgussenhoven.puppyplay.entity;

import android.location.Location;

/**
 * Created by Nick van Endhoven, 2119719 on 15-Jan-18.
 */

public class Park {
    Location location;
    String uuid;

    public Park(Location location) {
        this.location = location;
        uuid = location.getLatitude() + ":" + location.getLongitude();
    }


    public String getUuid() {
        return uuid;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Park{" +
                "location=" + location.getLatitude() + " " + location.getLongitude() +
                '}';
    }
}
