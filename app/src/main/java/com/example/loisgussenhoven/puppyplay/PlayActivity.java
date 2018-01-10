package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.loisgussenhoven.puppyplay.Entity.Dog;


public class PlayActivity extends AppCompatActivity{

    public ProgressBar hunger;
    public ProgressBar thirst;
    public ProgressBar poop;
    public ProgressBar social;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Dog dog = (Dog) getIntent().getSerializableExtra("DOG");

        hunger = findViewById(R.id.AP_PB_Hunger);
        hunger.setProgress(dog.getHunger());

        thirst = findViewById(R.id.AP_PB_Thirst);
        thirst.setProgress(dog.getThirst());

        poop = findViewById(R.id.AP_PB_Poop);
        poop.setProgress(dog.getPoop());

        social = findViewById(R.id.AP_PB_Social);
        social.setProgress(dog.getSocial());

        ImageButton btnSessions = findViewById(R.id.AP_IB_Friends);
        btnSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SessionActivity.class);
                startActivity(i);
            }
        });

        ImageButton btnMaps = findViewById(R.id.AP_IB_Map);
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        ImageButton btnNeeds = findViewById(R.id.AP_IB_Needs);
        btnNeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dog.getThirst() < 100 || dog.getHunger() < 100)
                    thirst.setProgress(dog.getThirst() + 100);
                    hunger.setProgress(dog.getHunger() + 100);

            }
        });

        // TODO: 30-Dec-17 Disable back button 
    }
}
