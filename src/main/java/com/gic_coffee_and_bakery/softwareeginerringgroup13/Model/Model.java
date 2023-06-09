package com.gic_coffee_and_bakery.softwareeginerringgroup13.Model;

import com.google.gson.Gson;

public class Model {
    private int id;

    public Model() {
    }

    public Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
}
