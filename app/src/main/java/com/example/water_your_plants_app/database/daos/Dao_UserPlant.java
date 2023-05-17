package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.tables.Table_UserPlant;

import java.util.List;

@Dao
public interface Dao_UserPlant {

    @Insert
    void insert(Table_UserPlant userPlant);

    @Query("SELECT * FROM userPlants")
    List<Table_UserPlant> getAllUserPlants();

    @Query("SELECT * FROM userPlants WHERE userPlant_id = :id")
    Table_UserPlant getUserPlant(int id);

    @Delete
    void delete(Table_UserPlant userPlant);
}
