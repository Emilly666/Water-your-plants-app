package com.example.water_your_plants_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.water_your_plants_app.database.daos.Dao_Plant;
import com.example.water_your_plants_app.database.daos.Dao_PlantType;
import com.example.water_your_plants_app.database.daos.Dao_Task;
import com.example.water_your_plants_app.database.daos.Dao_UserPlant;
import com.example.water_your_plants_app.database.tables.Plant;
import com.example.water_your_plants_app.database.tables.PlantType;
import com.example.water_your_plants_app.database.tables.Task;
import com.example.water_your_plants_app.database.tables.UserPlant;

@Database(entities = {PlantType.class, Plant.class, UserPlant.class, Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract Dao_PlantType dao_plantType();
    public abstract Dao_Plant dao_plant();
    public abstract Dao_UserPlant dao_userPlant();
    public abstract Dao_Task dao_task();

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "PLANTS_DATABASE.db")
                        .allowMainThreadQueries()
                        .build();
            }
        return INSTANCE;
    }
}
