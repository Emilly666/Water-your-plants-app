package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.tables.PlantType;

import java.util.List;

@Dao
public interface Dao_PlantType {

    @Insert
    void insertPlantType(PlantType plantType);
    @Delete
    void deletePlantType(PlantType plantType);

    @Query("SELECT * FROM plantTypes")
    List<PlantType> getAllPlantTypes();

    @Query("SELECT typeName FROM plantTypes")
    List<String> getAllPlantTypesNames();

    @Query("SELECT * FROM plantTypes WHERE type_id = :id")
    PlantType getPlantTypeById(long id);

    @Query("SELECT COUNT(type_id) FROM plantTypes;")
    int getTypesCount();
}
