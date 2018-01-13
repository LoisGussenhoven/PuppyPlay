package com.example.loisgussenhoven.puppyplay;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loisgussenhoven.puppyplay.Entity.Dog;

import java.util.HashMap;

import static android.app.PendingIntent.getActivity;

public class CreateActivity extends AppCompatActivity {
    public Button BTN_Create;

    String[] colornames = {"bruin", "geel", "blauw", "paars", "rood", "groen", "roze", "zwart", "wit"};
    String[] colors = {"795548", "FFEB3B", "03A9F4", "E040FB", "F44336", "8BC34A", "E91E63", "212121", "FFFFFF"};

    // TODO: 13-Jan-18  Create text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Spinner spColor1 = findViewById(R.id.create_sp_color_1);
        Spinner spColor2 = findViewById(R.id.create_sp_color_2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, colornames);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColor1.setAdapter(adapter);
        spColor2.setAdapter(adapter);

        spColor1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView layer = findViewById(R.id.create_iv_dog_layer1);
                layer.setColorFilter( Color.parseColor("#" + colors[i]), PorterDuff.Mode.MULTIPLY );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spColor2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView layer = findViewById(R.id.create_iv_dog_layer2);
                layer.setColorFilter( Color.parseColor("#" +  colors[i]), PorterDuff.Mode.MULTIPLY );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        BTN_Create = findViewById(R.id.AC_BTN_Create);
        BTN_Create.setOnClickListener(view -> {
            EditText ET_Name = findViewById(R.id.AC_ET_Name);
            EditText ET_DogName = findViewById(R.id.AC_ET_DogName);
            RadioGroup radioGroup = findViewById(R.id.AC_RG_Gender);

            int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);

            if(ET_Name.getText().equals("") || ET_DogName.getText().equals("") || selectedRadioButton != null) {
                Toast.makeText(getApplicationContext(),R.string.AllInfo, Toast.LENGTH_LONG).show();
            }
            else {
                Dog dog = new Dog(String.valueOf(ET_DogName.getText()), String.valueOf(ET_Name.getText()), "", colors[spColor1.getSelectedItemPosition()], colors[spColor2.getSelectedItemPosition()]);
                Manager.yourDog = dog;

                Intent i = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(i);
                finish();
            }

        });
    }

    @Override
    public void onBackPressed() {
    }
}
