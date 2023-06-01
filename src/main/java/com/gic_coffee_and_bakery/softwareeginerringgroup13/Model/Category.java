package com.gic_coffee_and_bakery.softwareeginerringgroup13.Model;


public class Category extends Model {
    private String categoryName;
    
    public Category() {
        // Default constructor
    }

    public Category(int id, String categoryName) {
        super(id);
        this.categoryName = categoryName;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
