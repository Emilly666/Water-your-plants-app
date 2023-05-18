package com.example.water_your_plants_app.database.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "userPlants",
        foreignKeys = @ForeignKey(entity = Plant.class, parentColumns = "plant_id", childColumns = "myPlant_id", onDelete = ForeignKey.CASCADE))
public class UserPlant {

    @PrimaryKey(autoGenerate = true)
    public long userPlant_id;

    public long myPlant_id;
    public String plantNickname;

    public UserPlant(long myPlant_id, String plantNickname){
        this.myPlant_id = myPlant_id;
        this.plantNickname = plantNickname;
    }
}