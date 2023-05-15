package com.example.water_your_plants_app;

public class Task {
    private final int VIEW_TYPE_ITEM_WATER = 0;
    private final int VIEW_TYPE_ITEM_FERTILIZE = 1;

    private final int type;
    private boolean expanded = false;

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
