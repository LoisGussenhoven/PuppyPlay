package com.example.loisgussenhoven.puppyplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.loisgussenhoven.puppyplay.adapters.SessionAdapter;
import com.example.loisgussenhoven.puppyplay.datalayer.DBObject;
import com.example.loisgussenhoven.puppyplay.entity.FriendSession;

import java.util.List;

public class SessionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        List<FriendSession> sessions = DBObject.getInstanceOf().getSession().getAllSessions();
        SessionAdapter adapter = new SessionAdapter(sessions);



        RecyclerView rvSessions = findViewById(R.id.social_rv_sessions);
        rvSessions.setLayoutManager(new LinearLayoutManager(this));
        rvSessions.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

}
