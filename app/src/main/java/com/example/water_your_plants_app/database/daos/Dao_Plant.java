package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.tables.Table_Plant;

import java.util.List;

@Dao
public interface Dao_Plant {

    @Insert
    void insert(Table_Plant plant);

    @Query("SELECT * FROM plants")
    List<Table_Plant> getAllPlants();
}
