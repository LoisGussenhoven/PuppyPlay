package com.example.loisgussenhoven.puppyplay.services;

public interface VolleyListener{
    void onReceive(String body);
    void onReceiveError(String error);
}
