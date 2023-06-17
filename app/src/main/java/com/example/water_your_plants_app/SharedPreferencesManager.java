package com.example.water_your_plants_app;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.LocalTime;
import java.time.temporal.ChronoField;

public class SharedPreferencesManager {
    private static final String APP_PREFS = "AppPrefsFile";
    private static final Long notificationsTime = LocalTime.of(10,0).getLong(ChronoField.SECOND_OF_DAY);
    private static final boolean notificationsWater = true;
    private static final boolean notificationsFertilize = true;

    private SharedPreferences sp;
    private static SharedPreferencesManager instance;

    private SharedPreferencesManager(Context context) {
        sp = context.getApplicationContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }
    public static synchronized SharedPreferencesManager getInstance(Context context){
        if(instance == null)
            instance = new SharedPreferencesManager(context);

        return instance;
    }

    public void setNotificationsTime(LocalTime time) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("notificationsTime", time.getLong(ChronoField.SECOND_OF_DAY));
        editor.apply();
    }
    public LocalTime getNotificationsTime() {
        return LocalTime.ofSecondOfDay(sp.getLong("notificationsTime", LocalTime.of(10,0).getLong(ChronoField.SECOND_OF_DAY)));
    }

    public void setNotificationsWater(boolean notificationsWater) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("notificationsWater", notificationsWater);
        editor.apply();
    }
    public boolean getNotificationsWater() {
        return sp.getBoolean("notificationsWater", true);
    }

    public void setNotificationsFertilize(boolean notificationsFertilize) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("notificationsFertilize", notificationsFertilize);
        editor.apply();
    }
    public boolean getNotificationsFertilize() {
        return sp.getBoolean("notificationsFertilize", true);
    }
}