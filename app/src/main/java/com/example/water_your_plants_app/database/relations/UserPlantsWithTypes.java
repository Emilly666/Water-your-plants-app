package com.example.water_your_plants_app.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.water_your_plants_app.database.tables.Plant;
import com.example.water_your_plants_app.database.tables.UserPlant;

public class UserPlantsWithTypes {
    @Embedded
    public UserPlant userPlant;

    @Relation(parentColumn = "plant_id", entityColumn = "plant_id")
    public Plant plant;
}
