package com.example.loisgussenhoven.puppyplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CreateActivity extends AppCompatActivity {
        public Button BTN_Create;
        public String name = "";
        public String dogName = "";
        public String gender = "";
        public String colour = "";
        public int hunger = 100;
        public int thirst = 100;
        public int poop = 100;
        public int social = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        BTN_Create = findViewById(R.id.AC_BTN_Create);
        BTN_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dog dog = new Dog(dogName, name, colour, gender, hunger, thirst, poop, social);

                //TODO: set colour of dog

                EditText ET_Name = findViewById(R.id.AC_ET_Name);
                dog.setNameOwner(ET_Name.getText().toString());

                EditText ET_DogName = findViewById(R.id.AC_ET_DogName);
                dog.setName(ET_DogName.getText().toString());

                RadioGroup radioGroup = findViewById(R.id.AC_RG_Gender);
                int selectedRadioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonID);
                String selectedRadioButtonText = selectedRadioButton.getText().toString();
                dog.setGender(selectedRadioButtonText);

                //TODO: what if button isn't checked, empty name or empty dogname

                Log.e("dog", dog.toString());

                Intent i = new Intent(getApplicationContext(), PlayActivity.class);
                i.putExtra("DOG", dog);
                startActivity(i);
                finish();
            }
        });
    }
}
