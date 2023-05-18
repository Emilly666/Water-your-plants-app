package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.tables.Plant;

import java.util.List;

@Dao
public interface Dao_Plant {

    @Insert
    void insertPlant(Plant plant);
    @Delete
    void deletePlant(Plant plant);

    @Query("SELECT * FROM plants")
    List<Plant> getAllPlants();
}
