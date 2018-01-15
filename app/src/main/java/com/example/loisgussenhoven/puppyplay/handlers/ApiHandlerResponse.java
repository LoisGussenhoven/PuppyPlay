package com.example.loisgussenhoven.puppyplay.handlers;

import com.example.loisgussenhoven.puppyplay.entity.Park;

import java.util.List;

public interface ApiHandlerResponse {
    void onReceived(List<Park> parks);
}
