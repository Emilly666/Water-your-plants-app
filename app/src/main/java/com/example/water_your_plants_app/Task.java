package com.example.water_your_plants_app;

import java.util.Date;

public class Task {
    private boolean expanded = false;

    private int task_id;
    private final int type; //0 = WATER //1 = FERTILIZE
    private Date date;              // task's date
    private String plantNickname;   // plants nickname
    private int plantUser_id;       // user plant's id
    private String fertilizerType;  // short description, null if type == watering


    public Task(int type){
        this.type = type;
        this.expanded = false;
    }
    public int getType(){
        return type;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
