package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.loisgussenhoven.puppyplay.Entity.Dog;

import java.util.Timer;
import java.util.TimerTask;


public class PlayActivity extends AppCompatActivity{

    public ProgressBar hunger;
    public ProgressBar thirst;
    public ProgressBar poop;
    public ProgressBar social;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Dog dog = Manager.yourDog;

        hunger = findViewById(R.id.AP_PB_Hunger);
        thirst = findViewById(R.id.AP_PB_Thirst);
        poop = findViewById(R.id.AP_PB_Poop);
        social = findViewById(R.id.AP_PB_Social);

        hunger.setProgress((int)dog.getHunger());
        thirst.setProgress((int)dog.getThirst());
        poop.setProgress((int)dog.getPoop());
        social.setProgress((int)dog.getSocial());


        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                dog.live();
                hunger.post(() -> hunger.setProgress((int)dog.getHunger()));
                thirst.post(() -> thirst.setProgress((int)dog.getThirst()));
                poop.post(() -> poop.setProgress((int)dog.getPoop()));
                social.post(() -> social.setProgress((int)dog.getSocial()));
                social.getProgress();

            }
        };timer.schedule (hourlyTask,0,1000);

        ImageButton btnSessions = findViewById(R.id.AP_IB_Friends);
        btnSessions.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SessionActivity.class);
            startActivity(i);
        });

        ImageButton btnMaps = findViewById(R.id.AP_IB_Map);
        btnMaps.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(i);
        });

        ImageButton btnNeeds = findViewById(R.id.AP_IB_Needs);
        btnNeeds.setOnClickListener(view -> {
            if(dog.getThirst() < 100 || dog.getHunger() < 100)
                thirst.setProgress((int)dog.getThirst() + 100);
                hunger.setProgress((int)dog.getHunger() + 100);

        });
    }

    @Override
    public void onBackPressed() {

    }
}
