package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.tables.Table_PlantType;

import java.util.List;

@Dao
public interface Dao_PlantType {

    @Insert
    void insert(Table_PlantType plantType);

    @Query("SELECT * FROM plantTypes")
    List<Table_PlantType> getAllPlantTypes();
}
