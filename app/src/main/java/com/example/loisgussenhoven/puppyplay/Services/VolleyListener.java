package com.example.loisgussenhoven.puppyplay.Services;

public interface VolleyListener{
    void onReceive(String body);
    void onReceiveError(String error);
}
