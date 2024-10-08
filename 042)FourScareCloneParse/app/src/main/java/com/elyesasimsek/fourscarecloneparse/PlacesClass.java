package com.elyesasimsek.fourscarecloneparse;

import android.graphics.Bitmap;

public class PlacesClass {

    private static PlacesClass instance;

    private Bitmap image;
    private String name;
    private String type;
    private String atmosphere;

    public PlacesClass() {
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(String atmosphere) {
        this.atmosphere = atmosphere;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static PlacesClass getInstance() {
        if (instance == null){
            instance = new PlacesClass();
        }
        return instance;
    }
}
