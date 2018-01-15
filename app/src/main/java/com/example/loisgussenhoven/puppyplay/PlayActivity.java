package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.loisgussenhoven.puppyplay.entity.Dog;

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

        TextView tvDogName = findViewById(R.id.play_tv_dog_name);
        tvDogName.setText(dog.getName());

        hunger = findViewById(R.id.AP_PB_Hunger);
        thirst = findViewById(R.id.AP_PB_Thirst);
        poop = findViewById(R.id.AP_PB_Poop);
        social = findViewById(R.id.AP_PB_Social);

        hunger.setProgress((int)dog.getHunger());
        thirst.setProgress((int)dog.getThirst());
        poop.setProgress((int)dog.getPoop());
        social.setProgress((int)dog.getSocial());

        ImageView layer = findViewById(R.id.AP_IV_Puppy_layer1);
        layer.setColorFilter(Color.parseColor("#" + Manager.yourDog.getColor1()), PorterDuff.Mode.MULTIPLY );
        ImageView layer2 = findViewById(R.id.AP_IV_Puppy_layer2);
        layer2.setColorFilter(Color.parseColor("#" + Manager.yourDog.getColor2()), PorterDuff.Mode.MULTIPLY );

        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                dog.live();
                hunger.post(() -> hunger.setProgress((int)dog.getHunger()));
                thirst.post(() -> thirst.setProgress((int)dog.getThirst()));
                poop.post(() -> poop.setProgress((int)dog.getPoop()));
                social.post(() -> social.setProgress((int)dog.getSocial()));
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
            thirst.setProgress(thirst.getMax());
            hunger.setProgress(hunger.getMax());
            dog.setThirst(100);
            dog.setHunger(100);
        });

        ImageButton btnSettings = findViewById(R.id.AP_IB_Settings);
        btnSettings.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
        });
    }

    @Override
    public void onBackPressed() {

    }
}
