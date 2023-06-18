package com.example.water_your_plants_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.water_your_plants_app.database.AppDatabase;
import com.google.android.material.slider.RangeSlider;

import java.util.List;


public class AddPlantSpeciesActivity extends AppCompatActivity {

    EditText speciesName;
    Toolbar toolbar;
    NumberPicker plantTypePicker;
    RangeSlider temperatureSlider;
    TextView temperatureFrom, temperatureTo;

    Context context;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        context = getApplicationContext();
        db = AppDatabase.getDatabase(context);

        speciesName = findViewById(R.id.speciesName);
        toolbar = findViewById(R.id.toolbar);
        plantTypePicker = findViewById(R.id.plantTypePicker);
        temperatureSlider = findViewById(R.id.temperatureSlider);
        temperatureFrom = findViewById(R.id.temperatureFrom);
        temperatureTo = findViewById(R.id.temperatureTo);

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

        //setup temperature slider
        temperatureSlider.setValues(0f,50f);
        temperatureSlider.addOnChangeListener((slider, value, fromUser) -> {
            temperatureFrom.setText(String.format("%d°C", Math.round(slider.getValues().get(0))));
            temperatureTo.setText(String.format("%d°C", Math.round(slider.getValues().get(1))));
        });

    }
}