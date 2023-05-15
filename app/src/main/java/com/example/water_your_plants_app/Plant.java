package com.example.water_your_plants_app;

public class Plant {
    private boolean expanded = false;

    public Plant(){
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
