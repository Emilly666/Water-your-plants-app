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
    public int humidity;
    public int wateringFrequency;
    public int fertilizerFrequency;

    public Table_Plant(long plantType_id, String plantName, int temperatureFrom, int temperatureTo, String light, int humidity, int wateringFrequency, int fertilizerFrequency){
        this.plantType_id = plantType_id;
        this.plantName = plantName;
        this.temperatureFrom = temperatureFrom;
        this.temperatureTo = temperatureTo;
        this.light = light;
        this.humidity = humidity;
        this.wateringFrequency = wateringFrequency;
        this.fertilizerFrequency = fertilizerFrequency;
    }
}
