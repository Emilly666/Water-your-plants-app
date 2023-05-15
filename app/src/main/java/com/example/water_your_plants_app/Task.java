package com.example.water_your_plants_app;

import java.util.Date;

public class Task {
    private boolean expanded = false;
    private final int type; //0 = WATER //1 = FERTILIZE

    private int task_id;
    private Date date;
    private String plantNickname;
    private String fertilizerType;


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
