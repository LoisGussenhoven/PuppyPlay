package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;


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


    }
}
