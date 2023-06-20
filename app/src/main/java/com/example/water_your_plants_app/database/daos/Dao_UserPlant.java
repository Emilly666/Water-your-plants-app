package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.relations.UserPlantsWithTypes;
import com.example.water_your_plants_app.database.tables.UserPlant;

import java.util.List;

@Dao
public interface Dao_UserPlant {

    @Insert
    void insertUserPlant(UserPlant userPlant);
    @Delete
    void deleteUserPlant(UserPlant userPlant);

    @Query("DELETE FROM userPlants WHERE userPlant_id = :id")
    void deleteUserPlantWitTypes(int id);

    @Query("SELECT * FROM userPlants")
    List<UserPlant> getAllUserPlants();
    @Query("SELECT * FROM userPlants WHERE userPlant_id = :id")
    UserPlant getUserPlant(int id);

    @Query("SELECT up.*, p.*, pt.* " +
            "FROM userPlants up " +
            "INNER JOIN plants p ON up.myPlant_id = p.plant_id " +
            "INNER JOIN plantTypes pt ON p.plantType_id = pt.type_id " +
            "ORDER BY up.userPlant_id DESC " +
            "LIMIT 1")
    UserPlantsWithTypes getLatestUserPlantWithType();
    @Query("SELECT up.*, p.*, pt.* " +
            "FROM userPlants up " +
            "INNER JOIN plants p ON up.myPlant_id = p.plant_id " +
            "INNER JOIN plantTypes pt ON p.plantType_id = pt.type_id ")
    List<UserPlantsWithTypes> getAllUserPlantsWithTypes();
}
