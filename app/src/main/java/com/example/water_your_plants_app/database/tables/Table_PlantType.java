package com.example.water_your_plants_app.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plantTypes")
public class Table_PlantType {

    @PrimaryKey(autoGenerate = true)
    public long plantType_id;

    public String typeName;
    public String soil;
    public String fertilizerType;

    public Table_PlantType(String typeName, String soil, String fertilizerType){
        this.typeName = typeName;
        this.soil = soil;
        this.fertilizerType = fertilizerType;
    }
}
