package com.example.water_your_plants_app.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plants")
public class Table_Plant {

    @PrimaryKey(autoGenerate = true)
    public long plant_id;

    public long plantType_id;

    public String plantName;
    public int temperatureFrom;
    public int temperatureTo;
    public String light;
    public String humidity;
    public String wateringFrequency;
    public String fertilizerFrequency;

    public Table_Plant(){

    }
}
