package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.tables.PlantType;

import java.util.List;

@Dao
public interface Dao_PlantType {

    @Insert
    void insert(PlantType plantType);

    @Query("SELECT * FROM plantTypes")
    List<PlantType> getAllPlantTypes();

    @Query("SELECT * FROM plantTypes WHERE plantType_id = :plantType_id")
    PlantType getPlantTypeById(long plantType_id);
}
