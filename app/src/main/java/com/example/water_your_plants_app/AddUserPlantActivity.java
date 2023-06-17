package com.example.water_your_plants_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;


public class AddUserPlantActivity extends AppCompatActivity {

    EditText speciesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_plant);

        speciesName = findViewById(R.id.speciesName);

        Bundle b = getIntent().getExtras();
        if(b != null)
            speciesName.setText(b.getString("speciesName"));
        }
}