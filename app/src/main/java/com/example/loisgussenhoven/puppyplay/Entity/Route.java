package com.example.loisgussenhoven.puppyplay.entity;

/**
 * Created by Nick van Endhoven on 16-1-2018.
 */

public class Route {
    private String name, information;

    public Route(String name, String information) {
        this.name = name;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return information;
    }
}
