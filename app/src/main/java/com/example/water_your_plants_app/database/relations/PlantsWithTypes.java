package com.example.water_your_plants_app.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.water_your_plants_app.database.tables.Plant;
import com.example.water_your_plants_app.database.tables.PlantType;

public class PlantsWithTypes {
    @Embedded
    public Plant plant;

    @Relation(parentColumn = "plantType_id", entityColumn = "plantType_id")
    public PlantType plantType;
}
