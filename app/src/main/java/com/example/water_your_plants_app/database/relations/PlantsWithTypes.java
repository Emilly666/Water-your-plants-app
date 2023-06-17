package com.example.water_your_plants_app.database.relations;

import androidx.room.Embedded;

import com.example.water_your_plants_app.database.tables.Plant;
import com.example.water_your_plants_app.database.tables.PlantType;

public class PlantsWithTypes {
    @Embedded
    public Plant plant;
    @Embedded
    public PlantType plantType;

}
