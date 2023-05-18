package com.example.water_your_plants_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.water_your_plants_app.database.relations.PlantsWithTypes;
import com.example.water_your_plants_app.database.tables.Plant;

import java.util.List;

@Dao
public interface Dao_Plant {

    @Insert
    void insertPlant(Plant plant);
    @Query("SELECT * FROM plants")
    LiveData<List<Plant>> getAllPlants();

    @Transaction
    @Query("SELECT * FROM plants")
    List<PlantsWithTypes> getAllPlantWithPlantType();
    @Transaction
    @Query("SELECT * FROM plants WHERE plant_id = :plant_id")
    PlantsWithTypes getPlantWithPlantType(long plant_id);
}
