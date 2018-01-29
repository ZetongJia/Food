package com.example.tina.underthebutton;

/**
 * Created by apple on 18/1/28.
 */

public class FoodTruck {
    public FoodTruck(String name, String rating, int id, double lat, double lon) {
        this.name = name;
        this.rating = rating;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }

    private String name;
    private String rating;
    private int id;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    private double lat;
    private double lon;

    public String getRating() {
        return rating;
    }

    public int getID() {
        return id;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
