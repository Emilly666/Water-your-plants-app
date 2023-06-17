package com.example.water_your_plants_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.time.LocalTime;

public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TimePicker notificationsTimePicker;
    SwitchCompat switchWater, switchFertilizer;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = getApplicationContext();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.logowyp);

        notificationsTimePicker = findViewById(R.id.notificationsTimePicker);

        if(DateFormat.is24HourFormat(this)){
            notificationsTimePicker.setIs24HourView(true);
        }

        LocalTime notificationsTime = SharedPreferencesManager.getInstance(context).getNotificationsTime();
        notificationsTimePicker.setHour(notificationsTime.getHour());
        notificationsTimePicker.setMinute(notificationsTime.getMinute());

        switchWater = findViewById(R.id.switchWater);
        switchFertilizer = findViewById(R.id.switchFertilizer);

        switchWater.setChecked(SharedPreferencesManager.getInstance(context).getNotificationsWater());
        switchFertilizer.setChecked(SharedPreferencesManager.getInstance(context).getNotificationsFertilize());
    }
}