package com.example.water_your_plants_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TimePicker notificationsTimePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.logowyp);

        notificationsTimePicker = findViewById(R.id.notificationsTimePicker);

        if(DateFormat.is24HourFormat(this)){
            notificationsTimePicker.setIs24HourView(true);
        }


    }
}