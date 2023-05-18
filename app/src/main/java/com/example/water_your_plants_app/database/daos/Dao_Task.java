package com.example.water_your_plants_app.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.water_your_plants_app.database.tables.Task;

import java.util.List;

@Dao
public interface Dao_Task {

    @Insert
    void insert(Task task);

    @Query("SELECT * FROM tasks")
    List<Task> getAllTasks();
}
