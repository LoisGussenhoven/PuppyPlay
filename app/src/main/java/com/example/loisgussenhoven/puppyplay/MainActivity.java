package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.example.loisgussenhoven.puppyplay.handlers.ApiHandler;
import com.example.loisgussenhoven.puppyplay.handlers.ApiHandlerResponse;
import com.example.loisgussenhoven.puppyplay.datalayer.DBObject;
import com.example.loisgussenhoven.puppyplay.entity.Dog;
import com.example.loisgussenhoven.puppyplay.entity.Park;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements ApiHandlerResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBObject.init(getApplicationContext());
        ApiHandler.init(getApplicationContext());

        ApiHandler.getInstance().attachListener(this);

        ApiHandler.getInstance().getParks();

        ImageButton ibPaw = findViewById(R.id.main_ib_paw);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        animation.reset();
        animation.setRepeatMode(Animation.INFINITE);
        ibPaw.startAnimation(animation);


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
        }, 5000);
    }

    @Override
    public void onReceived(List<Park> parks) {
        Manager.parks = parks;
    }
}
