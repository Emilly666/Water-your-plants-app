package com.example.water_your_plants_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.appcompat.widget.Toolbar;

import com.example.water_your_plants_app.database.AppDatabase;

import java.util.List;


public class AddPlantSpeciesActivity extends AppCompatActivity {

    EditText speciesName;
    Toolbar toolbar;
    NumberPicker plantTypePicker;
    Context context;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_plant);

        context = getApplicationContext();
        db = AppDatabase.getDatabase(context);

        speciesName = findViewById(R.id.speciesName);
        toolbar = findViewById(R.id.toolbar);
        plantTypePicker = findViewById(R.id.plantTypePicker);

        //get entered species name from popup
        Bundle b = getIntent().getExtras();
        if (b != null) {
            speciesName.setText(b.getString("speciesName"));
        }

        //set toolbar
        toolbar.setTitle(R.string.add_new_species);
        toolbar.setNavigationIcon(R.drawable.logowyp);
        setSupportActionBar(toolbar);

        //initialize numberPicker
        List<String> typesList = db.dao_plantType().getAllPlantTypesNames();
        plantTypePicker.setMinValue(0);
        plantTypePicker.setMaxValue(typesList.size()-1);
        String[] array = typesList.toArray(new String[0]);
        plantTypePicker.setDisplayedValues(array);


    }
}