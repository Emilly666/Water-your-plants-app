package com.example.water_your_plants_app.database.tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Table_Task {

    @PrimaryKey(autoGenerate = true)
    public int task_id;
}
