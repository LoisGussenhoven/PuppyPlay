package com.example.loisgussenhoven.puppyplay.Entity;

import android.graphics.Bitmap;
import android.location.Location;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Nick van Endhoven, 2119719 on 30-Dec-17.
 */

public class FriendSession implements Serializable{

    private String uuid;
    private Date date;
    private String time;
    private String duration;
    private Dog other;
    private Location location;

    public FriendSession(String uuid, Date date, String time, String duration, Dog other, Location location) {
        this.uuid =  uuid;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.other = other;
        this.location = location;
    }

    public FriendSession(Date date, String time, String duration, Dog other, Location location) {
        this.uuid =  UUID.randomUUID().toString();
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.other = other;
        this.location = location;
    }

    public String getUuid() {
        return uuid;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDuration() {
        return duration;
    }

    public Dog getOther() {
        return other;
    }

    public Location getLocation() {
        return location;
    }

}
