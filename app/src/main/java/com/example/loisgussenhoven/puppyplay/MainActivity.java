package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.loisgussenhoven.puppyplay.ApiHandling.ApiHandler;
import com.example.loisgussenhoven.puppyplay.Datalayer.DBObject;
import com.example.loisgussenhoven.puppyplay.Entity.Dog;
import com.example.loisgussenhoven.puppyplay.Entity.FriendSession;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBObject.init(getApplicationContext());
        ApiHandler.init(getApplicationContext());

        new Timer().schedule(new TimerTask() {
            public void run() {
                SharedPreferences prefs = getSharedPreferences("PUPPYPLAYPREFS", MODE_PRIVATE);

                if (prefs.getBoolean("created", false)) {
                    Dog dog = new Dog(
                            prefs.getString("dog_name", ""),
                            prefs.getString("name", ""),
                            prefs.getString("gender", ""),
                            prefs.getString("color1", ""),
                            prefs.getString("color2", "")
                    );

                    Manager.yourDog = dog;
                    startActivity(new Intent(MainActivity.this, PlayActivity.class));
                    return;
                }

                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        }, 2000);


        // TODO: 12-1-2018 Read your dog data 
    }

}
