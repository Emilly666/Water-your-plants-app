package com.example.water_your_plants_app.database.tables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks",
        foreignKeys = @ForeignKey(entity = UserPlant.class, parentColumns = "userPlant_id", childColumns = "myUserPlant_id", onDelete = ForeignKey.CASCADE))

public class Task {

    @PrimaryKey(autoGenerate = true)
    public long task_id;

    public long myUserPlant_id;
    public short taskType;

    public String date;

    public Task(long task_id, long myUserPlant_id, short taskType, String date ){
        this.task_id = task_id;
        this.myUserPlant_id = myUserPlant_id;
        this.taskType = taskType;
        this.date = date;
    }
}
