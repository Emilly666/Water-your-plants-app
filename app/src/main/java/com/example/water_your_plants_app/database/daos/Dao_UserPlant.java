package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.water_your_plants_app.database.relations.UserPlantsWithTypes;
import com.example.water_your_plants_app.database.tables.UserPlant;

import java.util.List;

@Dao
public interface Dao_UserPlant {

    @Insert
    void insert(UserPlant userPlant);
    @Delete
    void delete(UserPlant userPlant);

    @Query("SELECT * FROM userPlants")
    List<UserPlant> getAllUserPlants();
    @Query("SELECT * FROM userPlants WHERE userPlant_id = :id")
    UserPlant getUserPlant(int id);

    @Transaction
    @Query("SELECT * FROM plants")
    List<UserPlantsWithTypes> getAllUserPlantWithPlantType();
    @Transaction
    @Query("SELECT * FROM plants WHERE plant_id = :plant_id")
    UserPlantsWithTypes getUserPlantWithPlantType(long plant_id);
}
