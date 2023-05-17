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

    public Table_PlantType(){
        this.typeName = "type1";
        this.soil = "nice soil";
        this.fertilizerType = "f type1";
    }
}
