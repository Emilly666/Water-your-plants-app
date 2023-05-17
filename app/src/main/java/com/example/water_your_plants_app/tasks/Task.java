package com.example.water_your_plants_app.tasks;

import java.util.Date;

public class Task {
    private boolean expanded;

    private int task_id;
    private final int type; //0 = WATER //1 = FERTILIZE
    private Date date;              // task's date
    private String plantNickname;   // plants nickname
    private int plantUser_id;       // user plant's id
    private String fertilizerType;  // short description, null if type == watering


    public Task(int type){
        this.expanded = false;
        this.type = type;
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
