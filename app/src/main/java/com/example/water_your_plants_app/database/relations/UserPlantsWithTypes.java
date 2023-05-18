package com.example.water_your_plants_app.database.relations;

import androidx.room.Embedded;

import com.example.water_your_plants_app.database.tables.Plant;
import com.example.water_your_plants_app.database.tables.PlantType;
import com.example.water_your_plants_app.database.tables.UserPlant;

public class UserPlantsWithTypes {
    @Embedded
    public UserPlant userPlant;
    @Embedded
    public Plant plant;
    @Embedded
    public PlantType plantType;
}
