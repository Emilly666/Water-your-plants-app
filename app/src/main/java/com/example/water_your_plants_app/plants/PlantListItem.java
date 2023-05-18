package com.example.water_your_plants_app.plants;

import com.example.water_your_plants_app.database.relations.UserPlantsWithTypes;

public class PlantListItem {
    private boolean expanded = false;

    public UserPlantsWithTypes userPlantsWithTypes;

    public PlantListItem(UserPlantsWithTypes userPlantsWithTypes){
        this.userPlantsWithTypes = userPlantsWithTypes;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
