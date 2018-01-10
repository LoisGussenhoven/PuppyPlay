package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
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
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        }, 2000);

        // Disable this for test sessions
        testSessions();
    }

    private void testSessions(){
        Dog d1 = new Dog("Blues", "Nick van Endhoven", "male", 0);
        Dog d2 = new Dog("Spronko", "Paardu Staart", "female", 1);

        Location loc = new Location("");
        loc.setLongitude(4.754586f);
        loc.setLatitude(51.573752f);

        FriendSession f1 = new FriendSession(new Date(), "13:47", "12", d1, loc);
        FriendSession f2 = new FriendSession(new Date(), "14:01", "10", d2, loc);
        FriendSession f3 = new FriendSession(new Date(), "14:32", "12", d2, loc);

        DBObject.getInstanceOf().getDog().addDog(d1);
        DBObject.getInstanceOf().getDog().addDog(d2);

        DBObject.getInstanceOf().getSession().addSession(f1);
        DBObject.getInstanceOf().getSession().addSession(f2);
        DBObject.getInstanceOf().getSession().addSession(f3);
    }
}
