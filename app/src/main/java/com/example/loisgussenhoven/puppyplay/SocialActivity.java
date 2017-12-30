package com.example.loisgussenhoven.puppyplay;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.loisgussenhoven.puppyplay.Adapters.SessionAdapter;
import com.example.loisgussenhoven.puppyplay.Datalayer.DBObject;
import com.example.loisgussenhoven.puppyplay.Entity.Dog;
import com.example.loisgussenhoven.puppyplay.Entity.FriendSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SocialActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        List<FriendSession> sessions = DBObject.getInstanceOf().getSession().getAllSessions();
        SessionAdapter adapter = new SessionAdapter(sessions);

        RecyclerView rvSessions = findViewById(R.id.social_rv_sessions);
        rvSessions.setLayoutManager(new LinearLayoutManager(this));
        rvSessions.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

}
