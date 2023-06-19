package com.example.water_your_plants_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.example.water_your_plants_app.database.AppDatabase;
import com.example.water_your_plants_app.database.tables.Plant;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import java.util.List;

public class AddPlantSpeciesActivity extends AppCompatActivity {

    EditText speciesName;
    Toolbar toolbar;
    NumberPicker plantTypePicker, wateringPicker, fertilizingPicker;
    RangeSlider temperatureSlider;
    Slider lightSlider, humiditySlider;
    TextView temperatureFrom, temperatureTo, humidityLabel;
    Button addPlantSpeciesButton;

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
        wateringPicker = findViewById(R.id.waterFrequencyPicker);
        fertilizingPicker = findViewById(R.id.fertilizerFrequencyPicker);
        addPlantSpeciesButton = findViewById(R.id.addPlantSpeciesButton);
        lightSlider = findViewById(R.id.lightSlider);
        humidityLabel = findViewById(R.id.humidityLabel);
        humiditySlider = findViewById(R.id.humiditySlider);

        //setup toolbar
        toolbar.setTitle(R.string.add_new_species);
        toolbar.setNavigationIcon(R.drawable.logowyp);
        toolbar.setTitle(R.string.add_new_species);
        setSupportActionBar(toolbar);

        //get entered species name from popup
        Bundle b = getIntent().getExtras();
        if (b != null) {
            speciesName.setText(b.getString("speciesName"));
        }

        //initialize plantTypePicker
        List<String> typesList = db.dao_plantType().getAllPlantTypesNames();
        plantTypePicker.setMinValue(0);
        plantTypePicker.setMaxValue(typesList.size()-1);
        String[] array = typesList.toArray(new String[0]);
        plantTypePicker.setDisplayedValues(array);

        //setup temperature slider
        temperatureSlider.setValues(0f,50f);
        temperatureSlider.setTickVisible(false);
        temperatureFrom.setText( String.format(getResources().getString(R.string.temp), 0) );
        temperatureTo.setText( String.format(getResources().getString(R.string.temp), 50) );
        temperatureSlider.addOnChangeListener((slider, value, fromUser) -> {
            temperatureFrom.setText( String.format(getResources().getString(R.string.temp), Math.round(slider.getValues().get(0))) );
            temperatureTo.setText( String.format(getResources().getString(R.string.temp), Math.round(slider.getValues().get(1))) );
        });

        //setup humidity slider
        humidityLabel.setText( String.format(getResources().getString(R.string.preferable_humidity), 50) );
        humiditySlider.addOnChangeListener((slider, value, fromUser) -> {
            humidityLabel.setText( String.format(getResources().getString(R.string.preferable_humidity), Math.round(slider.getValue())) );
        });

        //initialize wateringPicker
        wateringPicker.setMinValue(1);
        wateringPicker.setMaxValue(100);

        //initialize fertilizingPicker
        fertilizingPicker.setMinValue(1);
        fertilizingPicker.setMaxValue(100);
    }

    public void addPlantSpeciesButtonClicked(View view) {
        String str = speciesName.getText().toString();
        if ( str.isEmpty() ){
            Toast.makeText(context, "Enter species name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( (str.startsWith(" ") && str.endsWith(" ")) ){
            Toast.makeText(context, "Incorrect species name!", Toast.LENGTH_SHORT).show();
            return;
        }
        List<String> plantList =  db.dao_plant().getAllPlantNames();
        plantList.replaceAll(String::toLowerCase);
        if( plantList.contains(str.toLowerCase()) ){
            Toast.makeText(context, "That plant species already exist in database!", Toast.LENGTH_SHORT).show();
            return;
        }

        long typeId = db.dao_plantType().getTypeIdByName(plantTypePicker.getDisplayedValues()[plantTypePicker.getValue()]);

        String light;
        switch( Math.round(lightSlider.getValue())  ) {
            case 0:
                light = "some light 1";
                break;
            case 1:
                light = "some light 2";
                break;
            case 3:
                light = "some light 4";
                break;
            case 4:
                light = "some light 5";
                break;
            default: //default position of seekBar
                light = "some light 3";
        }

        Plant newPlant = new Plant(
                    typeId,
                    speciesName.getText().toString(),
                    Math.round(temperatureSlider.getValues().get(0)),
                    Math.round(temperatureSlider.getValues().get(1)),
                    light,
                    Math.round(humiditySlider.getValue()),
                    wateringPicker.getValue(),
                    fertilizingPicker.getValue()
                );
        db.dao_plant().insertPlant(newPlant);
        this.finish();
    }
}