package com.example.water_your_plants_app.plants;

public class PlantObj {
    private boolean expanded = false;

    private int id;

    public String name;            // plant species
    private String nickname;        // nickname given by user
    private String plantType;       // category name
    private int temperatureFrom;    // integer in Celsius
    private int temperatureTo;      // integer in Celsius
    private String light;           // short description
    private int humidity;           // integer between 0 and 100, representing % of humidity
    private String soil;            // short description of soil
    private int wateringFrequency;  // integer, number of bays between watering
    private String fertilizerType;  // short description of fertilizer
    private int fertilizerFrequency;// integer, number of bays between fertilizing

    public PlantObj(String name, String plantType){
        this.name = name;
        this.plantType = plantType;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
