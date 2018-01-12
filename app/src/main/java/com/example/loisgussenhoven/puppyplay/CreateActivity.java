package com.example.loisgussenhoven.puppyplay;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.loisgussenhoven.puppyplay.Entity.Dog;

import static android.app.PendingIntent.getActivity;

public class CreateActivity extends AppCompatActivity {
        public Button BTN_Create;
        public String name = "";
        public String dogName = "";
        public String gender = "";
        public int colour = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        BTN_Create = findViewById(R.id.AC_BTN_Create);
        BTN_Create.setOnClickListener(view -> {
            Dog dog = new Dog(dogName, name, gender, colour);
            EditText ET_Name = findViewById(R.id.AC_ET_Name);
            EditText ET_DogName = findViewById(R.id.AC_ET_DogName);
            RadioGroup radioGroup = findViewById(R.id.AC_RG_Gender);
            int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);

            if(name != "" || dogName != "" || selectedRadioButton != null) {
                dog.setNameOwner(ET_Name.getText().toString());
                dog.setName(ET_DogName.getText().toString());
                String selectedRadioButtonText = selectedRadioButton.getText().toString();
                dog.setGender(selectedRadioButtonText);

                Log.e("dog", dog.toString());
                Manager.yourDog = dog;
                Intent i = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),R.string.AllInfo, Toast.LENGTH_LONG).show();

            }


        });
    }

    // TODO: 30-Dec-17 Disable back button
}
