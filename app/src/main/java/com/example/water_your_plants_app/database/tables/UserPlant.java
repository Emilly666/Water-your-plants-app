package com.example.water_your_plants_app.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userPlants")
public class UserPlant {

    @PrimaryKey(autoGenerate = true)
    public long userPlant_id;

    public long plant_id;
    public String plantNickname;

    public UserPlant(long plant_id, String plantNickname){
        this.plant_id = plant_id;
        this.plantNickname = plantNickname;
    }
}