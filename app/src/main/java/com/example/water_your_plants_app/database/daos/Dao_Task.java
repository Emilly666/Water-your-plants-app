package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.tables.Table_Task;

import java.util.List;

@Dao
public interface Dao_Task {

    @Insert
    void insert(Table_Task task);

    @Query("SELECT * FROM tasks")
    List<Table_Task> getAllTasks();
}
