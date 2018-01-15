package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.loisgussenhoven.puppyplay.datalayer.DBObject;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnReset = findViewById(R.id.settings_btn_reset);
        btnReset.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), CreateActivity.class);
            startActivity(i);
        });

        Button btnResetSessions = findViewById(R.id.settings_btn_reset_sessions);
        btnResetSessions.setOnClickListener(view -> {
            DBObject.getInstanceOf().getSession().deleteAllSessions();
            Toast.makeText(getApplicationContext(), "Sessions cleared", Toast.LENGTH_LONG); // TODO: 14-1-2018 Localize 
        });
    }
}
