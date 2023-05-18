package com.example.water_your_plants_app.database.tables;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.List;

@Entity(tableName = "plantTypes")
public class PlantType {

    @PrimaryKey(autoGenerate = true)
    public long plantType_id;

    public String typeName;
    public String soil;
    public String fertilizerType;

    public PlantType(String typeName, String soil, String fertilizerType){
        this.typeName = typeName;
        this.soil = soil;
        this.fertilizerType = fertilizerType;
    }
}
