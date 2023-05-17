package com.example.water_your_plants_app.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userPlants")
public class Table_UserPlant {

    @PrimaryKey(autoGenerate = true)
    public int userPlant_id;

    public int plant_id;
    public String plantNickname;

    public Table_UserPlant(){

    }
}